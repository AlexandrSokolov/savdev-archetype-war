package com.savdev.mvn.mm.template.project.commons.jax.rs.client.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

public class TestObjectMapper extends ObjectMapper {

  public TestObjectMapper() {
    super();
    SimpleModule module = new SimpleModule();
    module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    this.registerModule(module);
  }
}
