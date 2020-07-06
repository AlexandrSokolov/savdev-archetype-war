package com.savdev.mvn.mm.template.project.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * This service is an example of running a long running job.
 *
 * EJB does not offer a good solution to cancel an asynchronous method. Each method has its own disadvantage.
 * See 'Misleading Cancellation of a Future in @Asynchronous':
 * http://highcohesionloosecoupling.com/index.php/2017/08/28/misleading-cancellation-future-asynchronous
 *
 * Solution is, instead of trying to cancel a big task, that is run asynchronously, we split it into smaller jobs
 * and run each sub-job only in case a package with the service is not undeployed (SessionContext.wasCancelCalled)
 *
 */
@Stateless
public class LongRunningJobsService {

  private static Log logger = LogFactory.getLog(LongRunningJobsService.class);

  @Inject
  Instance<LongRunningJobsService> that;

  @Resource
  SessionContext sessionContext;

  private static class SomeJobEntity {
    long id;
    String status;
    LocalDateTime created;
    LocalDateTime finished;

    @Override
    public String toString() {
      return new StringJoiner(", ", SomeJobEntity.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("status='" + status + "'")
        .add("created=" + created)
        .add("finished=" + finished)
        .toString();
    }
  }

  /**
   * This method is entry point, it is better to move other asynchronous methods into a separate service
   */
  @TransactionTimeout(value = 3, unit = TimeUnit.HOURS)
  @Asynchronous //Asynchronous must be public
  public void runVeryLongJob(){

    logger.error("runVeryLongJob async triggered");
    SomeJobEntity job = new SomeJobEntity();
    job.status = "new";
    job.created = LocalDateTime.now();
    //save and flush job entity
    //repositoryService.saveAndFlush(job)
    that.get().complexJob(job);
    logger.error("runVeryLongJob async finished");
  }

  /**
   * This method waits for the result of the small jobs that have been invoked
   * @param job
   */
  @Asynchronous //Asynchronous must be public
  public void complexJob(SomeJobEntity job){
    try {
      logger.error("complexJob async triggered");

      long expectedTasksNum = 100;
      AtomicLong actuallyTasksNum = new AtomicLong(0);
      List<Future<Void>> asyncTasks = LongStream.range(0, expectedTasksNum).boxed()
        .map(id -> that.get().cancellableSmallJob(id, actuallyTasksNum))
        .collect(Collectors.toList());

      while (!asyncTasks.stream()
        .allMatch(Future::isDone)) {
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      if (expectedTasksNum != actuallyTasksNum.get()) {
        logger.error(String.format("complexJob expected %d tasks, actually was finished only: %d",
          expectedTasksNum, actuallyTasksNum.get()));
      }
      job.status = "finished";
    } catch (Exception e) {
      job.status = "failed";
      throw e;
    } finally {
      job.finished = LocalDateTime.now();
      logger.error("complexJob async finished");
      System.out.println(job);
    }
  }

  @Asynchronous //Asynchronous must be public
  public Future<Void> cancellableSmallJob(long id, AtomicLong actuallyTasksNum){
    //do some job asynchronously if not undeployed
    if (!sessionContext.wasCancelCalled()) {
      actuallyTasksNum.incrementAndGet();
      smallJob(id);
    }
    return new AsyncResult<>(null);
  }

  private void smallJob(long id){
    //do some job asynchronously
    System.out.println(id);
    try {
      logger.error("smallJobs start " + id);
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.error("smallJobs end " + id);
  }
}
