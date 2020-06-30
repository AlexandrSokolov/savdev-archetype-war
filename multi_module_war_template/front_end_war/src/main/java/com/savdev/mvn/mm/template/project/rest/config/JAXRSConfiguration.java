package com.savdev.mvn.mm.template.project.rest.config;

import com.google.common.collect.Sets;
import com.savdev.mvn.mm.template.project.rest.app.TimerRestService;
import com.savdev.mvn.mm.template.project.rest.exception.mappers.WebApplicationExceptionMapper;
import com.savdev.mvn.mm.template.project.rest.app.AppRestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath(JAXRSConfiguration.APPLICATION_PATH)
public class JAXRSConfiguration extends Application {

  //see src/main/webapp/WEB-INF/jboss-web.xml
  public static final String CONTEXT_ROOT = "/template/project/url";
  public static final String APPLICATION_PATH = "/rest";

  @Override
  public Set<Class<?>> getClasses() {
    return Sets.newHashSet(
      AppRestService.class,
      TimerRestService.class,
      ServerSideJacksonProvider.class,
      WebApplicationExceptionMapper.class
    );
  }
}
