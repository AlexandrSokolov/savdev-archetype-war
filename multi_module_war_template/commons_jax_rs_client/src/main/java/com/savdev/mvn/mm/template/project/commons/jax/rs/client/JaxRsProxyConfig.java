package com.savdev.mvn.mm.template.project.commons.jax.rs.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

public class JaxRsProxyConfig {

  private final String domain;

  private final ClientRequestFilter authFilter;

  private final JacksonJsonProvider jacksonJsonProvider;

  private final Collection<ClientResponseFilter> clientResponseFilters;

  public static JaxRsProxyConfigBuilder builder() {
    return new JaxRsProxyConfigBuilder();
  }

  JaxRsProxyConfig(
    final String domain,
    final ClientRequestFilter authFilter,
    final Collection<ClientResponseFilter> clientResponseFilters,
    final JacksonJsonProvider jacksonJsonProvider ) {
    this.domain = domain;
    this.authFilter = authFilter;
    this.jacksonJsonProvider = jacksonJsonProvider;
    this.clientResponseFilters = clientResponseFilters;
  }

  public <T> T proxy(Class<T> restProxy){

    try {
      Client client = ClientBuilder.newClient();
      client.register(jacksonJsonProvider);
      ResteasyWebTarget target = (ResteasyWebTarget) client.target(domain);

      if (authFilter != null) {
        target.register(authFilter);
      }
      if (clientResponseFilters != null){
        clientResponseFilters.forEach(target::register);
      }
      //proxy is returned, but not request is not sent yet
      return target.proxy(restProxy);
    } catch (Exception e){
      throw new IllegalStateException("Could not create proxy for the rest interface: '"
        + restProxy.getName() + "'. Reason: '" + e.getMessage()
        + "'. Exception type: " + e.getClass().getCanonicalName());
    }
  }

  public ObjectMapper objectMapper() {
    return this.jacksonJsonProvider.locateMapper(
      Object.class, MediaType.APPLICATION_JSON_TYPE);
  }
}
