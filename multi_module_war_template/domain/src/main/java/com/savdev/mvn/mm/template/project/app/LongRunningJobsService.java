package com.savdev.mvn.mm.template.project.app;

import com.google.common.collect.Lists;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Stateless
public class LongRunningJobsService {

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

  @TransactionTimeout(value = 3, unit = TimeUnit.HOURS)
  public void runVeryLongJob(){

    SomeJobEntity job = new SomeJobEntity();
    job.status = "new";
    job.created = LocalDateTime.now();
    //save and flush job entity
    //repositoryService.saveAndFlush(job)
    complexJob(job);
  }

  //Asynchronous must be public
  @Asynchronous
  public void complexJob(SomeJobEntity job){
    try {
      List<Future<Void>> asyncTasks = Lists.newArrayList(1, 2, 3).stream()
        .map(this::smallJobs)
        .collect(Collectors.toList());
      while (!asyncTasks.stream().allMatch(Future::isDone)) {
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      job.status = "finished";
    } catch (Exception e) {
      job.status = "failed";
      throw e;
    } finally {
      job.finished = LocalDateTime.now();
      System.out.println(job);
    }

  }

  //Asynchronous must be public
  @Asynchronous
  public Future<Void> smallJobs(int id){
    //do some job asynchronously
    System.out.println(id);
    return new AsyncResult<>(null);
  }
}
