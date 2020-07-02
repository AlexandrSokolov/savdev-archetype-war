package com.savdev.mvn.mm.template.project.rest.jackson.deserializer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.CUSTOM_OFFSET_ZONED_DATE_TIME;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.CUSTOM_ZONED_DATE_TIME_STR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.JSON_PARSER;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.LOCAL_DATE_TIME;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.SYSTEM_ZONED_DATE_TIME_STR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.ZONED_DATE_TIME;
import static org.mockito.Mockito.when;

public class InstantDeserializerTest {

  InstantDeserializer deserializer = new InstantDeserializer();

  @Test
  public void testDeserializeSystem() throws IOException {
    when(JSON_PARSER.getValueAsString()).thenReturn(SYSTEM_ZONED_DATE_TIME_STR);
    Assert.assertEquals(
      ZONED_DATE_TIME.toInstant(),
      deserializer.deserialize(JSON_PARSER, null));

  }

  @Test
  public void testDeserializeCustom() throws IOException {
    when(JSON_PARSER.getValueAsString()).thenReturn(CUSTOM_ZONED_DATE_TIME_STR);
    Assert.assertEquals(
      CUSTOM_OFFSET_ZONED_DATE_TIME.toInstant(),
      deserializer.deserialize(JSON_PARSER, null));
  }
}
