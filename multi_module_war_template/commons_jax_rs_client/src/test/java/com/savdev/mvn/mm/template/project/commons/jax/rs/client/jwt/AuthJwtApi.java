package com.savdev.mvn.mm.template.project.commons.jax.rs.client.jwt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.mvn.mm.template.project.commons.jax.rs.client.jwt.AuthJwtApi.AUTH_JWT_PATH;

@Path(AUTH_JWT_PATH)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthJwtApi {

  String AUTH_JWT_PATH = "/path/test/jwt/auth/service";

  @GET
  AuthToken authenticate();
}
