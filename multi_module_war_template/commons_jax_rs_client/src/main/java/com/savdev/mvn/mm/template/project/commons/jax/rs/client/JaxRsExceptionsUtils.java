package com.savdev.mvn.mm.template.project.commons.jax.rs.client;

import com.savdev.mvn.mm.template.project.commons.jax.rs.client.filter.RequestResponseInfo;

import javax.ws.rs.WebApplicationException;

public class JaxRsExceptionsUtils {

  public static String extractErrorFromResponse(final Exception e){
    if (e instanceof WebApplicationException){
      WebApplicationException we = (WebApplicationException) e;
      return RequestResponseInfo.errorRequestResponseInfo(we.getResponse())
        .orElseThrow(() -> new IllegalStateException("Not nullable instance of RequestResponseInfo is expected."));
    } else if (e.getCause() != null
      //to avoid stackoverflow:
      && e != e.getCause().getCause()){
      return extractErrorFromResponse((Exception) e.getCause());
    } else {
      return e.getMessage();
    }
  }
}
