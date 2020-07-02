package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.JSON_GENERATOR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.LOCAL_DATE_TIME;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.SYSTEM_ZONED_DATE_TIME_STR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.VALUE_CAPTURE;
import static org.mockito.Mockito.verify;

public class LocalDateTimeSerializerTest {

  @Test
  public void testSerialize() throws IOException {
    LocalDateTimeSerializer serializer = new LocalDateTimeSerializer();
    serializer.serialize(LOCAL_DATE_TIME, JSON_GENERATOR, null);
    verify(JSON_GENERATOR).writeString(VALUE_CAPTURE.capture());
    Assert.assertEquals(
      SYSTEM_ZONED_DATE_TIME_STR,
      VALUE_CAPTURE.getValue());
  }
}
