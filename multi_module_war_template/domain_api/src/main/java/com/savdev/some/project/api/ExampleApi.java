package com.savdev.some.project.api;

import java.math.BigDecimal;

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

  BigDecimal getMoney();

  void setMoney(BigDecimal money);

  float getFloatField();

  void setFloatField(float floatField);

  double getDoubleField();

  void setDoubleField(double doubleField);

  BigDecimal getDecimalField();

  void setDecimalField(BigDecimal decimalField);
}
