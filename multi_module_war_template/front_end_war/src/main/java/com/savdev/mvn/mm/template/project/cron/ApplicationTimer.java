package com.savdev.mvn.mm.template.project.cron;

import com.savdev.mvn.mm.template.project.cron.core.CronService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class ApplicationTimer {

  //10:15 AM every day.
  static final String CRON_EXPRESSION = "0 15 10 * * *";

  static final String TIMER_NAME = "Template Project Timer";

  @Inject
  CronService cronService;

  @PostConstruct
  void createTimer() {
    cronService.createTimer(
      TIMER_NAME,
      CRON_EXPRESSION,
      () -> System.out.println(TIMER_NAME + " triggered"));
  }

}
