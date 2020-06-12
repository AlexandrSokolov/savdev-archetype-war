##### Maven multi-module project template

This project is a template for a custom maven archetype generation and installation.

The project is a Maven multi-module project.

#### Usage:

To install custom Maven archetype locally, run: 

```./installArchetype.sh``` 

To generate project from the installed archetype, run:

```
mvn TODO
```

`installArchetype.sh` script performs:
1. generates a custom archetype
2. updates certain values in the generated files with archetype variables
3. installs the updated custom archetype locally


------------------------------------------------

Some Project - visible name
some.project - package
some-project - artifact name
some_project_jndi_db_name
some_project_pu_name
SecurityDomainName, SecurityRole
"name": "some-project",

/some/project/url
{ "/some/project/url/rest/*": "/$1" }

<title>Some Project Web Application</title>
  <description package="com.webcohesion.enunciate.sample"/>
  <copyright>savdev.com</copyright>
  <contact email="alex@some.com" name="Some Project Rest API" />

<application path="/some/project/url/rest"/>

docs:
http://localhost:18080/some/project/url/enunciate/docs/json_ExampleDto.html
file:///home/alexandr/projects/maven/template-war/multi_module_war_template/rest_api/target/enunciate/docs/index.html

```
    <swagger
      basePath="/some/project/url/rest"
      disabled="false">
    </swagger>
```

yyyy-MM-dd'T'HH:mm:ss'Z'

standalone configuration:
security rdomain name: 
<security-domain name="SecurityDomainName"
SecurityRole in web.xml

datasource configuration:


insert into example values(1, 'some name', 'CREATED', 1, 22, 333, 4444, 5555555555, 100932.02, 25.3, 35.2, 42343.43253423, now(), now(), now(), now(), now(), now(), now(), now(), now(), now(), 'another name value');