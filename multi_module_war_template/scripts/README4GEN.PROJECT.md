#[[### ]]# post generation steps in case some functions are not needed:

You do not need
1. React:

    - remove `frontend-maven-plugin` entirely
    - in the `maven-war-plugin` configuration remove related to React:
      ```
      <resource>
        <directory>${basedir}/react/build</directory>
        <targetPath>/</targetPath>
      </resource>
      ```
    - remove `front_end_war/react` folder
    - remove `skip-react-build` Maven profile

2. Sql datasource:

    - remove `sql_datasource` module itself and from the parent
    - correct `domain` module to be able to compile it

3. Security context (rest services will be available without authentication):

    - Remove the line from `front_end_war/src/main/webapp/WEB-INF/jboss-web.xml`:
      ```
      <security-domain flushOnSessionInvalidation="true">...</security-domain>
      ```
      
    - Remove from the /home/alexandr/projects/maven/tmp/my-integration/front_end_war/src/main/webapp/WEB-INF/web.xml` 
      all security-related tags

4. 

Note: if you have built the project before you cleared any functionality, 
clear your local Maven repository to avoid these artifacts usage. 

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
