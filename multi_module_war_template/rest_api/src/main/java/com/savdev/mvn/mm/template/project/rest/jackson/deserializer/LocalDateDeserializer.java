package com.savdev.mvn.mm.template.project.rest.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.savdev.mvn.mm.template.project.rest.jackson.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

  @Override
  public LocalDate deserialize(final JsonParser jsonParser,
                               final DeserializationContext deserializationContext) throws IOException {

    return LocalDate.parse(
      jsonParser.getValueAsString(),
      DateTimeUtils.instance().dateFormatter());
  }

}
