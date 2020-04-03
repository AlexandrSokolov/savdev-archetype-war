package ${package}.repository;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DbSchemaCreation {

  @Resource(lookup = "${jtaDataSource}")
  private DataSource myDataSource;

  @Produces
  @LiquibaseType
  public CDILiquibaseConfig createConfig() {
    CDILiquibaseConfig config = new CDILiquibaseConfig();
    config.setChangeLog("db/changelog/db.changelog-master.yml");
    return config;
  }

  @Produces
  @LiquibaseType
  public DataSource createDataSource() throws SQLException {
    return myDataSource;
  }

  @Produces
  @LiquibaseType
  public ResourceAccessor create() {
    return new ClassLoaderResourceAccessor(getClass().getClassLoader());
  }
}
