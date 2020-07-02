package com.savdev.mvn.mm.template.project.rest.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;

import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.SYSTEM_ZONED_DATE_TIME_STR;
import static com.savdev.mvn.mm.template.project.rest.jackson.DateTimeConstants.ZONED_DATE_TIME;
import static org.mockito.Mockito.verify;

public class InstantSerializerTest {

  JsonGenerator jsonGenerator = Mockito.mock(JsonGenerator.class);
  ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

  @Test
  public void testSerialize() throws IOException {
    InstantSerializer serializer = new InstantSerializer();
    serializer.serialize(ZONED_DATE_TIME.toInstant(), jsonGenerator, null);
    verify(jsonGenerator).writeString(argumentCaptor.capture());
    Assert.assertEquals(
      SYSTEM_ZONED_DATE_TIME_STR,
      argumentCaptor.getValue());
  }
}
