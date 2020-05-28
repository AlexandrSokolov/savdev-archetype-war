#### TODO list:
- base test to get String, InputStream, json
- react support
- codestyle plugin (the last one)
- split archetype to:
  rest-war
  rest-db-war
  react-rest-db-war
  react-rest-war
- maven-javadoc-plugin what is it, from org.apache.maven.plugins
- maven-project-info-reports-plugin, from org.apache.maven.plugins
- exlcude certain java classes and packages from analyses of findbug, update docs
- create parent-child project, 
  to configure everything in the parent, 
  and only small things in child

To be able to generate your projects from this custom archetype, you need:

#### 1. To install the custom archetype into your local environment:

`mvn clean install`

#### 2. The archetype properties without default values

You need to prepare the following property values, before you build your own project:

```
  -DgroupId=com.b.cs \
  -DartifactId=integration \
  -Dversion=1.0.0 \
  -DprojectDescription="This project is created for integration between subsystems" \
  -DgitRepositoryUrl=scm:git:git@gitlab.dev.b.c:si/customer/Amazon/Amazon_hsa-fpi-fix.git \
  -DjiraUrl=https://jira4.b.c/confluence/display/SI/04+-+Integrations \
  -Dbm-product-bom=6.5.0-SNAPSHOT \
  
```

#### 3. The archetype properties with default values
```
    <requiredProperty key="appContextRoot">
      <defaultValue>/cs</defaultValue>
    </requiredProperty>
    <requiredProperty key="persistenceUnitName">
      <defaultValue>cs_pu_name</defaultValue>
    </requiredProperty>
    <requiredProperty key="jtaDataSource">
      <defaultValue>java:/cs_db</defaultValue>
    </requiredProperty>
```

#### 4. The command, you can use to build your project, based on this archetype

```
mvn archetype:generate \
  -DarchetypeGroupId=com.savdev.mvn.archetype \
  -DarchetypeArtifactId=savdev-war \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.b.cs \
  -DartifactId=integration \
  -Dversion=1.0.0 \
  -DprojectDescription="This project is created for integration between subsystems" \
  -DgitRepositoryUrl=scm:git:git@gitlab.dev.b.c:si/customer/Amazon/Amazon_hsa-fpi-fix.git \
  -DjiraUrl=https://jira4.b.c/confluence/display/SI/04+-+Integrations \
  -Dbm-product-bom=6.7.0-SNAPSHOT
```

#### 5. The command, you can use to build your project, to overwrite all archetype properties
```
mvn archetype:generate \
  -DarchetypeGroupId=com.savdev.mvn.archetype \
  -DarchetypeArtifactId=savdev-war \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.b.cs \
  -DartifactId=integration \
  -Dversion=1.0.0 \
  -DprojectDescription="This project is created for integration between subsystems" \
  -DgitRepositoryUrl=scm:git:git@gitlab.dev.b.c:si/customer/Amazon/Amazon_hsa-fpi-fix.git \
  -DjiraUrl=https://jira4.b.c/confluence/display/SI/04+-+Integrations \
  -Dbm-product-bom=6.7.0-SNAPSHOT \
  -DcompanyUrl=http://www.b.c/de.html \
  -DcompanyName="BrandMaker GmbH" \
  -DsecurityDomain=BrandMaker \
  -DrepositoryId=b-release-repository \
  -DrepositoryUrl=https://nexus.b.c/content/repositories/releases \
  -DbomGroupId=com.b.product \
  -DbomArtifactId=bm-product-bom \
  -DdistributionRepositoryId=b-release-repository \
  -DdistributionRepositoryUrl=https://nexus.b.c/content/repositories/releases \
  -DdistributionSnapshotRepositoryId=b-snapshot-repository \
  -DdistributionSnapshotRepositoryUrl=https://nexus.b.c/content/repositories/snapshots \
  -DauthRoleName=PIMEDIA_ORGANISATION_MODULE_ACCESS
  
```

#### 6. Properties that should be removed, value might be statically defined:

```
  -DcompanyUrl=http://www.b.c/de.html \
  -DcompanyName="BrandMaker GmbH" \
  -DsecurityDomain=BrandMaker \
  -DrepositoryId=b-release-repository \
  -DrepositoryUrl=https://nexus.b.c/content/repositories/releases \
  -DbomGroupId=com.b.product \
  -DbomArtifactId=bm-product-bom \
  -DdistributionRepositoryId=b-release-repository \
  -DdistributionRepositoryUrl=https://nexus.b.c/content/repositories/releases \
  -DdistributionSnapshotRepositoryId=b-snapshot-repository \
  -DdistributionSnapshotRepositoryUrl=https://nexus.b.c/content/repositories/snapshots \
  -DauthRoleName=PIMEDIA_ORGANISATION_MODULE_ACCESS \
  
```
