package com.savdev.some.project.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Provider
public class ServerSideJacksonProvider implements ContextResolver<ObjectMapper> {

  private ObjectMapper mapper;

  public ServerSideJacksonProvider() {
    this.mapper = createObjectMapper();
  }

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return mapper;
  }

  private ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getDefault());
    mapper.setDateFormat(dateFormat);
    return mapper;
  }
}
