# Finx Accelerator Platform

A comprehensive polyglot monorepo supporting web applications, mobile apps, and microservices across Java, Node.js, Python, React, and other technologies.

## Structure

```
├── services/           # All applications and services
│   ├── finx-logger-service-sapi/      # Java Spring Boot microservice
│   ├── finx-payload-transformer-service-sapi/  # Java Spring Boot microservice
│   ├── finx-codegen-ui/               # React web application
│   ├── finx-accelerator-test-executions/  # Node.js service
│   └── py-demo/                       # Python FastAPI service
├── shared/            # Shared libraries and utilities
│   ├── java/          # Java shared libraries
│   ├── nodejs/        # Node.js shared packages
│   ├── python/        # Python shared modules
│   └── common/        # Language-agnostic configs
├── tools/             # Build scripts and utilities
└── docs/              # Documentation

```

## Technologies Supported

- **Java**: Spring Boot microservices with Gradle
- **React**: Web applications with modern UI frameworks
- **Node.js**: Backend services and APIs with Express/NestJS
- **Python**: FastAPI/Django services with pip/poetry
- **Mobile**: React Native/Flutter mobile applications
- **Docker**: Containerization for all applications
- **Nx**: Build orchestration and caching across all platforms

## Commands

```bash
# Build all services
npm run build

# Test all services
npm run test

# Run all services in development
npm run dev

# Build specific service
nx build service-name

# Test specific service
nx test service-name
```

## Adding New Services

### Java Service
```bash
nx g @nx/gradle:application --name=my-java-service --directory=services
```

### Node.js Service
```bash
nx g @nx/node:application --name=my-node-service --directory=services
```

### Python Service
```bash
nx g @nx/python:application --name=my-python-service --directory=services
```