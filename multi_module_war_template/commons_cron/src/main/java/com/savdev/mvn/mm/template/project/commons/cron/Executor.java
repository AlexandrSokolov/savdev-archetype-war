package com.savdev.mvn.mm.template.project.commons.cron;

@FunctionalInterface
public interface Executor {
  /**
   * Wraps an execution
   */
  void execute();
}