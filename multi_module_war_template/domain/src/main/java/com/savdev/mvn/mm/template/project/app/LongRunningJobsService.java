package com.savdev.mvn.mm.template.project.app;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This service handles huge amount of data, each item is handled with its own handler
 *
 */
public class LongRunningJobsService {

  private static Log logger = LogFactory.getLog(LongRunningJobsService.class);


  public static class SomeJobStatisticsEntity {
    long id;

    String status;
    String errorMessage;
    LocalDateTime created;
    LocalDateTime finished;

    AtomicLong processed = new AtomicLong(0);
    Map<Integer, String> errors = new HashMap<>();

    @Override
    public String toString() {
      return new StringJoiner(", ", SomeJobStatisticsEntity.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("status='" + status + "'")
        .add("errorMessage='" + errorMessage + "'")
        .add("created=" + created)
        .add("finished=" + finished)
        .add("processed=" + processed)
        .add("errors=" + errors)
        .toString();
    }

    public void itemProcessed() {
      processed.incrementAndGet();
    }

    public void addError(Integer id, String error) {
      errors.put(id, error);
    }
  }

  public SomeJobStatisticsEntity runVeryLongJob(
    final Supplier<Boolean> interruptionChecker,
    final Consumer<Pair<Integer, SomeJobStatisticsEntity>> taskConsumer) {
    logger.info("runVeryLongJob triggered");

    SomeJobStatisticsEntity job = new SomeJobStatisticsEntity();
    job.status = "IN_PROGRESS";
    job.created = LocalDateTime.now();
    //save and flush job entity
    //repositoryService.saveAndFlush(job)

    try {
      for (int i = 1; i < 100; i++) {
        if (interruptionChecker.get()) {
          job.status = "INTERRUPTED";
          break;
        }
        taskConsumer.accept(Pair.of(i, job));
        logger.info("put into queue: " + i + "; really processed: " + job.processed.get());
      }
    } catch (Exception e) {
      job.status = "ERROR";
      job.errorMessage = e.getMessage();
    } finally {
      if ("IN_PROGRESS".equals(job.status)) {
        job.status = "FINISHED";
      }
      job.finished = LocalDateTime.now();
    }

    //save and flush job entity
    //repositoryService.saveAndFlush(job)
    return job;
  }

  public void smallTaskHandler(Pair<Integer, SomeJobStatisticsEntity> pair) {
    try {
      TimeUnit.SECONDS.sleep(10);
      int i = pair.getLeft();
      logger.info(Thread.currentThread().getId() + "; handled: " + i);
      if (i % 5 == 0) {
        throw new IllegalStateException("Thread id: '" + Thread.currentThread().getId() + "'; input: '" + i + "'");
      }
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    } catch (Exception e) {
      pair.getRight()
        .addError(
          pair.getLeft(),
          e.getMessage());
      logger.error(Thread.currentThread().getId() + "; error catched: " + pair.getLeft());
    } finally {
      pair.getRight().itemProcessed();
    }
  }

  public void sendStatistics(SomeJobStatisticsEntity statisticsEntity) {
    //send via email statistics
    logger.error("runVeryLongJob finished: " + statisticsEntity);
  }
}
