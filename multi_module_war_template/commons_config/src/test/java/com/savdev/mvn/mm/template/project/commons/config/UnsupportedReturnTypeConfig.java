package com.savdev.mvn.mm.template.project.commons.config;

import java.util.Collections;

import static com.savdev.mvn.mm.template.project.commons.config.TestPropertiesConfig.STRING_PROP_KEY1;

public interface UnsupportedReturnTypeConfig {

  @PropertyKey(STRING_PROP_KEY1)
  Collections unsupportedReturnType();
}
