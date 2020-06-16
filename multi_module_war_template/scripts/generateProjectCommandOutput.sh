#!/bin/bash

echo "Use the following command to generate project, from your own location
mvn archetype:generate \\
  -DarchetypeGroupId=com.savdev.mvn.mm.template.project \\
  -DarchetypeArtifactId=savdev-multi-module-archetype \\
  -DarchetypeVersion=1.0.0 \\
  -DgroupId=com.b.cs \\
  -DartifactId=integration \\
  -Dversion=1.0.0 \\
  -DprojectName=\"My Integration Project Name\" \\
  -DprojectShortName=\"integration-project\" \\
  -DprojectDescription=\"Here is my integration project description\" \\
  -DpersistenceUnitName=integration_pu \\
  -DjndiDatasourceName=\"java:/integration_db\" \\
  -DprojectUrl=\"/integration/url\" \\
  -DsecurityDomainName=IntegrationSecurityDomain \\
  -DsecurityRoleName=IntegrationRole \\
  -DinteractiveMode=false
  "
