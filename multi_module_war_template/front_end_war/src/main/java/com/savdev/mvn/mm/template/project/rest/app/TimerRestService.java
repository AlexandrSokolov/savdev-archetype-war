package com.savdev.mvn.mm.template.project.rest.app;

import com.savdev.mvn.mm.template.project.commons.cron.CronService;
import com.savdev.mvn.mm.template.project.commons.cron.Timer;
import com.savdev.mvn.mm.template.project.rest.api.TimerRestApi;

import javax.inject.Inject;
import java.util.List;

public class TimerRestService implements TimerRestApi {

  @Inject
  CronService cronService;

  @Override
  public List<Timer> timers() {
    return JaxRsHandlerUtils.handle(cronService::currentTimers);
  }
}
