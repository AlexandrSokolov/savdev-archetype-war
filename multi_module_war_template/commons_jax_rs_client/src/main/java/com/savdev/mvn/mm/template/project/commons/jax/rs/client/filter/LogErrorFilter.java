package com.savdev.mvn.mm.template.project.commons.jax.rs.client.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;

public class LogErrorFilter implements ClientResponseFilter {

  private final Log logger;
  private final ObjectMapper objectMapper;

  public static LogErrorFilter instance(
    final Class<?> clazz,
    final ObjectMapper objectMapper) {
    return new LogErrorFilter(clazz, objectMapper);
  }

  private LogErrorFilter(
    final Class<?> clazz,
    final ObjectMapper objectMapper) {
    this.logger = LogFactory.getLog(clazz);
    this.objectMapper = objectMapper;
  }

  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
    if (Sets.newHashSet(
      Response.Status.Family.CLIENT_ERROR,
      Response.Status.Family.SERVER_ERROR)
      .contains(Response.Status.Family.familyOf(responseContext.getStatus()))) {
      RequestResponseInfo requestResponseInfo = RequestResponseInfo.instance(
        objectMapper, requestContext, responseContext);
      logger.error(requestResponseInfo);

      responseContext
        .getHeaders()
        .putSingle(
          RequestResponseInfo.ERROR_HEADER,
          requestResponseInfo.toString());
    }
  }
}
