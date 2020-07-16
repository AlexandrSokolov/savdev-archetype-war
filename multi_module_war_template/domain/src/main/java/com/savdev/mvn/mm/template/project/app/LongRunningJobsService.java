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
 * Requirements:
 *  - timeout setting, @TransactionTimeout(value = 3, unit = TimeUnit.HOURS)
 *  - public asynchronous method for entry point, @Asynchronous
 *
 * We should split large task into small ones and run them asynchronously.
 *
 * Requirements to such small tasks, run asynchronously from within the main async method:
 *  - such methods must be public, marked with @Asynchronous
 *  - such methods are invoked from the main asynchronous method not directly,
 *    but only through self-reference with 'that'
 *    otherwise they will not be invoked asynchronously
 *  - such asynchronous methods are invoked, only if application is not undeployed,
 *    so you do not need to think about cancellation, if such methods are not long running
 *
 *
 * Requirements to make the long job cancellable:
 *  - the asynchronous method must return Future
 *  - to check in the asynchronous method whether it is cancelled, use SessionContext.wasCancelCalled().
 *  - the invoker of the method, stores Future as its instance field
 *  - in the @PostConstruct of the invoker, the instance of Future is cancelled with 'true' argument.
 *    NOTES:
 *      - you cannot use @PostConstruct in the service itself, it will not work!!!
 *      - SessionContext.wasCancelCalled() works correctly, only if invoker cancells the Future result.
 *        otherwise it always returns false
 *
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
  public Future<Void> runVeryLongJob(){

    logger.error("runVeryLongJob async triggered");
    SomeJobEntity job = new SomeJobEntity();
    job.status = "new";
    job.created = LocalDateTime.now();
    //save and flush job entity
    //repositoryService.saveAndFlush(job)

    //we queue the complex task, it is invoked, when thread is available in the pool!
    that.get().complexJob(job);
    logger.error("runVeryLongJob async finished");

    return new AsyncResult<>(null);
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
