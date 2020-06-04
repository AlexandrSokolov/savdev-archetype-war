package com.savdev.some.project.app;

import com.google.common.collect.Lists;
import com.savdev.some.project.api.ExampleApi;
import com.savdev.some.project.datasource.entities.EntityExample;
import com.savdev.some.project.datasource.repository.EntityExampleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ApplicationService {

  @Inject
  EntityExampleRepository entityExampleRepository;

  public List<ExampleApi> items(){
    return Lists.newArrayList(entityExampleRepository.findAll());
  }

  public ExampleApi add(ExampleApi item){
    return entityExampleRepository.save(
      new EntityExample(item));
  }

  public ExampleApi update(ExampleApi item){
    return entityExampleRepository.save(
      new EntityExample(item));
  }

  public void delete(long id){
    entityExampleRepository.deleteById(id);
  }

}
