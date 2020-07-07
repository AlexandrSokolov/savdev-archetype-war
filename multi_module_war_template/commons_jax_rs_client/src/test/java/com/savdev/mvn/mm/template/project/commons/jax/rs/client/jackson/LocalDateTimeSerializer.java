package com.savdev.mvn.mm.template.project.commons.jax.rs.client.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

  @Override
  public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeFormatter.ISO_OFFSET_DATE_TIME
        .withLocale( Locale.getDefault() )
        .withZone( ZoneId.systemDefault() )
        .format(ZonedDateTime.of(value, ZoneId.systemDefault())));
  }
}
