# Finx Accelerator Microservices

A polyglot monorepo supporting Java, Node.js, Python, and other technologies.

## Structure

```
├── services/           # All microservices
│   ├── finx-logger-service-sapi/      # Java Spring Boot service
│   └── finx-payload-transformer-service-sapi/  # Java Spring Boot service
├── shared/            # Shared libraries and utilities
│   ├── java/          # Java shared libraries
│   ├── nodejs/        # Node.js shared packages
│   ├── python/        # Python shared modules
│   └── common/        # Language-agnostic configs
├── tools/             # Build scripts and utilities
└── docs/              # Documentation

```

## Technologies Supported

- **Java**: Spring Boot services with Gradle
- **Node.js**: Express/NestJS services with npm/yarn
- **Python**: FastAPI/Django services with pip/poetry
- **Docker**: Containerization for all services
- **Nx**: Build orchestration and caching

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