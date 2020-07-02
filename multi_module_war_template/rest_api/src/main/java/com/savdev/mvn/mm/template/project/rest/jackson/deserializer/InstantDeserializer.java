package com.savdev.mvn.mm.template.project.rest.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantDeserializer extends JsonDeserializer<Instant> {

  @Override
  public Instant deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {

    String dateTime = jsonParser.getValueAsString();

    return ZonedDateTime.parse(dateTime)
      .withZoneSameInstant(ZoneId.systemDefault())
      .toInstant();
  }
}
