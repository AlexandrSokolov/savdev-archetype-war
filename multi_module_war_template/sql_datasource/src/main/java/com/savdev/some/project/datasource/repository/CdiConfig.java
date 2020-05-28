package com.savdev.some.project.datasource.repository;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig {

  @Produces
  @Dependent //do not set RequestScoped
  @PersistenceContext(unitName = "some_project_pu_name") //see persistence-unit name="some_project_pu_name" in persistence.xml
  public EntityManager entityManager;
}
