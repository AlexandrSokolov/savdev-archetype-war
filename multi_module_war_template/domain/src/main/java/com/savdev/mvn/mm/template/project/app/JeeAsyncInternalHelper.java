package com.savdev.mvn.mm.template.project.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This class is used only by JeeAsyncHandler, must not be exposed to the clients, to simplify API for them
 *
 * About cancellation, see:
 *  See 'Misleading Cancellation of a Future in @Asynchronous':
 *  http://highcohesionloosecoupling.com/index.php/2017/08/28/misleading-cancellation-future-asynchronous
 *
 * To run small tasks asynchronously from within the main async method:
 *  - such methods must be public, marked with @Asynchronous
 *  - such methods are invoked from the main asynchronous method not directly,
 *    but only through self-reference with 'that'
 *      otherwise they will not be invoked asynchronously
 *  - such asynchronous methods are invoked, only if application is not undeployed and free threads are available,
 *    => you do not need to think about cancellation of small tasks, just wait, when they are finished
 *
 */
@Stateless
public class JeeAsyncInternalHelper {

  private static final Log logger = LogFactory.getLog(JeeAsyncInternalHelper.class);

  private static final int PARALLEL_TASKS = 10;

  @Resource
  SessionContext sessionContext;

  @Inject
  Instance<JeeAsyncInternalHelper> that;

  @Asynchronous
  public <T, R> Future<Void> asyncTrigger(
    final BiFunction<Supplier<Boolean>, Consumer<T>, R> longJob,
    final Consumer<T> smallTaskConsumer,
    final Consumer<R> resultConsumer) {
    Semaphore semaphorePutInQueue = new Semaphore(PARALLEL_TASKS);
    Semaphore semaphoreTriggered = new Semaphore(PARALLEL_TASKS);
    resultConsumer.accept(
      longJob.apply(
        () -> sessionContext.wasCancelCalled(),
        t -> {
          try {
            logger.info("semaphore.acquire");
            //block and do not put into queue if all possible parallel tasks are running
            semaphorePutInQueue.acquire();
            //put into the queue
            logger.info("put task into the queue: ");
            that.get().asyncTaskTrigger(semaphorePutInQueue, semaphoreTriggered, t, smallTaskConsumer);
            if (sessionContext.wasCancelCalled()) {
              //block until all triggered items are not handled
              logger.info("task is canceled, waiting for tasks number: " + semaphoreTriggered.getQueueLength());
              boolean waitingResult = semaphoreTriggered.tryAcquire(PARALLEL_TASKS, 30, TimeUnit.SECONDS);
              logger.info(waitingResult ? "waiting for all tasks is successful" : "waiting was interrupted");
            }
          } catch (InterruptedException e) {
            throw new IllegalStateException(e);
          }

        }));
    return new AsyncResult<>(null);
  }

  /**
   * this task is run, only if application is not undeployed and there are free threads in the pool
   *
   */
  @Asynchronous
  public <T> void asyncTaskTrigger(
    final Semaphore semaphorePutInQueue,
    final Semaphore semaphoreTriggered,
    final T input,
    final Consumer<T> consumer) {
    try {
      semaphoreTriggered.acquire();
      consumer.accept(input);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    } finally {
      semaphorePutInQueue.release();
      semaphoreTriggered.release();
      logger.info("semaphore.release, input: " + input);
    }
  }
}
