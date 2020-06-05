package com.savdev.some.project.rest.api.dto;

import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.api.Status;
import com.webcohesion.enunciate.metadata.DocumentationExample;
import com.webcohesion.enunciate.metadata.rs.TypeHint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static com.savdev.some.project.rest.documentation.DocumentationExamples.DATETIME_VALUE;
import static com.savdev.some.project.rest.documentation.DocumentationExamples.MONEY_VALUE;

/**
 * Rest Dto object for example item
 */
public class ExampleDto implements ExampleApi {

  private long id;

  private String name;

  private Status status;

  private short smallestInt;

  private short smallInt;

  private int number1;

  private int number2;

  private long bigNumber;

  private BigDecimal money;

  private float floatField;

  private double doubleField;

  private BigDecimal decimalField;

  private Instant instantField;

  private OffsetDateTime offsetDateTime;

  private ZonedDateTime zonedDateTime;

  private LocalDateTime localDateTime;

  private LocalDate localDate;

  private LocalTime localTime;

  private Date javaDate;

  private Date javaOnlyDate;

  private java.sql.Date sqlDate;

  private Timestamp sqlTimestamp;

  @Override
  public long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Status getStatus() {
    return status;
  }

  @Override
  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public short getSmallestInt() {
    return smallestInt;
  }

  @Override
  public void setSmallestInt(short smallestInt) {
    this.smallestInt = smallestInt;
  }

  @Override
  public short getSmallInt() {
    return smallInt;
  }

  @Override
  public void setSmallInt(short smallInt) {
    this.smallInt = smallInt;
  }

  @Override
  public int getNumber1() {
    return number1;
  }

  @Override
  public void setNumber1(int number1) {
    this.number1 = number1;
  }

  @Override
  public int getNumber2() {
    return number2;
  }

  @Override
  public void setNumber2(int number2) {
    this.number2 = number2;
  }

  @Override
  public long getBigNumber() {
    return bigNumber;
  }

  @Override
  public void setBigNumber(long bigNumber) {
    this.bigNumber = bigNumber;
  }

  @DocumentationExample(value = MONEY_VALUE, type=@TypeHint(BigDecimal.class))
  @Override
  public BigDecimal getMoney() {
    return money;
  }

  @Override
  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  @Override
  public float getFloatField() {
    return floatField;
  }

  @Override
  public void setFloatField(float floatField) {
    this.floatField = floatField;
  }

  @Override
  public double getDoubleField() {
    return doubleField;
  }

  @Override
  public void setDoubleField(double doubleField) {
    this.doubleField = doubleField;
  }

  @DocumentationExample(value = "2345.7578", type=@TypeHint(BigDecimal.class))
  @Override
  public BigDecimal getDecimalField() {
    return decimalField;
  }

  @Override
  public void setDecimalField(BigDecimal decimalField) {
    this.decimalField = decimalField;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(Instant.class))
  @Override
  public Instant getInstantField() {
    return instantField;
  }

  @Override
  public void setInstantField(Instant instantField) {
    this.instantField = instantField;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(OffsetDateTime.class) )
  @Override
  public OffsetDateTime getOffsetDateTime() {
    return offsetDateTime;
  }

  @Override
  public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
    this.offsetDateTime = offsetDateTime;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(ZonedDateTime.class) )
  @Override
  public ZonedDateTime getZonedDateTime() {
    return zonedDateTime;
  }

  @Override
  public void setZonedDateTime(ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(LocalDateTime.class) )
  @Override
  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  @Override
  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  @Override
  public LocalDate getLocalDate() {
    return localDate;
  }

  @Override
  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  @Override
  public LocalTime getLocalTime() {
    return localTime;
  }

  @Override
  public void setLocalTime(LocalTime localTime) {
    this.localTime = localTime;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(Date.class) )
  @Override
  public Date getJavaDate() {
    return javaDate != null ? (Date) javaDate.clone() : null;
  }

  @Override
  public void setJavaDate(Date javaDate) {
    this.javaDate = javaDate != null ? (Date) javaDate.clone() : null;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(Date.class) )
  @Override
  public Date getJavaOnlyDate() {
    return javaOnlyDate != null ? (Date) javaOnlyDate.clone() : null;
  }

  @Override
  public void setJavaOnlyDate(Date javaOnlyDate) {
    this.javaOnlyDate = javaOnlyDate != null ? (Date) javaOnlyDate.clone() : null;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(java.sql.Date.class) )
  @Override
  public java.sql.Date getSqlDate() {
    return sqlDate != null ? (java.sql.Date) sqlDate.clone() : null;
  }

  @Override
  public void setSqlDate(java.sql.Date sqlDate) {
    this.sqlDate = sqlDate != null ? (java.sql.Date) sqlDate.clone() : null;
  }

  @DocumentationExample(value=DATETIME_VALUE, type=@TypeHint(Timestamp.class) )
  @Override
  public Timestamp getSqlTimestamp() {
    return sqlTimestamp != null ? (Timestamp) sqlTimestamp.clone() : null;
  }

  @Override
  public void setSqlTimestamp(Timestamp sqlTimestamp) {
    this.sqlTimestamp = sqlTimestamp != null ? (Timestamp) sqlTimestamp.clone() : null;
  }
}
