package com.savdev.mvn.mm.template.project.rest.app;

import com.savdev.mvn.mm.template.project.rest.api.dto.ExampleDto;
import com.savdev.mvn.mm.template.project.app.ApplicationService;
import com.savdev.mvn.mm.template.project.rest.api.AppRestApi;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AppRestService implements AppRestApi {

  @Inject
  ApplicationService application;

  @Override
  public List<ExampleDto> items() {
    return JaxRsHandlerUtils.handle(application::items)
      .stream()
      .map(item -> new ExampleDto().update(item))
      .collect(Collectors.toList());
  }

  @Override
  public ExampleDto add(final ExampleDto item) {
    return new ExampleDto()
      .update(
        JaxRsHandlerUtils.validateAndHandle(
          item,
          application::validate4creation,
          application::add));
  }

  @Override
  public ExampleDto update(long id, ExampleDto item) {
    item.setId(id);
    return new ExampleDto()
      .update(JaxRsHandlerUtils.validateAndHandle(
        item,
        application::validate4update,
        application::update));
  }

  @Override
  public void delete(long id) {
    JaxRsHandlerUtils.handle(id, application::delete);
  }
}
