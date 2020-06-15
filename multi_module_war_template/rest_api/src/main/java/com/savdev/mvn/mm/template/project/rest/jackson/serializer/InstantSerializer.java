package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.mvn.mm.template.project.rest.jackson.DateTimeFormatterProvider;

import java.io.IOException;
import java.time.Instant;

/**
 * Default Instant serialiation looks like:
 *    "instantField":{"epochSecond":1591088460,"nano":0}
 */
public class InstantSerializer extends JsonSerializer<Instant> {

  @Override
  public void serialize(Instant value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeFormatterProvider.instance()
        .dateTimeFormatter()
        .format(value));
  }
}
