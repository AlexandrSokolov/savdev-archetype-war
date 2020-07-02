package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.JSON_GENERATOR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.SYSTEM_ZONED_DATE_TIME_STR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.VALUE_CAPTURE;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.ZONED_DATE_TIME;
import static org.mockito.Mockito.verify;

public class InstantSerializerTest {

  @Test
  public void testSerialize() throws IOException {
    InstantSerializer serializer = new InstantSerializer();
    serializer.serialize(ZONED_DATE_TIME.toInstant(), JSON_GENERATOR, null);
    verify(JSON_GENERATOR).writeString(VALUE_CAPTURE.capture());
    Assert.assertEquals(
      SYSTEM_ZONED_DATE_TIME_STR,
      VALUE_CAPTURE.getValue());
  }
}
