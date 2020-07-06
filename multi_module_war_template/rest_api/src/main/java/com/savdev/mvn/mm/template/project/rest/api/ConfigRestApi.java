package com.savdev.mvn.mm.template.project.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static com.savdev.mvn.mm.template.project.rest.api.ConfigRestApi.SERVICE_REST_URL;

/**
 * Returns template.project.properties configuration
 */
@Path(SERVICE_REST_URL)
@Produces("text/plain")
public interface ConfigRestApi {

  String SERVICE_REST_URL = "/config";

  /**
   * Returns the current configuration file
   *
   * @return properties config
   */
  @GET
  Response config();
}
