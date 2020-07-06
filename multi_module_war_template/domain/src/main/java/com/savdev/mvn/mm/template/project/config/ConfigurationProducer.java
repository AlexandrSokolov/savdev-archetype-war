package com.savdev.mvn.mm.template.project.config;

import com.savdev.mvn.mm.template.project.commons.config.Configs;

import javax.enterprise.inject.Produces;

import static com.savdev.mvn.mm.template.project.config.Configuration.CONFIG_FILE;
import static com.savdev.mvn.mm.template.project.config.Configuration.CONFIG_FOLDER_VARIABLE;

public class ConfigurationProducer {

  @Produces
  public Configuration produce(){
    return Configs.fileConfig(CONFIG_FOLDER_VARIABLE, CONFIG_FILE)
      .proxy(Configuration.class);
  }
}
