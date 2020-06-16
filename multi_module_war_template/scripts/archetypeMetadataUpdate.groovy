#!/usr/bin/env groovy

import groovy.xml.XmlUtil

// file path related to the Maven ${project.basedir}
// todo pass it via parameter
def archetypeDescriptorFile = "target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata.xml"

def archetypeDescriptor = new XmlParser().parse(archetypeDescriptorFile)

/////////////////// adding requiredProperty properties before fileSets
archetypeDescriptor.children().add(0, new NodeBuilder().requiredProperties {
  [
    'projectName',
    'projectDescription',
    'persistenceUnitName',
    'jndiDatasourceName']
    .each {
      requiredProperty(key: "${it}")
    }
})
///////////////////////////////////////////////////////////////////

def archetypeDescriptorUpdatedFile = "target/generated-sources/archetype/src/main/resources/META-INF/maven/archetype-metadata-updated.xml"
XmlUtil xmlUtil = new XmlUtil()
xmlUtil.serialize(
  archetypeDescriptor,
  new FileWriter(new File(archetypeDescriptorUpdatedFile)))