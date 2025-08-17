# Finx Accelerator Microservices - Monorepo Guide

## Table of Contents
1. [What is a Monorepo?](#what-is-a-monorepo)
2. [Why Use Monorepo?](#why-use-monorepo)
3. [What is Nx?](#what-is-nx)
4. [Why Use Nx?](#why-use-nx)
5. [Project Structure](#project-structure)
6. [Available Commands](#available-commands)
7. [What We've Built](#what-weve-built)
8. [Getting Started](#getting-started)
9. [Adding New Services](#adding-new-services)

## What is a Monorepo?

A **monorepo** (monolithic repository) is a software development strategy where code for many projects is stored in the same repository. Instead of having separate repositories for each service/library, everything lives in one place.

### Traditional Multi-Repo vs Monorepo

**Multi-Repo (Traditional):**
```
├── user-service/          (separate repo)
├── payment-service/       (separate repo)
├── notification-service/  (separate repo)
└── shared-utils/          (separate repo)
```

**Monorepo:**
```
finx-accelerator-microservices/
├── services/
│   ├── user-service/
│   ├── payment-service/
│   └── notification-service/
└── shared/
    └── common-utils/
```

## Why Use Monorepo?

### ✅ Benefits

1. **Unified Versioning**: All services use the same version of dependencies
2. **Code Sharing**: Easy to share common utilities and libraries
3. **Atomic Changes**: Make changes across multiple services in one commit
4. **Simplified CI/CD**: One pipeline for all services
5. **Better Collaboration**: Teams can see and contribute to all code
6. **Consistent Tooling**: Same build tools, linting, testing across all projects
7. **Easier Refactoring**: Rename/move code across service boundaries

### ❌ Challenges

1. **Repository Size**: Can become large over time
2. **Build Times**: May take longer to build everything
3. **Access Control**: Harder to restrict access to specific services
4. **Learning Curve**: Teams need to understand the entire codebase

## What is Nx?

**Nx** is a smart, fast, and extensible build system with first-class monorepo support and powerful integrations. It's developed by the team behind Angular CLI.

### Key Features:
- **Smart Rebuilds**: Only rebuilds what changed
- **Distributed Caching**: Shares build artifacts across team
- **Code Generation**: Scaffolds new projects and components
- **Dependency Graph**: Visualizes project relationships
- **Plugin Ecosystem**: Supports React, Angular, Node.js, Java, Python, etc.

## Why Use Nx?

### 🚀 Performance
- **Incremental Builds**: Only builds affected projects
- **Computation Caching**: Caches build results locally and remotely
- **Parallel Execution**: Runs tasks in parallel when possible

### 🛠️ Developer Experience
- **Code Generation**: `nx g` commands to scaffold projects
- **Dependency Graph**: Visual representation of project dependencies
- **Affected Commands**: Only test/build what changed
- **IDE Integration**: Works with VS Code, IntelliJ, etc.

### 🏗️ Scalability
- **Language Agnostic**: Java, Node.js, Python, Go, etc.
- **Framework Support**: Spring Boot, React, Angular, NestJS, etc.
- **Plugin Architecture**: Extensible with custom plugins

## Project Structure

```
finx-accelerator-microservices/
├── services/                           # All microservices
│   ├── finx-logger-service-sapi/       # Java Spring Boot service
│   └── finx-payload-transformer-service-sapi/  # Java Spring Boot service
├── shared/                             # Shared libraries
│   └── java/                           # Java shared configuration
│       ├── common-dependencies.gradle  # Common Gradle dependencies
│       └── project.json               # Nx project configuration
├── docs/                               # Documentation
├── tools/                              # Build scripts and utilities
├── .gitignore                          # Git ignore rules
├── nx.json                             # Nx workspace configuration
├── package.json                        # Node.js dependencies and scripts
├── tsconfig.base.json                  # TypeScript base configuration
└── README.md                           # Project overview
```

### Service Structure (Java)
```
services/finx-logger-service-sapi/
├── src/
│   ├── main/java/                      # Java source code
│   └── test/java/                      # Test code
├── build.gradle                        # Gradle build configuration
├── project.json                        # Nx project configuration
├── Dockerfile                          # Docker configuration
└── docker-compose.yml                  # Local development setup
```

## Available Commands

### Core Nx Commands

#### Build Commands
```bash
# Build all projects
npm run build
npx nx run-many --target=build --all

# Build specific service
npx nx build finx-logger-service-sapi
npx nx build finx-payload-transformer-service-sapi

# Build only affected projects (changed since last commit)
npx nx affected --target=build
```

#### Test Commands
```bash
# Test all projects
npm run test
npx nx run-many --target=test --all

# Test specific service
npx nx test finx-logger-service-sapi

# Test only affected projects
npx nx affected --target=test
```

#### Development Commands
```bash
# Run all services in development mode
npm run dev
npx nx run-many --target=serve --all --parallel

# Run specific service
npx nx serve finx-logger-service-sapi
```

#### Graph Visualization
```bash
# Show project dependency graph
npm run graph
npx nx graph

# Show only affected projects
npm run graph:affected
npx nx affected:graph

# Filter by language/type
npm run graph:java        # Show only Java projects
npm run graph:apps        # Show only applications
npm run graph:libs        # Show only libraries
```

#### Utility Commands
```bash
# Show project information
npx nx show projects
npx nx show project finx-logger-service-sapi

# Reset Nx cache
npx nx reset

# Lint code
npm run lint
npx nx run-many --target=lint --all

# Format code
npx nx format:write
```

### Advanced Commands

#### Affected Analysis
```bash
# See what's affected by changes
npx nx affected:apps
npx nx affected:libs

# Run affected with specific base
npx nx affected --target=build --base=main
npx nx affected --target=test --base=origin/main
```

#### Code Generation
```bash
# Generate new Java service
npx nx g @nx/gradle:application --name=my-service --directory=services

# Generate new Node.js service
npx nx g @nx/node:application --name=my-service --directory=services

# Generate new Python service
npx nx g @nx/python:application --name=my-service --directory=services
```

## What We've Built

### 1. Polyglot Monorepo Structure
- **Language Agnostic**: Supports Java, Node.js, Python
- **No Root Dependencies**: Each service manages its own build
- **Shared Configuration**: Common Gradle setup for Java services

### 2. Java Services
- **finx-logger-service-sapi**: Logging microservice with Spring Boot
- **finx-payload-transformer-service-sapi**: Data transformation service
- **Shared Dependencies**: Common Spring Boot, Lombok, testing libraries

### 3. Build System
- **Nx Orchestration**: Unified build system across all languages
- **Gradle Integration**: Each Java service has its own Gradle wrapper
- **Caching**: Build results cached for faster subsequent builds

### 4. Developer Experience
- **Graph Visualization**: See project dependencies visually
- **Affected Builds**: Only build what changed
- **IDE Integration**: Works with IntelliJ, VS Code
- **Docker Support**: Each service has Dockerfile

## Getting Started

### Prerequisites
- **Node.js** (v16+)
- **Java** (v11+)
- **Git**
- **IDE** (IntelliJ IDEA, VS Code)

### Setup Steps

1. **Clone Repository**
   ```bash
   git clone <repository-url>
   cd finx-accelerator-microservices
   ```

2. **Install Dependencies**
   ```bash
   npm install
   ```

3. **Build All Services**
   ```bash
   npm run build
   ```

4. **View Dependency Graph**
   ```bash
   npm run graph
   ```

5. **Run Services**
   ```bash
   # Run specific service
   npx nx serve finx-logger-service-sapi
   
   # Or run all services
   npm run dev
   ```

### IDE Setup

#### IntelliJ IDEA
1. Open the root folder
2. Import as Gradle project
3. IntelliJ will auto-detect all services
4. Enable auto-import for Gradle

#### VS Code
1. Install Java Extension Pack
2. Install Nx Console extension
3. Open root folder
4. VS Code will detect Gradle projects

## Adding New Services

### Java Service
```bash
# Create new Java service
mkdir services/my-new-service
cd services/my-new-service

# Copy from existing service or use Nx generator
npx nx g @nx/gradle:application --name=my-new-service --directory=services
```

### Node.js Service
```bash
# Generate Node.js service
npx nx g @nx/node:application --name=my-node-service --directory=services
```

### Python Service
```bash
# Generate Python service
npx nx g @nx/python:application --name=my-python-service --directory=services
```

## Best Practices

### 1. Project Organization
- Keep services in `services/` directory
- Put shared code in `shared/` directory
- Use consistent naming: `finx-{domain}-service-{type}`

### 2. Dependencies
- Use shared configurations for common dependencies
- Keep service-specific dependencies in individual build files
- Regular dependency updates across all services

### 3. Testing
- Run affected tests before commits: `npx nx affected --target=test`
- Maintain test coverage across all services
- Use shared testing utilities

### 4. CI/CD
- Use `npx nx affected` in CI pipelines
- Cache build artifacts with Nx Cloud
- Deploy only affected services

### 5. Development Workflow
- Use feature branches
- Run `npx nx affected --target=build,test,lint` before PR
- Keep commits atomic across service boundaries

## Troubleshooting

### Common Issues

1. **Build Failures**
   ```bash
   # Reset Nx cache
   npx nx reset
   
   # Clean and rebuild
   npm run build
   ```

2. **IDE Not Recognizing Projects**
   - Ensure services are built: `npm run build`
   - Refresh IDE project structure
   - Check `project.json` files exist

3. **Gradle Issues**
   - Each service has its own Gradle wrapper
   - Use service-specific Gradle commands
   - Check Java version compatibility

## Logging and Debugging

### Build Logs
```bash
# Verbose build logs
npx nx build finx-logger-service-sapi --verbose

# Build with detailed output
npx nx build finx-logger-service-sapi --skip-nx-cache

# Show all build output (no summary)
npx nx build finx-logger-service-sapi --output-style=stream
```

### Gradle Build Logs
```bash
# From service directory with detailed logs
cd services/finx-logger-service-sapi
gradlew build --info

# Debug level logs
gradlew build --debug

# Stacktrace on failures
gradlew build --stacktrace
```

### Service Runtime Logs
```bash
# Run service and see logs
npx nx serve finx-logger-service-sapi

# Or directly with Gradle
cd services/finx-logger-service-sapi
gradlew bootRun
```

### Docker Logs
```bash
# If running with Docker
docker-compose logs -f finx-logger-service-sapi

# All services logs
docker-compose logs -f
```

### Nx Operation Logs
```bash
# See what's affected with details
npx nx affected --target=build --verbose

# Show dependency graph reasons
npx nx affected:graph --verbose

# See cache hits/misses
npx nx build finx-logger-service-sapi --verbose
```

### Debug Commands
```bash
# Project configuration
npx nx show project finx-logger-service-sapi

# All projects
npx nx show projects --verbose

# Stop/start daemon with logs
npx nx daemon --stop
npx nx daemon --start --verbose
```

### Log File Locations
- **Nx Logs**: `.nx/cache/` and `.nx/cache/terminalOutputs/`
- **Gradle Logs**: `services/{service-name}/build/reports/`
- **Test Reports**: `services/{service-name}/build/reports/tests/`
- **Application Logs**: Console output or configured log files

### Most Useful Debug Commands
```bash
# Build with full logs
npx nx build finx-logger-service-sapi --verbose --skip-nx-cache

# Run service and see all logs
npx nx serve finx-logger-service-sapi --verbose

# Test with detailed output
npx nx test finx-logger-service-sapi --verbose

# See what changed and why
npx nx affected --target=build --verbose --dry-run
```

## Resources

- [Nx Documentation](https://nx.dev)
- [Monorepo Best Practices](https://nx.dev/concepts/more-concepts/why-monorepos)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Gradle Documentation](https://gradle.org/guides/)

---

**Questions?** Contact the development team or check the project's issue tracker.