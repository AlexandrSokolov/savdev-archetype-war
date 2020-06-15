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
  sed -i 's/template-project-deps/\${rootArtifactId}-deps/g' {} +


# Fix aggregator pom
# main pom attributes
sed -i 's/<artifactId>\${artifactId}<\/artifactId>/<artifactId>\${artifactId}-aggregator<\/artifactId>/g' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i '/<groupId>\${groupId}<\/groupId>/,+1 d' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
sed -i '/<version>\${version}<\/version>/,+1 d' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml
# add packaging after artifact id line:
sed -i '/<artifactId>\${artifactId}-aggregator<\/artifactId>/ a \ \ <packaging>pom<\/packaging>' target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml

# Fix modules names
sed -i 's/name="template-project-/name="${rootArtifactId}-/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/id="template-project-/id="${rootArtifactId}-/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="template-project-/dir="/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
# change minus with underscore symbol:
sed -i 's/dir="domain-api/dir="domain_api/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="rest-api/dir="rest_api/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/dir="sql-datasource/dir="sql_datasource/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
# special fix to a final web war module
sed -i 's/dir="template-project/dir="front_end_war/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/name="template-project/name="${rootArtifactId}/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml
sed -i 's/id="template-project/id="${rootArtifactId}/g' target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml


echoInfo "Installing custom archetype into the local Maven repository"
cd target/generated-sources/archetype && mvn clean install

echoStatus "INSTALL SUCCESS"
echoSeparator
echoInfo "Run 'scripts/generateProjectCommandOutput.sh' to get command for project generation"




