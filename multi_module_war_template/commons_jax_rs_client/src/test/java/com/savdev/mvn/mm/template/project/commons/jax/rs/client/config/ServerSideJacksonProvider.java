package com.savdev.mvn.mm.template.project.commons.jax.rs.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jackson.TestObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerSideJacksonProvider implements ContextResolver<ObjectMapper> {

  private final ObjectMapper objectMapper = new TestObjectMapper();

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return objectMapper;
  }

}
