package com.savdev.mvn.mm.template.project.commons.config;

import static com.savdev.mvn.mm.template.project.commons.config.TestPropertiesConfig.NOT_EXISTING_PROP_KEY;

public interface NotExistingStringConfig {

  @PropertyKey(NOT_EXISTING_PROP_KEY)
  String notExistingProperty();
}
