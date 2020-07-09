#!/bin/bash

echoSeparator () {
  echoInfo "\e[1m------------------------------------------------------------------------"
}

echoInfo () {
  echo -e "[\e[01;34mINFO\e[m] $1"
}

echoStatus () {
  echo -e "[\e[01;34mINFO\e[m] \e[01;34m$1\e[m"
}

echoInfo "To be able to install the archetype, all modules must be installed into the Maven local repository"
echoInfo "To do that, run 'mvn clean install' first, before running this script"
echoInfo "You can delete all the installed artifacts manually with 'rm -rf ~/.m2/repository/com/savdev/mvn' command"

echoSeparator
echoInfo "Cleaning generated files"
echoSeparator

mvn clean

echoInfo "Removing react files, generated during build process, not needed for the archetype"

rm -rf front_end_war/react/node_modules

rm -rf front_end_war/react/build

rm -rf front_end_war/react/package-lock.json

echoSeparator
echoInfo "Creating custom archetype"
echoSeparator

# using --settings option to avoid:
# The specified user settings file does not exist: ~/.m2/settings.xml
mvn --settings scripts/emptyMavenSettings.xml \
  archetype:create-from-project &&

echoInfo "Removing custom archetype test folder. It causes an error and is not needed."
rm -rf target/generated-sources/archetype/src/test
rm -rf target/generated-sources/archetype/target/test-classes

echoSeparator

# Fix archetype pom
sed -i 's/template-project-aggregator-archetype/savdev-multi-module-archetype/g' target/generated-sources/archetype/pom.xml

# Fix artifactId for all modules in the parent section:
find target/generated-sources/archetype/ -name pom.xml -exec \
  sed -i 's/template-project-deps/${rootArtifactId}-deps/g' {} +
#################################
# fix group id, do not change the order:
# the next 2 groupId fixes depend on the order. first we delete groupId, cause it inherits from the parent
# then we correct groupId for the parent
sed -i '/<groupId>${groupId}<\/groupId>/,+1 d' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i 's/<groupId>com.savdev.mvn.mm.template.project<\/groupId>/<groupId>${groupId}<\/groupId>/g' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
find target/generated-sources/archetype/src -name pom.xml -exec \
  sed -i 's/<groupId>com.savdev.mvn.mm.template.project<\/groupId>/<groupId>${groupId}<\/groupId>/g' {} +
#################################
find target/generated-sources/archetype/src -name pom.xml -exec \
  sed -i 's/<artifactId>template-project-/<artifactId>${rootArtifactId}-/g' {} +

######################## FIX /archetype-metadata.xml
# Fix modules names
sed -i 's/name="template-project-/name="${rootArtifactId}-/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/id="template-project-/id="${rootArtifactId}-/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="template-project-/dir="/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
# change minus with underscore symbol:
sed -i 's/dir="domain-api/dir="domain_api/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="rest-api/dir="rest_api/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="sql-datasource/dir="sql_datasource/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="commons-cron/dir="commons_cron/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="commons-config/dir="commons_config/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="commons-jax-rs-client/dir="commons_jax_rs_client/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
# special fix to a final web war module
sed -i 's/dir="template-project/dir="front_end_war/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/name="template-project/name="${rootArtifactId}/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/id="template-project/id="${rootArtifactId}/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
# add new properties to the archetype-metadata.xml
chmod u+x scripts/archetypeMetadataUpdate.groovy
./scripts/archetypeMetadataUpdate.groovy
mv target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata-updated.xml target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml

######################## FIX AGGREGATOR POM
# main pom attributes
sed -i 's/<artifactId>${artifactId}<\/artifactId>/<artifactId>${artifactId}-aggregator<\/artifactId>/g' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i '/<version>${version}<\/version>/,+1 d' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
# add packaging after artifact id line:
sed -i '/<artifactId>${artifactId}-aggregator<\/artifactId>/ a \ \ <packaging>pom<\/packaging>' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
# <description>Project Description</description> -> <description>${projectDescription}</description>
sed -i 's/<description>Project Description<\/description>/<description>${projectDescription}<\/description>/g' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
# git@github.com:AlexandrSokolov/savdev-archetype-war.git -> ${gitUrl}
sed -i 's/git@github.com\:AlexandrSokolov\/savdev-archetype-war\.git/${gitUrl}/g' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
# https://github.com/AlexandrSokolov/savdev-archetype-war/wiki -> ${projectDescriptionUrl}
sed -i 's/https\:\/\/github\.com\/AlexandrSokolov\/savdev-archetype-war\/wiki/${projectDescriptionUrl}/g' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml

################################################################################################

mv target/generated-sources/archetype/src/main/resources/archetype-resources/config/template.project.properties target/generated-sources/archetype/src/main/resources/archetype-resources/config/project.properties

