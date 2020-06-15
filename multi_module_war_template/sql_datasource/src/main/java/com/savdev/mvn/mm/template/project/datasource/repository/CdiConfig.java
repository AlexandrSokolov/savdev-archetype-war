package com.savdev.mvn.mm.template.project.datasource.repository;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig {

  @Produces
  @Dependent //do not set RequestScoped
  @PersistenceContext(unitName = "template_project_pu_name") //see persistence-unit name="template_project_pu_name" in persistence.xml
  public EntityManager entityManager;
}
