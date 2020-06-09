package com.savdev.some.project.app;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ApplicationStartup {

  @PostConstruct
  public void init(){
    System.out.println("Some initialation logic");
  }
}
