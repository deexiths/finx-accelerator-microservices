# Service Status Check Guide

## Quick Status Commands

### Check if services are running:
```bash
# Check payload transformer
curl http://localhost:8088/actuator/health
nx status finx-payload-transformer-service-sapi

# Check logger service  
curl http://localhost:8080/actuator/health
nx status finx-logger-service-sapi
```

### Get service information:
```bash
# Get payload transformer info
curl http://localhost:8088/actuator/info

# Get logger service info
curl http://localhost:8080/actuator/info
```

## Understanding the Output

### Gradle 80% EXECUTING Issue:
- This is normal Gradle behavior showing bootRun task progress
- The service is actually running and ready
- Use `--quiet` flag to reduce output: `gradlew.bat bootRun --quiet`

### Better Run Commands:
```bash
# Run with minimal output
nx run:verbose finx-payload-transformer-service-sapi

# Run with debug information
nx run:debug finx-payload-transformer-service-sapi

# Run and check status
nx serve:dev finx-payload-transformer-service-sapi
# In another terminal:
nx status finx-payload-transformer-service-sapi
```

## Manual Spring Boot Run Methods

### 1. Direct JAR execution (Recommended):
```bash
cd services/finx-payload-transformer-service-sapi
gradlew.bat build
java -Dspring.profiles.active=dev -jar build/libs/finx-payload-transformer-service-sapi.jar
```

### 2. Gradle bootRun with quiet output:
```bash
cd services/finx-payload-transformer-service-sapi
set SPRING_PROFILES_ACTIVE=dev
gradlew.bat bootRun --quiet
```

### 3. IDE Run Configuration:
- Main Class: `com.finx.PayloadTransformerApplication` (or similar)
- VM Options: `-Dspring.profiles.active=dev`
- Environment Variables: `SPRING_PROFILES_ACTIVE=dev`

## Service Endpoints

### Payload Transformer Service (Port 8088):
- Health: http://localhost:8088/actuator/health
- Info: http://localhost:8088/actuator/info  
- Metrics: http://localhost:8088/actuator/metrics
- H2 Console (dev): http://localhost:8088/h2-console

### Logger Service (Port 8080):
- Health: http://localhost:8080/actuator/health
- Info: http://localhost:8080/actuator/info
- Metrics: http://localhost:8080/actuator/metrics
- H2 Console (dev): http://localhost:8080/h2-console

## Troubleshooting

### If service won't start:
1. Check port availability: `netstat -an | findstr :8088`
2. Check logs: `nx logs finx-payload-transformer-service-sapi`
3. Verify profile: Look for "Active Profile" in startup banner
4. Check dependencies: `nx dependencies finx-payload-transformer-service-sapi`

### Profile Verification:
The custom banner will show:
```
:: Active Profile: dev :: Port: 8088
```

This confirms which profile is active and which port the service is using.