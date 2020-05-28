package com.savdev.some.project.datasource.repository;

import com.savdev.some.project.datasource.entities.EntityExample;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityExampleRepository extends JpaRepository<EntityExample, Long> {
}
