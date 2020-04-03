#[[
### Start of the temporal block. 
]]#

Please read it, remove unused generated code and this description itself

List of generated features:

1. [Jax Rs Support](#1-jax-rs-support)
2. [Database Support](#2-database-support)

#[[
##### 1 Jax Rs Support
]]#

Includes:
- Application Context Root (endpoint rest url)
- Jax Rs Configuration
- Jax Rs Service, without any methods
- Basic authentication

If this feature is not needed remove:

- Java `${package}.rest` package from `src/main/java`
- The `src/main/webapp/WEB-INF/jboss-web.xml` file
- The `src/main/webapp/WEB-INF/web.xml` file. It contains only authentication configuration.

#[[
##### 2 Database support
]]#

Includes:
- Maven dependency on liquibase
- scripts to create db schema using liquiabase
- Logic that triggers liquibase during application deployment

If this feature is not needed remove:
- Maven dependency on liquibase and snakeyaml (used by liquibase for yml format)
- Java `${package}.repository` package from `src/main/java`
- `src/main/resources/db` resource folder
- `src/main/resources/META-INF/persistence.xml`

#[[
### End of temporal block. 
]]#

#[[
#
]]#

#[[ ### ]]# ${projectDescription}


#[[
#### Build the project: 
]]#

`mvn clean install`

The built information is stored in the `build.properties` file 
inside of the `${artifactId}-${project.version}.war` package.

#[[
#### Code quality:
]]#

FindBug is triggered during compilation. 

You can view the found bugs via `mvn findbugs:gui`

You can also generate FindBugs reports via 'mvn site' 
and find the FindBugs and other reports in the `site` folder

#[[
#### TODO list. 

List of steps to implement the project
]]#
- 

