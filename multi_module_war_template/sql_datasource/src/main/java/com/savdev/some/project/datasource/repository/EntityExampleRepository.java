package com.savdev.some.project.datasource.repository;

import com.savdev.some.project.datasource.entities.EntityExample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EntityExampleRepository extends JpaRepository<EntityExample, Long> {

  //EntityExample.findByName query will be invoked:
  List<EntityExample> findByName(String name);

  //EntityExample.findByNameNative query will be invoked:
  List<EntityExample> findByNameNative(String name);

}
