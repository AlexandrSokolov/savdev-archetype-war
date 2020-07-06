package com.savdev.mvn.mm.template.project.config;



import com.savdev.mvn.mm.template.project.commons.config.PropertyKey;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Configuration {

  String CONFIG_FOLDER_VARIABLE = "config.dir";
  String CONFIG_FILE = "template.project.properties";

  String PROPERTY_1 = "property1";
  String CRON_EXRESSION_PROPERTY = "cron";
  String NUMBER_PROPERTY = "number";
  String LIST_ITEMS = "list";
  String MAP_ITEMS = "map";

  @PropertyKey(PROPERTY_1)
  String property();

  @PropertyKey(CRON_EXRESSION_PROPERTY)
  Optional<String> cronExpression();

  @PropertyKey(NUMBER_PROPERTY)
  int numberProperty();

  @PropertyKey(LIST_ITEMS)
  List<String> list();

  @PropertyKey(MAP_ITEMS)
  Map<String, String> map();

}
