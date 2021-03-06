package com.savdev.mvn.mm.template.project.app;

import com.google.common.collect.Lists;
import com.savdev.mvn.mm.template.project.api.ExampleApi;
import com.savdev.mvn.mm.template.project.datasource.entities.EntityExample;
import com.savdev.mvn.mm.template.project.datasource.repository.EntityExampleRepository;

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
      new EntityExample().update(item));
  }

  public ExampleApi validate4creation(ExampleApi item) {
    if ( item.getId() != 0 ) {
      throw new IllegalStateException("Id cannot be set for a new item");
    }
    return item;
  }

  public ExampleApi validate4update(ExampleApi item) {
    return item;
  }

  public ExampleApi update(ExampleApi item){
    return entityExampleRepository.save(
      entityExampleRepository.getOne(item.getId())
        .update(item));
  }

  public void delete(long id){
    entityExampleRepository.deleteById(id);
  }

}
