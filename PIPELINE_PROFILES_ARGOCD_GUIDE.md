# Pipeline Profiles & ArgoCD Integration Guide

## Java Spring Profiles Management

### 1. Profile-Based Commands in project.json

Add profile-specific commands to Java services:

```json
{
  "targets": {
    "serve:dev": {
      "executor": "nx:run-commands",
      "options": {
        "command": "gradlew.bat bootRun --args='--spring.profiles.active=dev'",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "serve:qa": {
      "executor": "nx:run-commands",
      "options": {
        "command": "gradlew.bat bootRun --args='--spring.profiles.active=qa'",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "serve:prod": {
      "executor": "nx:run-commands",
      "options": {
        "command": "gradlew.bat bootRun --args='--spring.profiles.active=prod'",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "build:dev": {
      "executor": "nx:run-commands",
      "options": {
        "command": "gradlew.bat build -Pprofile=dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "build:qa": {
      "executor": "nx:run-commands",
      "options": {
        "command": "gradlew.bat build -Pprofile=qa",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "build:prod": {
      "executor": "nx:run-commands",
      "options": {
        "command": "gradlew.bat build -Pprofile=prod",
        "cwd": "services/finx-logger-service-sapi"
      }
    }
  }
}
```

### 2. Docker Profile Commands

```json
{
  "targets": {
    "docker:build:dev": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build --build-arg PROFILE=dev -t finx-logger-service:dev .",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "docker:build:qa": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build --build-arg PROFILE=qa -t finx-logger-service:qa .",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "docker:build:prod": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build --build-arg PROFILE=prod -t finx-logger-service:prod .",
        "cwd": "services/finx-logger-service-sapi"
      }
    }
  }
}
```

## CI/CD Pipeline Integration

### 1. GitHub Actions Pipeline

```yaml
# .github/workflows/deploy.yml
name: Deploy Services

on:
  push:
    branches: [main, develop, qa]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Set Profile Based on Branch
        id: profile
        run: |
          if [[ "${{ github.ref }}" == "refs/heads/main" ]]; then
            echo "profile=prod" >> $GITHUB_OUTPUT
          elif [[ "${{ github.ref }}" == "refs/heads/qa" ]]; then
            echo "profile=qa" >> $GITHUB_OUTPUT
          else
            echo "profile=dev" >> $GITHUB_OUTPUT
          fi
      
      - name: Build Java Services
        run: |
          nx build:${{ steps.profile.outputs.profile }} finx-logger-service-sapi
          nx build:${{ steps.profile.outputs.profile }} finx-payload-transformer-service-sapi
      
      - name: Build Docker Images
        run: |
          nx docker:build:${{ steps.profile.outputs.profile }} finx-logger-service-sapi
          nx docker:build:${{ steps.profile.outputs.profile }} finx-payload-transformer-service-sapi
      
      - name: Push to Registry
        run: |
          docker push finx-logger-service:${{ steps.profile.outputs.profile }}
          docker push finx-payload-transformer:${{ steps.profile.outputs.profile }}
```

### 2. Jenkins Pipeline

```groovy
pipeline {
    agent any
    
    parameters {
        choice(
            name: 'PROFILE',
            choices: ['dev', 'qa', 'prod'],
            description: 'Deployment Profile'
        )
        choice(
            name: 'SERVICE',
            choices: ['all', 'finx-logger-service-sapi', 'finx-payload-transformer-service-sapi'],
            description: 'Service to Deploy'
        )
    }
    
    stages {
        stage('Build') {
            steps {
                script {
                    if (params.SERVICE == 'all') {
                        sh "nx run-many --target=build:${params.PROFILE} --projects=tag:lang:java"
                    } else {
                        sh "nx build:${params.PROFILE} ${params.SERVICE}"
                    }
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    if (params.SERVICE == 'all') {
                        sh "nx run-many --target=docker:build:${params.PROFILE} --projects=tag:lang:java"
                    } else {
                        sh "nx docker:build:${params.PROFILE} ${params.SERVICE}"
                    }
                }
            }
        }
        
        stage('Deploy to ArgoCD') {
            steps {
                sh """
                    argocd app sync finx-${params.SERVICE}-${params.PROFILE}
                    argocd app wait finx-${params.SERVICE}-${params.PROFILE}
                """
            }
        }
    }
}
```

## ArgoCD Integration

### 1. ArgoCD Application Manifests

