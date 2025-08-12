# Root Files Description

This document describes each file and directory in the root of the Nx workspace.

## Configuration Files

### `nx.json`
Main Nx workspace configuration file that defines:
- Named inputs for build optimization
- Target defaults for build, test, and other tasks
- Affected command configuration
- Nx Cloud access token
- Plugin configurations

### `package.json`
Node.js package configuration containing:
- Project metadata (name, version)
- NPM scripts for common Nx commands
- Development dependencies including Nx core packages

### `package-lock.json`
Automatically generated file that locks dependency versions for consistent installs across environments.

## Gradle Files

### `build.gradle`
Root Gradle build script that defines:
- Project-wide build configurations
- Common dependencies and plugins
- Multi-project build settings

### `settings.gradle`
Gradle settings file that:
- Defines the root project name
- Includes subprojects from the apps directory
- Configures project structure

### `gradle.properties`
Contains Gradle-specific properties and JVM settings for the build process.

### `gradlew` / `gradlew.bat`
Gradle wrapper scripts that ensure consistent Gradle version usage:
- `gradlew` - Unix/Linux/macOS wrapper script
- `gradlew.bat` - Windows wrapper script

## Directories

### `.gradle/`
Gradle's working directory containing:
- Build cache and metadata
- Dependency resolution cache
- Execution history and file hashes

### `.nx/`
Nx workspace cache directory storing:
- Build artifacts and outputs
- Project graph information
- Task execution metadata

### `apps/`
Contains application projects:
- `finx-logger-service-sapi/` - Logger service application
- `finx-payload-transformer-service-sapi/` - Payload transformer service application

### `gradle/`
Contains Gradle wrapper configuration:
- `wrapper/` - Gradle wrapper JAR and properties

## Other Files

### `.gitignore`
Specifies files and directories that Git should ignore, including:
- Build outputs
- IDE files
- Temporary files
- Node modules and caches