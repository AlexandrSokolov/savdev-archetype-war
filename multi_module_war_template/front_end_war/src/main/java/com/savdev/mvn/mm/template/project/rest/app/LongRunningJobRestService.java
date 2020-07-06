package com.savdev.mvn.mm.template.project.rest.app;

import com.savdev.mvn.mm.template.project.app.LongRunningJobsService;
import com.savdev.mvn.mm.template.project.rest.api.LongRunningJobRestApi;
import com.savdev.mvn.mm.template.project.rest.api.dto.JobDto;

import javax.inject.Inject;

public class LongRunningJobRestService implements LongRunningJobRestApi {

  @Inject
  LongRunningJobsService service;

  @Override
  public JobDto triggerJob() {
    service.runVeryLongJob();
    JobDto dto = new JobDto();
    dto.setStatus("triggered");
    return dto;
  }
}
