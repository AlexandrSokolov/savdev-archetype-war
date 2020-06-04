package com.savdev.some.project.rest.app;

import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.app.ApplicationService;
import com.savdev.some.project.rest.api.AppRestApi;
import com.savdev.some.project.rest.api.dto.ExampleDto;

import javax.inject.Inject;
import java.util.List;

import static com.savdev.some.project.rest.app.JaxRsHandlerUtils.handle;
import static com.savdev.some.project.rest.app.JaxRsHandlerUtils.validateAndHandle;

public class AppRestService implements AppRestApi {

  @Inject
  ApplicationService application;

  @Override
  public List<ExampleApi> items() {
    return handle(application::items);
  }

  @Override
  public ExampleApi add(final ExampleDto item) {
    return validateAndHandle(
      item,
      application::validate4creation,
      application::add);
  }

  @Override
  public ExampleApi update(long id, ExampleDto item) {
    item.setId(id);
    return validateAndHandle(
      item,
      application::validate4update,
      application::update);
  }

  @Override
  public void delete(long id) {
    handle(id, application::delete);
  }
}
