package com.savdev.mvn.mm.template.project.commons.jax.rs.client.config;

import com.google.common.collect.Sets;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.service.AuthJwtService;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.service.ServiceWithAuthJwtCheck;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("")
public class JAXRSConfiguration extends Application {


  @Override
  public Set<Class<?>> getClasses() {
    return Sets.newHashSet(
      ServiceWithAuthJwtCheck.class,
      AuthJwtService.class,
      ServerSideJacksonProvider.class);
  }
}
