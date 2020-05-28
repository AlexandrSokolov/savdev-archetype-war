package com.savdev.some.project.datasource.entities;

import com.savdev.some.project.api.ExampleApi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
