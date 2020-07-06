package com.savdev.mvn.mm.template.project.commons.config;

public interface ConfigFactory {

  <T> T proxy(Class<T> configInterface);

}
