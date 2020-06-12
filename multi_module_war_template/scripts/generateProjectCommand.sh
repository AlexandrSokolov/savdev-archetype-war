#!/bin/bash

echo "Use the following command to generate project, from your own location
mvn archetype:generate \\
  -DarchetypeGroupId=com.savdev.mvn.archetype \\
  -DarchetypeArtifactId=savdev-war \\
  -DarchetypeVersion=1.0.0 \\
  -DgroupId=com.b.cs \\
  -DartifactId=integration \\
  -Dversion=1.0.0 \\
  -DinteractiveMode=false
  "