######################## START requiredProperties for all

# Template Project -> ${projectName}
find target/generated-sources/archetype/src -name pom.xml -exec \
  sed -i 's/<name>Template Project/<name>${projectName}/g' {} +
find target/generated-sources/archetype/src -name enunciate.xml -exec \
  sed -i 's/<title>Template Project/<title>${projectName}/g' {} +
find target/generated-sources/archetype/src -name enunciate.xml -exec \
  sed -i 's/name="Template Project/name="${projectName}/g' {} +
find target/generated-sources/archetype/src -name App.js -exec \
  sed -i 's/applicationHeader = "Template Project"/applicationHeader = "${projectName}"/g' {} +
find target/generated-sources/archetype/src -name ApplicationTimer.java -exec \
  sed -i 's/Template Project Timer/${projectName} Timer/g' {} +
# template-project -> ${projectShortName}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/template-project/${projectShortName}/g' {} +
# template_project_pu_name -> ${persistenceUnitName}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/template_project_pu_name/${persistenceUnitName}/g' {} +
# java:/template_project_jndi_db_name -> ${jndiDatasourceName}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/java\:\/template_project_jndi_db_name/${jndiDatasourceName}/g' {} +
# /template/project/url -> ${projectUrl}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/\/template\/project\/url/${projectUrl}/g' {} +
# SecurityDomainName -> ${securityDomainName}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/SecurityDomainName/${securityDomainName}/g' {} +
# SecurityRole -> ${securityRoleName}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/SecurityRole/${securityRoleName}/g' {} +
# <name>Sav Dev</name> -> <name>${companyName}</name>
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/<name>Sav Dev<\/name>/<name>${companyName}<\/name>/g' {} +
# <copyright>savdev.com</copyright> -> <copyright>${companyUrl}</copyright>
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/<copyright>savdev.com<\/copyright>/<copyright>${companyUrl}<\/copyright>/g' {} +
# <url>http://www.savdev.com</url> -> <url>http://${companyUrl}</url>
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/<url>http:\/\/www\.savdev\.com<\/url>/<url>http:\/\/${companyUrl}<\/url>/g' {} +
# email="a@savdev.com" -> email="${companyEmail}"
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/email="a@savdev.com"/email="${companyEmail}"/g' {} +
# CONFIG_FOLDER_VARIABLE = "config.dir" -> CONFIG_FOLDER_VARIABLE = "${configDirVariable}"
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/CONFIG_FOLDER_VARIABLE = "config.dir"/CONFIG_FOLDER_VARIABLE = "${configDirVariable}"/g' {} +
# CONFIG_FILE = "template.project.properties" -> CONFIG_FILE = "${propertiesFileName}"
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/CONFIG_FILE = "template.project.properties"/CONFIG_FILE = "${propertiesFileName}"/g' {} +
# template.project.properties -> ${propertiesFileName}
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/template.project.properties/${propertiesFileName}/g' {} +
# <repositoryId>someRepositoryId</repositoryId> -> <repositoryId>${nexusRepositoryId}</repositoryId>
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/<repositoryId>someRepositoryId<\/repositoryId>/<repositoryId>${nexusRepositoryId}<\/repositoryId>/g' {} +
# <url>https://nexus.savdev.com/content/repositories/releases</url> -> <url>${nexusUrl4Publishing}</url>
find target/generated-sources/archetype/src -type f -exec \
  sed -i 's/<url>https:\/\/nexus\.savdev\.com\/content\/repositories\/releases<\/url>/<url>${nexusUrl4Publishing}<\/url>/g' {} +
################################################################################################

//special workaround, 'gitignore' will be renamed to '.gitignore' when a real project is generated from the archetype
cp .gitignore target/generated-sources/archetype/src/main/resources/archetype-resources/gitignore
cp target/generated-sources/archetype/src/main/resources/archetype-resources/scripts/README4GEN.PROJECT.md target/generated-sources/archetype/src/main/resources/archetype-resources/README.md
sed -i '/#set/,+0 d' target/generated-sources/archetype/src/main/resources/archetype-resources/scripts/archetype-post-generate.groovy
cp target/generated-sources/archetype/src/main/resources/archetype-resources/scripts/archetype-post-generate.groovy target/generated-sources/archetype/src/main/resources/META-INF/
rm -rf target/generated-sources/archetype/src/main/resources/archetype-resources/scripts

################################################################################################

echoInfo "Installing custom archetype into the local Maven repository"
cd target/generated-sources/archetype && mvn clean install

echoStatus "INSTALL SUCCESS"
echoSeparator
echoInfo "Run './scripts/generateProjectCommandOutput.sh' to get command for project generation"




