package ${package}.rest;

import org.junit.Assert;
import org.junit.Test;

public class AppRestServiceTest {

  @Test
  public void testHello(){
    Assert.assertEquals("hello", new AppRestService().hello());
  }
}
