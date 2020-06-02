package com.savdev.some.project.rest.jackson.serializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public enum  DateTimeFormatterProvider {

  INSTANCE;

  String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  String DATE_PATTERN = "yyyy-MM-dd";
  String TIME_PATTERN = "HH:mm:ss";


  public static DateTimeFormatterProvider instance(){
    return INSTANCE;
  }

  public DateTimeFormatter dateTimeFormatter(){
    return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)
        .withLocale( Locale.getDefault() )
        .withZone( ZoneId.systemDefault() );
  }

  public DateTimeFormatter dateFormatter(){
    return DateTimeFormatter.ofPattern(DATE_PATTERN)
      .withLocale( Locale.getDefault() )
      .withZone( ZoneId.systemDefault() );

  }

  public DateTimeFormatter timeFormatter(){
    return DateTimeFormatter.ofPattern(TIME_PATTERN)
      .withLocale( Locale.getDefault() )
      .withZone( ZoneId.systemDefault() );

  }

  public DateFormat dateFormat(){
    DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
    dateFormat.setTimeZone(TimeZone.getDefault());
    return dateFormat;
  }
}
