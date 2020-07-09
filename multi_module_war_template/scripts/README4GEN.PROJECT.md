#[[### ]]#${projectName}

${projectDescription}

#[[
#### Build the project: 
]]#

To build and apply all code quality checks, run:  `mvn clean install`

To speed build up without code quality checks, run: `mvn clean package`

To skip React code build, run: `mvn clean package -P skip-react-build`

**To skip React code build it is required at least once build it with one of the previous commands**

To skip tests, run Maven with: `-DskipTests=true` parameter. For instance:

`mvn clean package -P skip-react-build -DskipTests=true`

or as:

`mvn clean package -DskipTests=true`

#[[
#### Requirements: 
]]#
- datasource configuration with `${jndiDatasourceName}` jndi name

#[[
#### Usage: 
]]#

1. Application GUI url

    `${deployed.application.host}${projectUrl}`

    For instance: `http://localhost:18080${projectUrl}`

2. Enunciate and Swagger documentation on supported REST services url:
 
    `${deployed.application.host}${projectUrl}/docs`
                                    
    For instance: `http://localhost:18080${projectUrl}/docs`
    
#[[
#### Dev issues: 
]]#

For React developer, json server is configured to run on `http://localhost:3500`, see `react/web_server`
