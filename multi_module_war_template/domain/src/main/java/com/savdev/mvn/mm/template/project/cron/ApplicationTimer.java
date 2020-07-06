package com.savdev.mvn.mm.template.project.cron;

import com.savdev.mvn.mm.template.project.commons.cron.CronService;
import com.savdev.mvn.mm.template.project.config.Configuration;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class ApplicationTimer {

  static final String TIMER_NAME = "Template Project Timer";

  @Inject
  Configuration configuration;

  @Inject
  CronService cronService;

  @PostConstruct
  void createTimer() {
    configuration.cronExpression().ifPresent(cronExpression ->
      cronService.createTimer(
        TIMER_NAME,
        cronExpression,
        () -> System.out.println(TIMER_NAME + " triggered")));
  }

}
