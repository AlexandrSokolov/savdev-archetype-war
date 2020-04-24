package ${package}.repository;

import liquibase.integration.cdi.CDILiquibaseConfig;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


import javax.sql.DataSource;

import java.sql.SQLException;

import static ${package}.repository.DbSchemaCreation.MASTER_CHANGE_LOG;

public class DbSchemaCreationTest {

  @Test
  public void testCreateConfig(){
    CDILiquibaseConfig liquibaseConfig = new DbSchemaCreation().createConfig();
    Assert.assertNotNull(liquibaseConfig);
    Assert.assertEquals(MASTER_CHANGE_LOG,
      liquibaseConfig.getChangeLog());
  }

  @Test
  public void testCreateDataSource() throws SQLException {
    DbSchemaCreation dbSchema = new DbSchemaCreation();
    DataSource dataSource = Mockito.mock(DataSource.class);
    dbSchema.myDataSource = dataSource;
    Assert.assertEquals(
      dataSource,
      dbSchema.createDataSource());
  }

  @Test
  public void testCreate() {
    Assert.assertNotNull(new DbSchemaCreation().create());
  }
}
