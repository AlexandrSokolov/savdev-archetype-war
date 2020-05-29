package com.savdev.some.project.datasource.entities;

import com.savdev.some.project.api.ExampleApi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "example")
public class EntityExample implements ExampleApi {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String name;

  //liquibase type: tinyint
  private short smallestInt;

  //liquibase type: smallint
  private short smallInt;

  //liquibase type: number
  private int number1;

  //liquibase type: int
  private int number2;

  //liquibase type: bigint
  private long bigNumber;

  //liquibase type: currency
  private BigDecimal money;

  private float floatField;

  private double doubleField;

  private BigDecimal decimalField;

  ///////   new java 8 date time api ///////////////////
  //liquibase: datetime
  private OffsetDateTime offsetDateTime;

  //liquibase: datetime
  private ZonedDateTime zonedDateTime;

  //liquibase: datetime
  private LocalDateTime localDateTime;

  //liquibase: date
  private LocalDate localDate;

  //liquibase: time
  private LocalTime localTime;

  ///////   old java 8 date time api ///////////////////
  //liquibase: datetime
  private Date javaDate;

  //liquibase: date
  private Date javaOnlyDate;

  //liquibase: datetime
  private java.sql.Date sqlDate;

  //liquibase: timestamp
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
    return javaDate;
  }

  @Override
  public void setJavaDate(Date javaDate) {
    this.javaDate = javaDate;
  }

  @Override
  public Date getJavaOnlyDate() {
    return javaOnlyDate;
  }

  @Override
  public void setJavaOnlyDate(Date javaOnlyDate) {
    this.javaOnlyDate = javaOnlyDate;
  }

  @Override
  public java.sql.Date getSqlDate() {
    return sqlDate;
  }

  @Override
  public void setSqlDate(java.sql.Date sqlDate) {
    this.sqlDate = sqlDate;
  }

  @Override
  public Timestamp getSqlTimestamp() {
    return sqlTimestamp;
  }

  @Override
  public void setSqlTimestamp(Timestamp sqlTimestamp) {
    this.sqlTimestamp = sqlTimestamp;
  }
}
