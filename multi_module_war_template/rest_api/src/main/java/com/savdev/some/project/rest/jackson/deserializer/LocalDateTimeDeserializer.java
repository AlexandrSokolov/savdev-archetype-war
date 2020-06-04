package com.savdev.some.project.rest.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.savdev.some.project.rest.jackson.DateTimeFormatterProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(final JsonParser jsonParser,
                               final DeserializationContext deserializationContext) throws IOException {
    String dateTime = jsonParser.getValueAsString();

    return LocalDateTime.parse(
      dateTime,
      DateTimeFormatterProvider.instance().dateTimeFormatter())
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();
  }
}
