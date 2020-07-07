package com.savdev.mvn.mm.template.project.commons.jax.rs.client.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.mvn.mm.template.project.commons.jax.rs.client.api.ServiceApi.CLIENT_API_PATH;

@Path(CLIENT_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface ServiceApi {

  String CLIENT_API_PATH = "/rest/test/path/test/jwt/client";

  @GET
  RestDto getDto();
}
