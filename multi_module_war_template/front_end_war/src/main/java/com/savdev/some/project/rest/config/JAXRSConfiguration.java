package com.savdev.some.project.rest.config;

import com.google.common.collect.Sets;
import com.savdev.some.project.rest.app.AppRestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath(JAXRSConfiguration.APPLICATION_PATH)
public class JAXRSConfiguration extends Application {

  //see src/main/webapp/WEB-INF/jboss-web.xml
  public static final String CONTEXT_ROOT = "/some/project/url";
  public static final String APPLICATION_PATH = "/rest";

  @Override
  public Set<Class<?>> getClasses() {
    return Sets.newHashSet(
      AppRestService.class,
      ServerSideJacksonProvider.class);
  }
}