```yaml
# argocd/applications/finx-logger-service-dev.yaml
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata:
  name: finx-logger-service-dev
  namespace: argocd
spec:
  project: finx-platform
  source:
    repoURL: https://github.com/your-org/finx-accelerator-platform
    targetRevision: develop
    path: k8s/overlays/dev/finx-logger-service
  destination:
    server: https://kubernetes.default.svc
    namespace: finx-dev
  syncPolicy:
    automated:
      prune: true
      selfHeal: true
```

### 2. Kustomization for Profiles

```yaml
# k8s/overlays/dev/finx-logger-service/kustomization.yaml
apiVersion: kustomize.config.k8s.io/v1beta1
kind: Kustomization

resources:
  - ../../../base/finx-logger-service

patchesStrategicMerge:
  - deployment-patch.yaml
  - configmap-patch.yaml

images:
  - name: finx-logger-service
    newTag: dev
```

```yaml
# k8s/overlays/dev/finx-logger-service/deployment-patch.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: finx-logger-service
spec:
  template:
    spec:
      containers:
      - name: finx-logger-service
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "dev"
        - name: LOG_LEVEL
          value: "DEBUG"
```

## Logging Management & Monitoring

### 1. Service-Specific Logging Commands

Add to each Java service project.json:

```json
{
  "targets": {
    "logs:argocd": {
      "executor": "nx:run-commands",
      "options": {
        "command": "argocd app logs finx-logger-service-dev --follow",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "logs:k8s": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl logs -f deployment/finx-logger-service -n finx-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "logs:k8s:previous": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl logs deployment/finx-logger-service -n finx-dev --previous",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "logs:pod": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl get pods -n finx-dev -l app=finx-logger-service -o name | head -1 | xargs kubectl logs -f -n finx-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "logs:error:k8s": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl logs deployment/finx-logger-service -n finx-dev | grep -i error",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "describe:pod": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl get pods -n finx-dev -l app=finx-logger-service -o name | head -1 | xargs kubectl describe -n finx-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    }
  }
}
```

### 2. Environment-Specific Logging

```json
{
  "targets": {
    "logs:dev": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl logs -f deployment/finx-logger-service -n finx-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "logs:qa": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl logs -f deployment/finx-logger-service -n finx-qa",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "logs:prod": {
      "executor": "nx:run-commands",
      "options": {
        "command": "kubectl logs -f deployment/finx-logger-service -n finx-prod",
        "cwd": "services/finx-logger-service-sapi"
      }
    }
  }
}
```

### 3. ArgoCD Monitoring Commands

```json
{
  "targets": {
    "argocd:status": {
      "executor": "nx:run-commands",
      "options": {
        "command": "argocd app get finx-logger-service-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "argocd:sync": {
      "executor": "nx:run-commands",
      "options": {
        "command": "argocd app sync finx-logger-service-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "argocd:rollback": {
      "executor": "nx:run-commands",
      "options": {
        "command": "argocd app rollback finx-logger-service-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "argocd:diff": {
      "executor": "nx:run-commands",
      "options": {
        "command": "argocd app diff finx-logger-service-dev",
        "cwd": "services/finx-logger-service-sapi"
      }
    }
  }
}
```

## Usage Examples

### Profile-Based Development:
```bash
# Run with specific profile
nx serve:dev finx-logger-service-sapi
nx serve:qa finx-logger-service-sapi
nx serve:prod finx-logger-service-sapi

# Build for specific environment
nx build:dev finx-logger-service-sapi
nx build:qa finx-logger-service-sapi
nx build:prod finx-logger-service-sapi
```

### ArgoCD & Kubernetes Logging:
```bash
# Check ArgoCD application status
nx argocd:status finx-logger-service-sapi

# View live logs from Kubernetes
nx logs:k8s finx-logger-service-sapi

# View logs from specific environment
nx logs:dev finx-logger-service-sapi
nx logs:qa finx-logger-service-sapi
nx logs:prod finx-logger-service-sapi

# Check for errors
nx logs:error:k8s finx-logger-service-sapi

# Describe pod for troubleshooting
nx describe:pod finx-logger-service-sapi
```

### Pipeline Integration:
```bash
# CI/CD pipeline commands
nx build:${PROFILE} finx-logger-service-sapi
nx docker:build:${PROFILE} finx-logger-service-sapi
nx argocd:sync finx-logger-service-sapi
```

This setup provides comprehensive profile management, pipeline integration, and logging capabilities for your Java services in the ArgoCD/Kubernetes environment.