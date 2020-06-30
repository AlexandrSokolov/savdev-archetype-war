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

#### Actions per each new module:

1. hange minus with underscore symbol in `installArchetype.sh`, for instance:

    ```
    sed -i 's/dir="domain-api/dir="domain_api/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml

    ```
    
    Otherwise you'll get an exception like:
    ```
    Error merging velocity templates: Unable to find resource 'archetype-resources/domain-api/pom.xml'
    ```

2. TODO