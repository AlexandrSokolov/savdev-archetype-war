package ${package}.rest;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class JAXRSConfigurationTest {

  @Test
  public void testGetClasses(){
    Set<Class<?>> classes = new JAXRSConfiguration().getClasses();
    Assert.assertNotNull(classes);
    Assert.assertEquals(1, classes.size());
    Assert.assertEquals(
      AppRestService.class,
      Lists.newArrayList(classes).get(0));
  }
}