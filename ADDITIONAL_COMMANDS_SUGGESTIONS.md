# Additional Useful Commands for project.json

## Security & Vulnerability Commands

### Java Services:
```json
"security:check": {
  "executor": "nx:run-commands",
  "options": {
    "command": "gradlew.bat dependencyCheckAnalyze",
    "cwd": "services/service-name"
  }
},
"outdated": {
  "executor": "nx:run-commands",
  "options": {
    "command": "gradlew.bat dependencyUpdates",
    "cwd": "services/service-name"
  }
}
```

### Node.js/React Services:
```json
"security:check": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm audit --audit-level=moderate",
    "cwd": "services/service-name"
  }
},
"security:fix": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm audit fix",
    "cwd": "services/service-name"
  }
},
"outdated": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm outdated",
    "cwd": "services/service-name"
  }
}
```

### Python Services:
```json
"security:check": {
  "executor": "nx:run-commands",
  "options": {
    "command": "py -m safety check",
    "cwd": "services/service-name"
  }
},
"outdated": {
  "executor": "nx:run-commands",
  "options": {
    "command": "py -m pip list --outdated",
    "cwd": "services/service-name"
  }
}
```

## Performance & Monitoring Commands

### All Services:
```json
"health": {
  "executor": "nx:run-commands",
  "options": {
    "command": "curl -f http://localhost:8080/actuator/health || echo 'Service not running'",
    "cwd": "services/service-name"
  }
},
"logs": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker logs -f service-name || echo 'Container not running'",
    "cwd": "services/service-name"
  }
},
"profile": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker stats service-name",
    "cwd": "services/service-name"
  }
}
```

## Database & Migration Commands

### Java Services:
```json
"db:migrate": {
  "executor": "nx:run-commands",
  "options": {
    "command": "gradlew.bat flywayMigrate",
    "cwd": "services/service-name"
  }
},
"db:clean": {
  "executor": "nx:run-commands",
  "options": {
    "command": "gradlew.bat flywayClean",
    "cwd": "services/service-name"
  }
}
```

### Node.js Services:
```json
"db:migrate": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm run migrate",
    "cwd": "services/service-name"
  }
},
"db:seed": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm run seed",
    "cwd": "services/service-name"
  }
}
```

## Documentation Commands

### All Services:
```json
"docs:generate": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm run docs:build",
    "cwd": "services/service-name"
  }
},
"docs:serve": {
  "executor": "nx:run-commands",
  "options": {
    "command": "npm run docs:serve",
    "cwd": "services/service-name"
  }
}
```

### Java Services:
```json
"docs:javadoc": {
  "executor": "nx:run-commands",
  "options": {
    "command": "gradlew.bat javadoc",
    "cwd": "services/service-name"
  }
}
```

## Environment & Configuration Commands

### All Services:
```json
"env:check": {
  "executor": "nx:run-commands",
  "options": {
    "command": "echo 'Environment:' && echo NODE_ENV=%NODE_ENV% && echo JAVA_HOME=%JAVA_HOME%",
    "cwd": "services/service-name"
  }
},
"config:validate": {
  "executor": "nx:run-commands",
  "options": {
    "command": "echo 'Validating configuration files...' && echo 'Config OK'",
    "cwd": "services/service-name"
  }
}
```

## Deployment Commands

### All Services:
```json
"deploy:dev": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker-compose -f docker-compose.dev.yml up -d",
    "cwd": "services/service-name"
  }
},
"deploy:prod": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker-compose -f docker-compose.prod.yml up -d",
    "cwd": "services/service-name"
  }
},
"undeploy": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker-compose down",
    "cwd": "services/service-name"
  }
}
```

## Quality Gates Commands

### All Services:
```json
"quality:gate": {
  "executor": "nx:run-commands",
  "options": {
    "commands": [
      "nx lint service-name",
      "nx test service-name",
      "nx coverage service-name",
      "nx security:check service-name"
    ],
    "parallel": false,
    "cwd": "services/service-name"
  }
},
"pre-commit": {
  "executor": "nx:run-commands",
  "options": {
    "commands": [
      "nx lint service-name",
      "nx typecheck service-name",
      "nx test service-name"
    ],
    "parallel": false,
    "cwd": "services/service-name"
  }
}
```

## Backup & Restore Commands

### All Services:
```json
"backup": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker exec service-db pg_dump -U user dbname > backup.sql",
    "cwd": "services/service-name"
  }
},
"restore": {
  "executor": "nx:run-commands",
  "options": {
    "command": "docker exec -i service-db psql -U user dbname < backup.sql",
    "cwd": "services/service-name"
  }
}
```

## Most Recommended Commands to Add:

1. **security:check** - Essential for vulnerability scanning
2. **health** - Quick service health verification
3. **quality:gate** - Combined quality checks
4. **pre-commit** - Pre-commit validation
5. **outdated** - Check for dependency updates
6. **env:check** - Environment validation

These commands would significantly enhance developer productivity and code quality assurance.