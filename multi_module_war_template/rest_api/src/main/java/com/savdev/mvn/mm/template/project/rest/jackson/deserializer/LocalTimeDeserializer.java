package com.savdev.mvn.mm.template.project.rest.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.savdev.mvn.mm.template.project.rest.jackson.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

  @Override
  public LocalTime deserialize(final JsonParser jsonParser,
                               final DeserializationContext deserializationContext) throws IOException {
    String dateTime = jsonParser.getValueAsString();

    return LocalDateTime.parse(
      dateTime,
      DateTimeUtils.instance().dateTimeFormatter())
      .atZone(ZoneId.systemDefault())
      .toLocalTime();
  }
}
