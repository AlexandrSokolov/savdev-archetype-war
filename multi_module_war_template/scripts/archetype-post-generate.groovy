/**
 * CF : This script will be executed upon creating a project from this archetype.
 * https://maven.apache.org/archetype/maven-archetype-plugin/advanced-usage.html
 *
 *
 * This script:
 *  - deletes a <parent> element from the deps/pom.xml file
 *  - copies .gitignore file into the project
 *
 * This groovy script has access to the ArchetypeGenerationRequest object, accessible via 'request' variable
 */

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// the path where the project got generated
Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

// the properties available to the archetype
Properties properties = request.properties


/*******************************************************************************/
/*
  Removing:
    <parent>
        <artifactId>...</artifactId>
        <groupId>...s</groupId>
        <version>1.0.0</version>
    </parent>
    generated in deps/pom.xml
 */

Path depsPomXml = Paths.get(projectPath.toString(), "deps/pom.xml")

File file = depsPomXml.toFile();

def lines = file.readLines()


1.upto(5, { it -> lines.remove(3) } )

file.write(
  lines.join(System.lineSeparator()))

/*******************************************************************************/
// rename 'gitignore' into '.gitignore'

Path gitIgnore = Paths.get(projectPath.toString(), "gitignore")
Files.move(gitIgnore, gitIgnore.resolveSibling(".gitignore"))