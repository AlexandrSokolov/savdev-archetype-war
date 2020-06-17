##### Maven multi-module project template

This project is a template for a custom maven archetype generation and installation.

The project is a Maven multi-module project.

#### Requirements:

- `Groovy` is installed
- `sed` is installed
- this template application is installed into local Maven repository via `mvn clean install`

#### Usage:

To install custom Maven archetype locally, run: 

```./scripts/installArchetype.sh``` 

To generate project from the installed archetype, run:

```./scripts/generateProjectCommandOutput.sh```
