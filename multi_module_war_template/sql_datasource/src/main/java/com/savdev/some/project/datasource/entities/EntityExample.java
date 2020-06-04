package com.savdev.some.project.datasource.entities;

import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.api.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "example")
public class EntityExample implements ExampleApi {

  public EntityExample(){}

  public EntityExample(final ExampleApi exampleApi){
    this.id = exampleApi.getId();
    this.name = exampleApi.getName();
    this.status = exampleApi.getStatus();

    this.smallestInt = exampleApi.getSmallestInt();
    this.smallInt = exampleApi.getSmallInt();
    this.number1 = exampleApi.getNumber1();
    this.number2 = exampleApi.getNumber2();
    this.bigNumber = exampleApi.getBigNumber();

    this.money = exampleApi.getMoney();
    this.floatField = exampleApi.getFloatField();
    this.doubleField = exampleApi.getDoubleField();
    this.decimalField = exampleApi.getDecimalField();

    this.instantField = exampleApi.getInstantField();
    this.offsetDateTime = exampleApi.getOffsetDateTime();
    this.zonedDateTime = exampleApi.getZonedDateTime();
    this.localDateTime = exampleApi.getLocalDateTime();
    this.localDate = exampleApi.getLocalDate();
    this.localTime = exampleApi.getLocalTime();

    this.javaDate = exampleApi.getJavaDate();
    this.javaOnlyDate = exampleApi.getJavaOnlyDate();
    this.sqlDate = exampleApi.getSqlDate();
    this.sqlTimestamp = exampleApi.getSqlTimestamp();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private Status status;

  //liquibase type: tinyint
  @Column(name="smallest_int")
  private short smallestInt;

  //liquibase type: smallint
  @Column(name="small_int")
  private short smallInt;

  //liquibase type: number
  private int number1;

  //liquibase type: int
  private int number2;

  //liquibase type: bigint
  @Column(name="big_number")
  private long bigNumber;

  //liquibase type: currency
  private BigDecimal money;

  @Column(name="float_field")
  private float floatField;

  @Column(name="double_field")
  private double doubleField;

  @Column(name="decimal_field")
  private BigDecimal decimalField;

  ///////   new java 8 date time api ///////////////////
  //liquibase: datetime
  @Column(name="instant_field")
  private Instant instantField;

  //liquibase: datetime
  @Column(name="offset_date_time")
  private OffsetDateTime offsetDateTime;

  //liquibase: datetime
  @Column(name="zoned_date_time")
  private ZonedDateTime zonedDateTime;

  //liquibase: datetime
  @Column(name="local_date_time")
  private LocalDateTime localDateTime;

  //liquibase: date
  @Column(name="local_date")
  private LocalDate localDate;

  //liquibase: time
  @Column(name="local_time")
  private LocalTime localTime;

  ///////   old java 8 date time api ///////////////////
  //liquibase: datetime
  @Column(name="java_date")
  private Date javaDate;

  //liquibase: date
  @Column(name="java_only_date")
  private Date javaOnlyDate;

  //liquibase: datetime
  @Column(name="sql_date")
  private java.sql.Date sqlDate;

  //liquibase: timestamp
  @Column(name="sql_timestamp")
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

  @Override
  public BigDecimal getDecimalField() {
    return decimalField;
  }

  @Override
  public void setDecimalField(BigDecimal decimalField) {
    this.decimalField = decimalField;
  }

  @Override
  public Instant getInstantField() {
    return instantField;
  }

  @Override
  public void setInstantField(Instant instantField) {
    this.instantField = instantField;
  }

  @Override
  public OffsetDateTime getOffsetDateTime() {
    return offsetDateTime;
  }

  @Override
  public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
    this.offsetDateTime = offsetDateTime;
  }

  @Override
  public ZonedDateTime getZonedDateTime() {
    return zonedDateTime;
  }

  @Override
  public void setZonedDateTime(ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
  }

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

  @Override
  public Date getJavaDate() {
    return javaDate != null ? (Date) javaDate.clone() : null;
  }

  @Override
  public void setJavaDate(Date javaDate) {
    this.javaDate = javaDate != null ? (Date) javaDate.clone() : null;
  }

  @Override
  public Date getJavaOnlyDate() {
    return javaOnlyDate != null ? (Date) javaOnlyDate.clone() : null;
  }

  @Override
  public void setJavaOnlyDate(Date javaOnlyDate) {
    this.javaOnlyDate = javaOnlyDate != null ? (Date) javaOnlyDate.clone() : null;
  }

  @Override
  public java.sql.Date getSqlDate() {
    return sqlDate != null ? (java.sql.Date) sqlDate.clone() : null;
  }

  @Override
  public void setSqlDate(java.sql.Date sqlDate) {
    this.sqlDate = sqlDate != null ? (java.sql.Date) sqlDate.clone() : null;
  }

  @Override
  public Timestamp getSqlTimestamp() {
    return sqlTimestamp != null ? (Timestamp) sqlTimestamp.clone() : null;
  }

  @Override
  public void setSqlTimestamp(Timestamp sqlTimestamp) {
    this.sqlTimestamp = sqlTimestamp != null ? (Timestamp) sqlTimestamp.clone() : null;
  }
}
