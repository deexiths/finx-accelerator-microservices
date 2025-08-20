# Video Notes: Nx Commands for Finx Accelerator Platform

## Video Introduction Script

**"Hello and welcome to this tutorial on the Finx Accelerator Platform!**

**In this video, I'll show you how to work with our comprehensive polyglot monorepo that supports web applications, mobile apps, and microservices across multiple technologies including Java, Node.js, React, and Python - all managed efficiently using Nx.**

**What you'll learn today:**
- How to run Spring Boot Java microservices for backend APIs
- How to work with React web applications for user interfaces
- How to manage Node.js services for middleware and APIs
- How to execute Python services for data processing and ML
- How to build and deploy mobile applications
- How to use Nx commands to manage all applications from a single workspace

**This is a production-ready enterprise architecture that allows teams to:**
- Build web applications, mobile apps, and microservices in one repository
- Share code and libraries across different technologies and platforms
- Run consistent build, test, and deployment processes
- Scale development with multiple teams working on different applications
- Maintain code quality with unified tooling and standards across all platforms

**Let's dive in and see how easy it is to work with this powerful setup!"**

## Overview
Step-by-step guide for running web applications, mobile apps, and microservices across Java, Node.js, React, and Python using Nx commands.

## Prerequisites
- Node.js installed
- Java JDK installed
- Python installed
- Nx CLI installed globally: `npm install -g nx`
- Docker (for containerization)
- Mobile development tools (for mobile apps)

## Platform Overview
- **Java Microservices**: Spring Boot backend services
- **React Web Apps**: Modern frontend applications
- **Node.js Services**: API services and middleware
- **Python Services**: Data processing and ML services
- **Mobile Apps**: React Native/Flutter applications

## Step-by-Step Commands

### 1. Java Services (Spring Boot)

#### Available Java Services:
- `finx-logger-service-sapi`
- `finx-payload-transformer-service-sapi`

#### Commands:
```bash
# Install dependencies
nx install finx-logger-service-sapi

# Build
nx build finx-logger-service-sapi

# Test
nx test finx-logger-service-sapi

# Run in development
nx serve finx-logger-service-sapi

# Clean build
nx clean finx-logger-service-sapi
```

### 2. Node.js Services

#### Available Node.js Services:
- `finx-accelerator-test-executions`

#### Commands:
```bash
# Install dependencies
nx install finx-accelerator-test-executions

# Build TypeScript
nx build finx-accelerator-test-executions

# Test with Jest
nx test finx-accelerator-test-executions

# Test in watch mode
nx test:watch finx-accelerator-test-executions

# Run development server
nx serve finx-accelerator-test-executions

# Run production
nx start finx-accelerator-test-executions

# Lint code
nx lint finx-accelerator-test-executions
```

### 3. React Web Applications

#### Available React Services:
- `finx-codegen-ui`

#### Commands:
```bash
# Install dependencies
nx install finx-codegen-ui

# Build for production
nx build finx-codegen-ui

# Test React components
nx test finx-codegen-ui

# Test in watch mode
nx test:watch finx-codegen-ui

# Run development server (usually http://localhost:3000)
nx serve finx-codegen-ui

# Preview production build
nx preview finx-codegen-ui

# Lint React code
nx lint finx-codegen-ui
```

### 4. Python Services

#### Available Python Services:
- `py-demo`

#### Commands:
```bash
# Install Python dependencies
nx install py-demo

# Build (prepare for deployment)
nx build py-demo

# Test with pytest
nx test py-demo

# Run FastAPI development server (usually http://localhost:8000)
nx serve py-demo

# Lint Python code
nx lint py-demo

# Format Python code
nx format py-demo
```

## Running All Services

### Build All Services:
```bash
nx run-many --target=build --all
```

### Test All Services:
```bash
nx run-many --target=test --all
```

### Install Dependencies for All:
```bash
nx run-many --target=install --all
```

### Run Specific Technology Stack:
```bash
# All Java services
nx run-many --target=build --projects=tag:lang:java

# All Node.js services  
nx run-many --target=build --projects=tag:lang:nodejs

# All React services
nx run-many --target=build --projects=tag:lang:react

# All Python services
nx run-many --target=build --projects=tag:lang:python
```

## Development Workflow

### 1. First Time Setup:
```bash
# Install all dependencies
nx run-many --target=install --all

# Build all services
nx run-many --target=build --all

# Run all tests
nx run-many --target=test --all
```

### 2. Daily Development:
```bash
# Start specific service in development
nx serve <service-name>

# Run tests for specific service
nx test <service-name>

# Build specific service
nx build <service-name>
```

### 3. Before Deployment:
```bash
# Build all for production
nx run-many --target=build --all

# Run all tests
nx run-many --target=test --all

# Lint all code
nx run-many --target=lint --all
```

## Port Information
- **Java Services**: Usually run on ports 8080, 8081, etc.
- **Node.js Services**: Usually run on ports 3000, 3001, etc.
- **React Services**: Usually run on port 3000
- **Python Services**: Usually run on port 8000

## Troubleshooting

### Common Issues:
1. **Dependencies not installed**: Run `nx install <service-name>`
2. **Build failures**: Check logs and install missing dependencies
3. **Port conflicts**: Stop other services or change ports
4. **TypeScript errors**: Run `nx build <service-name>` to compile

### Useful Debug Commands:
```bash
# Show project information
nx show project <service-name>

# Show dependency graph
nx graph

# Show what will be affected by changes
nx affected:build

# Clear Nx cache
nx reset
```