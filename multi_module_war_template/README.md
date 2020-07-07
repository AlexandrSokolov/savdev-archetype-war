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

#### Customisations:

1. Change minus symbol with underscore symbol in `installArchetype.sh`, for instance:

    ```
    sed -i 's/dir="domain-api/dir="domain_api/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml

    ```
    
    Otherwise you'll get an exception like:
    ```
    Error merging velocity templates: Unable to find resource 'archetype-resources/domain-api/pom.xml'
    ```

2. Add a new variable:

    Keep in mind, that a variable name must be written in camel case. 
    You can for instance define a name as `someComplicatedVariable`, 
    but not as `some.complicated.variable`
    
    To add a variable:
    
    - add a new variable into `archetypeMetadataUpdate.groovy`
    
    - add its usage via changing the current value in this project with a variable. 
      See `installArchetype.sh`
      
    - add a variable usage in `generateProjectCommandOutput.sh`

3. Rename a file, which depends on a variable value, when a new project is generated

    A variable value is known when a new project is generated, 
    so you can do it only via `scripts/archetype-post-generate.groovy` script. 
    For instance:
    
    ```
    Path projectPropertiesPath = Paths.get(projectPath.toString(), "config/project.properties")
    Files.move(projectPropertiesPath, 
      projectPropertiesPath.resolveSibling((String) properties.get("propertiesFileName")))
    ```

