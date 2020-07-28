package com.savdev.mvn.mm.template.project.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
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
 */
@Singleton
public class JeeAsyncHandler {

  private static final Log logger = LogFactory.getLog(JeeAsyncHandler.class);

  @Inject
  JeeAsyncInternalHelper asyncInternalHelper;

  Future<Void> longJobFuture;

  /**
   *
   * This is the only method, exposed
   * @param longJob
   */
  @TransactionTimeout(value = 3, unit = TimeUnit.HOURS)
  public <T, R> void trigger(
    final BiFunction<Supplier<Boolean>, Consumer<T>, R> longJob,
    final Consumer<T> smallTaskConsumer,
    final Consumer<R> resultConsumer) {
    if (longJobFuture == null || longJobFuture.isDone()) {
      logger.info("No async jobs in progress. Run a new long running job.");
      longJobFuture = asyncInternalHelper.asyncTrigger(longJob, smallTaskConsumer, resultConsumer);
    } else {
      logger.info("Nothing is triggered. Async job is still in progress.");
    }
  }

  @PreDestroy
  void cancelJob() {
    if (longJobFuture != null && !longJobFuture.isCancelled()) {
      longJobFuture.cancel(true);
    }
  }

}
