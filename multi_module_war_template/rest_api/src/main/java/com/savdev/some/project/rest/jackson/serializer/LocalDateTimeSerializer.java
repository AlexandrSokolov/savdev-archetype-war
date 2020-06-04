package com.savdev.some.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.some.project.rest.jackson.DateTimeFormatterProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Default LocalDateTime serialization very complex:
 *   "localDateTime" : {
 *     "year" : 2020,
 *     "month" : "JUNE",
 *     "hour" : 11,
 *     "minute" : 1,
 *     "second" : 0,
 *     "nano" : 0,
 *     "dayOfYear" : 154,
 *     "dayOfWeek" : "TUESDAY",
 *     "dayOfMonth" : 2,
 *     "monthValue" : 6,
 *     "chronology" : {
 *       "calendarType" : "iso8601",
 *       "id" : "ISO"
 *     }
 *   }
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

  @Override
  public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeFormatterProvider.instance()
        .dateTimeFormatter()
        .format(value));
  }
}
