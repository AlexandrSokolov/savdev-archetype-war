package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.savdev.mvn.mm.template.project.rest.jackson.DateTimeUtils;

import java.io.IOException;
import java.time.OffsetDateTime;

/**
 * Default OffsetDateTime serialization:
 *   "offsetDateTime" : {
 *     "offset" : {
 *       "totalSeconds" : 7200,
 *       "id" : "+02:00",
 *       "rules" : {
 *         "transitions" : [ ],
 *         "fixedOffset" : true,
 *         "transitionRules" : [ ]
 *       }
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
 *     "monthValue" : 6
 *   }
 */
public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

  @Override
  public void serialize(OffsetDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeString(
      DateTimeUtils.instance()
        .dateTimeFormatter()
        .format(value));
  }
}
