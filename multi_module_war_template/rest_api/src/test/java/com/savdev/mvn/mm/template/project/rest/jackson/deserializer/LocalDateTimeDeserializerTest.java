package com.savdev.mvn.mm.template.project.rest.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.CUSTOM_OFFSET_ZONED_DATE_TIME;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.CUSTOM_ZONED_DATE_TIME_STR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.LOCAL_DATE_TIME;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.SYSTEM_ZONED_DATE_TIME_STR;
import static org.mockito.Mockito.when;

public class LocalDateTimeDeserializerTest {

  LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer();
  JsonParser jsonParser = Mockito.mock(JsonParser.class);

  @Test
  public void testDeserializeSystem() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn(SYSTEM_ZONED_DATE_TIME_STR);
    Assert.assertEquals(
      LOCAL_DATE_TIME,
      deserializer.deserialize(jsonParser, null));

  }

  @Test
  public void testDeserializeCustom() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn(CUSTOM_ZONED_DATE_TIME_STR);
    Assert.assertEquals(
      CUSTOM_OFFSET_ZONED_DATE_TIME.toLocalDateTime(),
      deserializer.deserialize(jsonParser, null));
  }
}
