package com.savdev.some.project.api;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public interface ExampleApi {

  long getId();

  void setId(long id);

  String getName();

  void setName(String name);

  short getSmallestInt();

  void setSmallestInt(short smallestInt);

  short getSmallInt();

  void setSmallInt(short smallInt);

  int getNumber1();

  void setNumber1(int number1);

  int getNumber2();

  void setNumber2(int number2);

  long getBigNumber();

  void setBigNumber(long bigNumber);

  //// decimal numbers:

  BigDecimal getMoney();

  void setMoney(BigDecimal money);

  float getFloatField();

  void setFloatField(float floatField);

  double getDoubleField();

  void setDoubleField(double doubleField);

  BigDecimal getDecimalField();

  void setDecimalField(BigDecimal decimalField);

  //// date and time:

  OffsetDateTime getOffsetDateTime();

  void setOffsetDateTime(OffsetDateTime offsetDateTime);

  ZonedDateTime getZonedDateTime();

  void setZonedDateTime(ZonedDateTime zonedDateTime);

  LocalDateTime getLocalDateTime();

  void setLocalDateTime(LocalDateTime localDateTime);

  LocalDate getLocalDate();

  void setLocalDate(LocalDate localDate);

  LocalTime getLocalTime();

  void setLocalTime(LocalTime localTime);

  Date getJavaDate();

  void setJavaDate(Date javaDate);

  Date getJavaOnlyDate();

  void setJavaOnlyDate(Date javaOnlyDate);

  java.sql.Date getSqlDate();

  void setSqlDate(java.sql.Date sqlDate);

  Timestamp getSqlTimestamp();

  void setSqlTimestamp(Timestamp sqlTimestamp);

}
