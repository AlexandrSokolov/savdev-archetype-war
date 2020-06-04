package com.savdev.some.project.rest.app;

import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.app.ApplicationService;
import com.savdev.some.project.rest.api.AppRestApi;
import com.savdev.some.project.rest.api.dto.ExampleDto;

import javax.inject.Inject;
import java.util.List;

public class AppRestService implements AppRestApi {

  @Inject
  ApplicationService application;

  @Override
  public List<ExampleApi> items() {
    return application.items();
  }

  @Override
  public ExampleApi add(final ExampleDto item) {
    return application.add(item);
  }

  @Override
  public ExampleApi update(long id, ExampleDto item) {
    item.setId(id);
    return application.update(item);
  }

  @Override
  public void delete(long id) {
    application.delete(id);
  }
}
