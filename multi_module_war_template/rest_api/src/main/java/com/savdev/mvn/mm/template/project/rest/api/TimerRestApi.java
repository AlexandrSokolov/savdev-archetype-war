package com.savdev.mvn.mm.template.project.rest.api;

import com.savdev.mvn.mm.template.project.cron.core.Timer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.savdev.mvn.mm.template.project.rest.api.TimerRestApi.SERVICE_REST_URL;

/**
 * Rest Service to display all currently configured timers
 */
@Path(SERVICE_REST_URL)
@Produces(MediaType.APPLICATION_JSON)
public interface TimerRestApi {

  String SERVICE_REST_URL = "/timers";

  @GET
  List<Timer> timers();
}
