package com.savdev.mvn.mm.template.project.cron.core;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.ScheduleExpression;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class Timer {

  static final String WRONG_FORMAT = "Wrong cron expression: %s. Expected: 6 or 7 elements. For instance: 0 0 */2 * * *";

  String info;

  String cronJobExpression;

  String scheduleExpression;

  LocalDateTime nextTimeout;

  public static Timer instance(final javax.ejb.Timer ejbTimer) {
    Timer timer = new Timer();
    timer.info = (String) ejbTimer.getInfo();
    timer.scheduleExpression = ejbTimer.getSchedule().toString();
    timer.nextTimeout = ejbTimer.getNextTimeout() != null ? ejbTimer.getNextTimeout()
      .toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime() : null;
    timer.cronJobExpression = timer.toCronJob(ejbTimer.getSchedule());
    return timer;
  }

  public ScheduleExpression fromCronJob(final String cronJobExpression) {
    final List<String> cronJobExpressionParts = Arrays.asList(cronJobExpression.split(" "));
    if (cronJobExpressionParts.size() == 6 || cronJobExpressionParts.size() == 7) {
      ScheduleExpression scheduleExpression = new ScheduleExpression()
        .second(cronJobExpressionParts.get(0))
        .minute(cronJobExpressionParts.get(1))
        .hour(cronJobExpressionParts.get(2))
        .dayOfMonth(cronJobExpressionParts.get(3))
        .month(cronJobExpressionParts.get(4))
        .dayOfWeek(cronJobExpressionParts.get(5));
      if (cronJobExpressionParts.size() == 7) {
        scheduleExpression.year(cronJobExpressionParts.get(6));
      }
      return scheduleExpression;
    } else {
      throw new IllegalStateException(
        String.format(
          WRONG_FORMAT,
          cronJobExpression ));
    }
  }

  public String toCronJob(final ScheduleExpression scheduleExpression) {
    String expressionWithoutYear = String.format("%s %s %s %s %s %s",
      scheduleExpression.getSecond(),
      scheduleExpression.getMinute(),
      scheduleExpression.getHour(),
      scheduleExpression.getDayOfMonth(),
      scheduleExpression.getMonth(),
      scheduleExpression.getDayOfWeek());
    return StringUtils.isEmpty(scheduleExpression.getYear()) ? expressionWithoutYear
      : String.format("%s %s", expressionWithoutYear, scheduleExpression.getYear());

  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getCronJobExpression() {
    return cronJobExpression;
  }

  public void setCronJobExpression(String cronJobExpression) {
    this.cronJobExpression = cronJobExpression;
  }

  public String getScheduleExpression() {
    return scheduleExpression;
  }

  public void setScheduleExpression(String scheduleExpression) {
    this.scheduleExpression = scheduleExpression;
  }

  public LocalDateTime getNextTimeout() {
    return nextTimeout;
  }

  public void setNextTimeout(LocalDateTime nextTimeout) {
    this.nextTimeout = nextTimeout;
  }
}
