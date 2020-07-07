package com.savdev.mvn.mm.template.project.commons.jax.rs.client.service;

import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jwt.AuthJwtApi;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.jwt.AuthToken;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthJwtService implements AuthJwtApi {

  public static final String JWT_LOGIN = "testJwtLogin";
  public static final String JWT_PASSWORD = "testJwtPassword";

  static final String AUTH_BASIC_PREFIX = "Basic ";

  static final AuthToken JWT_AUTH_TOKEN = AuthToken.instance("someGenaratedToken");

  @Context
  HttpHeaders headers;

  @Override
  public AuthToken authenticate() {
    checkBasicAuthentication(headers);
    return JWT_AUTH_TOKEN;
  }

  private void checkBasicAuthentication(final HttpHeaders headers) {
    try {
      if (headers.getRequestHeader(HttpHeaders.AUTHORIZATION) == null
        || headers.getRequestHeader(HttpHeaders.AUTHORIZATION).isEmpty()) {
        throw new IllegalStateException("Not authenticated via jwt");
      } else {
        String authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
        authHeader = authHeader.startsWith(AUTH_BASIC_PREFIX)
          ? authHeader.substring(AUTH_BASIC_PREFIX.length())
          : authHeader;

        if (!authHeader.equals(
          Base64.getEncoder().encodeToString(
            String.format("%s:%s", JWT_LOGIN, JWT_PASSWORD)
              .getBytes(StandardCharsets.UTF_8.name())))) {
          throw new IllegalStateException("Not authenticated via jwt");
        }
      }
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }
}
