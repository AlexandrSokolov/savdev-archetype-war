package ${package}.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static ${package}.rest.AppRestService.SERVICE_REST;

@Path(SERVICE_REST)
@Produces(MediaType.TEXT_HTML)
public class AppRestService {

  public static final String SERVICE_REST = "";

  @GET
  public String hello(){
    return "hello";
  }
}