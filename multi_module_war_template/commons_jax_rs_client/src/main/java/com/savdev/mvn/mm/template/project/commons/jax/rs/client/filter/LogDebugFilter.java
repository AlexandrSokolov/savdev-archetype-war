package com.savdev.mvn.mm.template.project.commons.jax.rs.client.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class LogDebugFilter implements ClientResponseFilter {

  private final Log logger;
  private final ObjectMapper objectMapper;

  public static LogDebugFilter instance(
    final Class<?> clazz,
    final ObjectMapper objectMapper) {
    return new LogDebugFilter(clazz, objectMapper);
  }

  private LogDebugFilter(
    final Class<?> clazz,
    final ObjectMapper objectMapper) {
    this.logger = LogFactory.getLog(clazz);
    this.objectMapper = objectMapper;
  }

  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
    if (logger.isDebugEnabled()) {
      logger.debug(RequestResponseInfo.instance(
        objectMapper, requestContext, responseContext));
    }
  }
}
