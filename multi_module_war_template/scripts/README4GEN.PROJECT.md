#[[### ]]#${projectName}

${projectDescription}

#[[
#### Build the project: 
]]#

`mvn clean install`

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

2. Application REST url
 
    `${deployed.application.host}${projectUrl}/rest`

    For instance: `http://localhost:18080${projectUrl}/rest`

3. Application REST Enunciate and Swagger documentation url
 
    `${deployed.application.host}${projectUrl}/docs`
                                    
    For instance: `http://localhost:18080${projectUrl}/docs`
    
#[[
#### Dev issues: 
]]#

For React developer, json server is configured to run on `http://localhost:3500`, see `react/web_server`
