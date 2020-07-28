package com.savdev.mvn.mm.template.project.rest.api;



import com.savdev.mvn.mm.template.project.rest.api.dto.JobDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.savdev.mvn.mm.template.project.rest.api.LongRunningJobRestApi.SERVICE_REST_URL;

@Path(SERVICE_REST_URL)
@Produces(MediaType.APPLICATION_JSON)
public interface LongRunningJobRestApi {

  String SERVICE_REST_URL = "/job";

  @POST
  JobDto triggerJob();
}
