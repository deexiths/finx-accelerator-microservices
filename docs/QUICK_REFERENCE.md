# Quick Reference - Nx Commands

## Most Used Commands

### Build & Test
```bash
# Build everything
npm run build

# Build specific service
npx nx build finx-logger-service-sapi

# Test everything
npm run test

# Build/test only what changed
npx nx affected --target=build
npx nx affected --target=test
```

### Development
```bash
# Run all services
npm run dev

# Run specific service
npx nx serve finx-logger-service-sapi

# View dependency graph
npm run graph
```

### Filtering
```bash
# Show only Java projects
npm run graph:java

# Show only applications
npm run graph:apps

# Show only libraries
npm run graph:libs
```

### Utilities
```bash
# Reset cache if issues
npx nx reset

# Show all projects
npx nx show projects

# Format code
npx nx format:write
```

## Project Structure
```
services/           # All microservices
shared/            # Shared libraries
docs/              # Documentation
tools/             # Build utilities
```

## Logging & Debugging
```bash
# Build with verbose logs
npx nx build finx-logger-service-sapi --verbose

# Service runtime logs
npx nx serve finx-logger-service-sapi

# Gradle detailed logs
cd services/finx-logger-service-sapi && gradlew build --info

# See what's affected
npx nx affected --target=build --verbose

# Project details
npx nx show project finx-logger-service-sapi
```

## Log Locations
- **Nx**: `.nx/cache/terminalOutputs/`
- **Gradle**: `services/{service}/build/reports/`
- **Tests**: `services/{service}/build/reports/tests/`

## Adding New Services
- **Java**: Copy existing service structure
- **Node.js**: `npx nx g @nx/node:application --name=my-service --directory=services`
- **Python**: `npx nx g @nx/python:application --name=my-service --directory=services`