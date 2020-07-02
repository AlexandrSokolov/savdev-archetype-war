package com.savdev.mvn.mm.template.project.rest.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface DateTimeConstants {

  String NOT_ZONED_DATE_TIME_STR = "2011-12-24T10:15:30";
  String SYSTEM_ZONED_DATE_TIME_STR = NOT_ZONED_DATE_TIME_STR + DateTimeUtils.instance().systemOffset();
  String CUSTOM_ZONED_DATE_TIME_STR = NOT_ZONED_DATE_TIME_STR + "-04:00";

  LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(
    2011, Month.DECEMBER,  24, 10, 15, 30);
  ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.of(LOCAL_DATE_TIME, ZoneId.systemDefault());
  ZonedDateTime CUSTOM_OFFSET_ZONED_DATE_TIME = ZonedDateTime.parse(CUSTOM_ZONED_DATE_TIME_STR)
    .withZoneSameInstant(ZoneId.systemDefault());

  JsonGenerator JSON_GENERATOR = Mockito.mock(JsonGenerator.class);
  ArgumentCaptor<String> VALUE_CAPTURE = ArgumentCaptor.forClass(String.class);
  JsonParser JSON_PARSER = Mockito.mock(JsonParser.class);
}
