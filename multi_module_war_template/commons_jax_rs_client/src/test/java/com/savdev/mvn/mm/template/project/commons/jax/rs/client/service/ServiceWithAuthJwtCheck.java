package com.savdev.mvn.mm.template.project.commons.jax.rs.client.service;


import com.savdev.mvn.mm.template.project.commons.jax.rs.client.api.RestDto;
import com.savdev.mvn.mm.template.project.commons.jax.rs.client.api.ServiceApi;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.savdev.mvn.mm.template.project.commons.jax.rs.client.filter.AuthClientRequestFilterFactory.BEARER_PREFIX;

public class ServiceWithAuthJwtCheck implements ServiceApi {

  static final String TEST_NAME = "Auth Jwt testName";
  static final BigDecimal TEST_BD = new BigDecimal("358.298");
  static final LocalDateTime TEST_DATE = LocalDateTime.now();

  public static final RestDto JWT_INSTANCE = RestDto.instance(TEST_NAME, TEST_DATE, TEST_BD);

  @Context
  HttpHeaders headers;

  @Override
  public RestDto getDto() {
    checkJwtAuthentication(headers);
    return JWT_INSTANCE;
  }

  private void checkJwtAuthentication(HttpHeaders headers) {
    if (headers.getRequestHeader(HttpHeaders.AUTHORIZATION) == null
      || headers.getRequestHeader(HttpHeaders.AUTHORIZATION).isEmpty()) {
      throw new IllegalStateException("Not authenticated");
    } else {
      String authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
      authHeader = authHeader.startsWith(BEARER_PREFIX)
        ? authHeader.substring(BEARER_PREFIX.length())
        : authHeader;

      if (!authHeader.equals(AuthJwtService.JWT_AUTH_TOKEN.getJwtToken())) {
        throw new IllegalStateException("Not authenticated");
      }
    }
  }
}
