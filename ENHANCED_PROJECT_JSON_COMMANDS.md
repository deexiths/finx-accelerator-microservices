# Enhanced project.json Commands for Developer Efficiency

## Additional Useful Commands for project.json

### 1. Java Services Enhanced Commands

```json
{
  "targets": {
    "clean": {
      "executor": "nx:run-commands",
      "options": {
        "command": "./gradlew clean",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "dependencies": {
      "executor": "nx:run-commands",
      "options": {
        "command": "./gradlew dependencies",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "bootRun": {
      "executor": "nx:run-commands",
      "options": {
        "command": "./gradlew bootRun",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "docker:build": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build -t finx-logger-service .",
        "cwd": "services/finx-logger-service-sapi"
      }
    },
    "docker:run": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker run -p 8080:8080 finx-logger-service",
        "cwd": "services/finx-logger-service-sapi"
      }
    }
  }
}
```

### 2. Node.js Services Enhanced Commands

```json
{
  "targets": {
    "clean": {
      "executor": "nx:run-commands",
      "options": {
        "command": "rm -rf dist node_modules package-lock.json",
        "cwd": "services/finx-accelerator-test-executions"
      }
    },
    "fresh-install": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm ci",
        "cwd": "services/finx-accelerator-test-executions"
      }
    },
    "debug": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm run start:debug",
        "cwd": "services/finx-accelerator-test-executions"
      }
    },
    "typecheck": {
      "executor": "nx:run-commands",
      "options": {
        "command": "tsc --noEmit",
        "cwd": "services/finx-accelerator-test-executions"
      }
    },
    "docker:build": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build -t finx-test-executions .",
        "cwd": "services/finx-accelerator-test-executions"
      }
    },
    "audit": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm audit",
        "cwd": "services/finx-accelerator-test-executions"
      }
    }
  }
}
```

### 3. React Applications Enhanced Commands

```json
{
  "targets": {
    "clean": {
      "executor": "nx:run-commands",
      "options": {
        "command": "rm -rf build node_modules package-lock.json",
        "cwd": "services/finx-codegen-ui"
      }
    },
    "analyze": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm run build && npx webpack-bundle-analyzer build/static/js/*.js",
        "cwd": "services/finx-codegen-ui"
      }
    },
    "storybook": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm run storybook",
        "cwd": "services/finx-codegen-ui"
      }
    },
    "e2e": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm run test:e2e",
        "cwd": "services/finx-codegen-ui"
      }
    },
    "docker:build": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build -t finx-codegen-ui .",
        "cwd": "services/finx-codegen-ui"
      }
    },
    "lighthouse": {
      "executor": "nx:run-commands",
      "options": {
        "command": "npm run lighthouse",
        "cwd": "services/finx-codegen-ui"
      }
    }
  }
}
```

### 4. Python Services Enhanced Commands

```json
{
  "targets": {
    "clean": {
      "executor": "nx:run-commands",
      "options": {
        "command": "rm -rf __pycache__ .pytest_cache dist build *.egg-info",
        "cwd": "services/py-demo"
      }
    },
    "install:dev": {
      "executor": "nx:run-commands",
      "options": {
        "command": "py -m pip install -e .[dev]",
        "cwd": "services/py-demo"
      }
    },
    "coverage": {
      "executor": "nx:run-commands",
      "options": {
        "command": "py -m pytest --cov=py_demo --cov-report=html",
        "cwd": "services/py-demo"
      }
    },
    "mypy": {
      "executor": "nx:run-commands",
      "options": {
        "command": "py -m mypy py_demo",
        "cwd": "services/py-demo"
      }
    },
    "docker:build": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker build -t py-demo .",
        "cwd": "services/py-demo"
      }
    },
    "requirements": {
      "executor": "nx:run-commands",
      "options": {
        "command": "py -m pip freeze > requirements.txt",
        "cwd": "services/py-demo"
      }
    }
  }
}
```

## Universal Commands for All Services

### Development Efficiency Commands

```json
{
  "targets": {
    "health": {
      "executor": "nx:run-commands",
      "options": {
        "command": "curl -f http://localhost:8080/health || echo 'Service not running'",
        "cwd": "services/service-name"
      }
    },
    "logs": {
      "executor": "nx:run-commands",
      "options": {
        "command": "docker logs -f service-name",
        "cwd": "services/service-name"
      }
    },
    "restart": {
      "executor": "nx:run-commands",
      "options": {
        "commands": [
          "nx stop service-name",
          "nx serve service-name"
        ],
        "parallel": false,
        "cwd": "services/service-name"
      }
    },
    "env:check": {
      "executor": "nx:run-commands",
      "options": {
        "command": "echo 'Environment variables:' && env | grep -E '^(NODE_ENV|JAVA_HOME|PYTHON_PATH)'",
        "cwd": "services/service-name"
      }
    }
  }
}
```

## Usage Examples

### Run enhanced commands:
```bash
# Clean and rebuild
nx clean service-name && nx build service-name

# Check dependencies
nx dependencies service-name

# Run with debugging
nx debug service-name

# Build Docker image
nx docker:build service-name

# Check service health
nx health service-name

# Analyze bundle size (React)
nx analyze finx-codegen-ui

# Run type checking (TypeScript)
nx typecheck finx-accelerator-test-executions

# Generate coverage report (Python)
nx coverage py-demo
```

## Benefits of Enhanced Commands

1. **Development Speed**: Quick clean, rebuild, debug workflows
2. **Quality Assurance**: Type checking, linting, coverage reports
3. **Docker Integration**: Build and run containers easily
4. **Health Monitoring**: Check service status and logs
5. **Dependency Management**: Audit, update, and analyze dependencies
6. **Performance Analysis**: Bundle analysis, lighthouse scores
7. **Environment Validation**: Check configurations and variables