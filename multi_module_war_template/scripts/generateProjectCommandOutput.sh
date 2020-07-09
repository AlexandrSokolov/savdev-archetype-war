#!/bin/bash

echo "Use the following command to generate project, from your own location
mvn archetype:generate \\
  -DarchetypeGroupId=com.savdev.mvn.mm.template.project \\
  -DarchetypeArtifactId=savdev-multi-module-archetype \\
  -DarchetypeVersion=1.0.0 \\
  -DgroupId=com.b.cs \\
  -DartifactId=my-integration \\
  -Dversion=1.0.0 \\
  -DprojectName=\"My Integration Project Name\" \\
  -DprojectShortName=\"integration-project\" \\
  -DprojectDescription=\"Here is my integration project description\" \\
  -DcompanyName=\"My Company Name\" \\
  -DcompanyUrl=some.company.com \\
  -DcompanyEmail=alex@gmail.com \\
  -DgitUrl=git@github.com:MyLogin/my-integration.git \\
  -DprojectDescriptionUrl=\"jira.some.company.com/integration\" \\
  -DnexusRepositoryId=savdev-release-repository \\
  -DnexusUrl4Publishing=\"https://nexus.savdev.com/content/repositories/releases\" \\
  -DpersistenceUnitName=integration_pu \\
  -DjndiDatasourceName=\"java:/integration_db\" \\
  -DprojectUrl=\"/integration/url\" \\
  -DconfigDirVariable=INTEGRATION_CONFIG_DIR \\
  -DpropertiesFileName=integration.properties \\
  -DsecurityDomainName=IntegrationSecurityDomain \\
  -DsecurityRoleName=IntegrationRole \\
  -DinteractiveMode=false
  "
