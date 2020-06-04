package com.savdev.some.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Default LocalDateTime serialization very complex:
 *   "localTime" : {
 *     "hour" : 11,
 *     "minute" : 1,
 *     "second" : 0,
 *     "nano" : 0
 *   },
 */
public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

  @Override
  public void serialize(LocalTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeFormatterProvider.instance()
        .timeFormatter()
        .format(value));
  }
}
