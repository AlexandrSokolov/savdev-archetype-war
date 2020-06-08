package com.savdev.some.project.rest.api;

import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.rest.api.dto.ExampleDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static com.savdev.some.project.rest.api.AppRestApi.SERVICE_REST_URL;

/**
 * Rest Application Service
 */
@Path(SERVICE_REST_URL)
@Produces(MediaType.APPLICATION_JSON)
public interface AppRestApi {

  String SERVICE_REST_URL = "/examples";

  @GET
  List<ExampleDto> items();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  ExampleDto add(ExampleDto item);

  @PUT
  @Path("{id : \\d+}") //support digit only
  @Consumes(MediaType.APPLICATION_JSON)
  ExampleDto update(@PathParam("id") long id, ExampleDto item);

  @DELETE
  @Path("{id : \\d+}")
  void delete(@PathParam("id") long id);
}
