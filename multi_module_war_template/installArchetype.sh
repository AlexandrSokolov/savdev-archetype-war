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
mvn --settings .emptyMavenSettings.xml \
  archetype:create-from-project

echoInfo "Removing custom archetype test folder. It causes an error and is not needed."
rm -rf target/generated-sources/archetype/src/test
rm -rf target/generated-sources/archetype/target/test-classes

echoSeparator
echoInfo "Installing custom archetype into the local Maven repository"
cd target/generated-sources/archetype && mvn install

echoSeparator
echoStatus "INSTALL SUCCESS"
echoSeparator
echoInfo "To generate project run: "
echoInfo "   mvn clean install TODO"




