package com.savdev.mvn.mm.template.project.cron.core;

@FunctionalInterface
public interface Executor {
  /**
   * Wraps an execution
   */
  void execute();
}