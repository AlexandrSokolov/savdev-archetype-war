package com.savdev.some.project.rest.app;

import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.app.ApplicationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static com.savdev.some.project.rest.app.AppRestService.SERVICE_REST_URL;

@Path(SERVICE_REST_URL)
@Produces(MediaType.APPLICATION_JSON)
public class AppRestService {

  public static final String SERVICE_REST_URL = "/examples";

  @Inject
  ApplicationService application;

  @GET
  public List<ExampleApi> items() {
    return application.items();
  }
}
