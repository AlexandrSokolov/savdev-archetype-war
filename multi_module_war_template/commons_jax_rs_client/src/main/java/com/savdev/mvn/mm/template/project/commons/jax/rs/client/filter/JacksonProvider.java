package com.savdev.mvn.mm.template.project.commons.jax.rs.client.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JacksonProvider extends JacksonJaxbJsonProvider {

  public JacksonProvider(final ObjectMapper mapper) {
    super();
    _mapperConfig.setMapper(mapper);
  }
}
