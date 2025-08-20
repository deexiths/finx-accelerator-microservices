# Monorepo Migration Strategy

## Current State vs Target State

### Current Architecture:
```
Service A Repo → Pipeline A → ArgoCD App A
Service B Repo → Pipeline B → ArgoCD App B  
Service C Repo → Pipeline C → ArgoCD App C
```

### Target Monorepo Architecture:
```
Monorepo → Smart Pipeline → ArgoCD Apps (A, B, C)
```

## Migration Approaches

### Option 1: Gradual Migration (Recommended)
**Keep existing pipelines, add monorepo gradually**

#### Phase 1: Dual Setup (3-6 months)
- Keep existing separate repos and pipelines running
- Create monorepo with all services
- Run both systems in parallel
- Gradually move teams to use monorepo for development

#### Phase 2: Pipeline Migration (2-3 months)
- Create smart monorepo pipeline that detects changes
- Migrate one service at a time
- Keep ArgoCD apps separate initially

#### Phase 3: Full Consolidation (1-2 months)
- Deprecate old repos and pipelines
- Consolidate ArgoCD applications if needed

### Option 2: Big Bang Migration
**Replace everything at once (Higher Risk)**

## Smart Pipeline for Monorepo

### Nx Affected Detection:
```yaml
# .github/workflows/ci-cd.yml
name: Monorepo CI/CD

on:
  push:
    branches: [main, develop, qa]
  pull_request:
    branches: [main]

jobs:
  detect-changes:
    runs-on: ubuntu-latest
    outputs:
      affected-services: ${{ steps.affected.outputs.services }}
    steps:
      - uses: actions/checkout@v3
        with:
          fetch-depth: 0
      
      - name: Detect Affected Services
        id: affected
        run: |
          # Get affected projects
          AFFECTED=$(npx nx affected:projects --plain)
          echo "services=$AFFECTED" >> $GITHUB_OUTPUT

  build-and-deploy:
    needs: detect-changes
    if: needs.detect-changes.outputs.affected-services != ''
    strategy:
      matrix:
        service: ${{ fromJson(needs.detect-changes.outputs.affected-services) }}
    runs-on: ubuntu-latest
    steps:
      - name: Build Service
        run: nx build:${{ env.PROFILE }} ${{ matrix.service }}
      
      - name: Deploy to ArgoCD
        run: |
          argocd app sync ${{ matrix.service }}-${{ env.PROFILE }}
```

## Pipeline Strategies

### Strategy 1: Service-Specific Pipelines in Monorepo
```yaml
# .github/workflows/service-logger.yml
name: Logger Service Pipeline
on:
  push:
    paths:
      - 'services/finx-logger-service-sapi/**'
      - 'shared/java/**'

jobs:
  build-logger:
    runs-on: ubuntu-latest
    steps:
      - name: Build Logger Service
        run: nx build:${{ env.PROFILE }} finx-logger-service-sapi
```

### Strategy 2: Unified Smart Pipeline
```yaml
# .github/workflows/monorepo.yml
name: Monorepo Pipeline
on: [push, pull_request]

jobs:
  build-affected:
    runs-on: ubuntu-latest
    steps:
      - name: Build All Affected
        run: nx affected:build --base=origin/main
      
      - name: Test All Affected  
        run: nx affected:test --base=origin/main
      
      - name: Deploy All Affected
        run: |
          for service in $(nx affected:projects --base=origin/main --plain); do
            argocd app sync $service-${{ env.PROFILE }}
          done
```

## ArgoCD Integration Options

### Option 1: Keep Separate ArgoCD Apps
```yaml
# Each service keeps its own ArgoCD application
finx-logger-service-dev
finx-payload-transformer-dev
finx-codegen-ui-dev
```

### Option 2: Consolidated ArgoCD App
```yaml
# Single ArgoCD application for entire platform
finx-platform-dev
  ├── logger-service
  ├── payload-transformer
  └── codegen-ui
```

### Option 3: Hybrid Approach
```yaml
# Group related services
finx-backend-services-dev    # Java microservices
finx-frontend-apps-dev       # React applications  
finx-data-services-dev       # Python services
```

## Migration Timeline

### Week 1-2: Setup Monorepo
- Create monorepo structure
- Add all existing services
- Create project.json files
- Test Nx commands locally

### Week 3-4: Parallel Development
- Teams start using monorepo for development
- Keep existing pipelines for deployment
- Validate monorepo setup

### Week 5-8: Pipeline Migration
- Create smart pipeline with affected detection
- Migrate one service pipeline at a time
- Test deployment process

### Week 9-12: Full Migration
- Deprecate old repositories
- Update team workflows
- Optimize ArgoCD setup

## Benefits of Monorepo Approach

### Development Benefits:
- **Unified Commands**: `nx serve:dev service-name`
- **Cross-Service Changes**: Single PR for changes across services
- **Shared Libraries**: Common code in `shared/` folder
- **Consistent Tooling**: Same build, test, lint across all services

### Pipeline Benefits:
- **Smart Builds**: Only build what changed
- **Parallel Execution**: Build multiple services simultaneously
- **Dependency Awareness**: Nx understands service dependencies
- **Caching**: Shared build cache across services

### ArgoCD Benefits:
- **Coordinated Deployments**: Deploy related services together
- **Environment Consistency**: Same configuration patterns
- **Rollback Coordination**: Rollback multiple services if needed

## Recommendation

**Start with Option 1 (Gradual Migration):**

1. **Month 1**: Create monorepo, keep existing pipelines
2. **Month 2**: Teams use monorepo for development
3. **Month 3**: Create smart pipeline, migrate 1-2 services
4. **Month 4**: Migrate remaining services
5. **Month 5**: Optimize and consolidate ArgoCD apps

This approach minimizes risk while providing immediate development benefits.