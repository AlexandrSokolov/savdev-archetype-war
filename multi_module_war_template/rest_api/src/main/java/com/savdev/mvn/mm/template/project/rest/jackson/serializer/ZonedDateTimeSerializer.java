package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.mvn.mm.template.project.rest.jackson.DateTimeFormatterProvider;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Default ZonedDateTime serialization very complex:
 *   "zonedDateTime" : {
 *     "offset" : {
 *       "totalSeconds" : 7200,
 *       "id" : "+02:00",
 *       "rules" : {
 *         "transitions" : [ ],
 *         "fixedOffset" : true,
 *         "transitionRules" : [ ]
 *       }
 *     },
 *     "zone" : {
 *       "id" : "Europe/Berlin", ...
 *     },
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
public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

  @Override
  public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeFormatterProvider.instance()
        .dateTimeFormatter()
        .format(value));
  }
}
