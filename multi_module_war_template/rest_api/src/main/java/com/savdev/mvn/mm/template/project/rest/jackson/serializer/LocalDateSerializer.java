package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.mvn.mm.template.project.rest.jackson.DateTimeFormatterProvider;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Default LocalDateTime serialization very complex:
 *   "localDate" : {
 *     "year" : 2020,
 *     "month" : "JUNE",
 *     "chronology" : {
 *       "calendarType" : "iso8601",
 *       "id" : "ISO"
 *     },
 *     "era" : "CE",
 *     "dayOfYear" : 154,
 *     "dayOfWeek" : "TUESDAY",
 *     "leapYear" : true,
 *     "dayOfMonth" : 2,
 *     "monthValue" : 6
 *   },
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

  @Override
  public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeFormatterProvider.instance()
        .dateFormatter()
        .format(value));
  }
}
