package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;

import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.LOCAL_DATE_TIME;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.SYSTEM_ZONED_DATE_TIME_STR;
import static org.mockito.Mockito.verify;

public class LocalDateTimeSerializerTest {

  JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);
  ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

  @Test
  public void testSerialize() throws IOException {
    LocalDateTimeSerializer serializer = new LocalDateTimeSerializer();
    serializer.serialize(LOCAL_DATE_TIME, jsonGenerator, null);
    verify(jsonGenerator).writeString(argumentCaptor.capture());
    Assert.assertEquals(
      SYSTEM_ZONED_DATE_TIME_STR,
      argumentCaptor.getValue());
  }
}
