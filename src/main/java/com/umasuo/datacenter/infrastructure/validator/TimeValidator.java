package com.umasuo.datacenter.infrastructure.validator;

import com.umasuo.exception.ParametersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by Davis on 17/6/19.
 */
public final class TimeValidator {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(TimeValidator.class);

  /**
   * Instantiates a new Time validator.
   */
  private TimeValidator() {
  }

  /**
   * Validate period.
   *
   * @param startTime the start time
   * @param endTime the end time
   */
  public static void validatePeriod(long startTime, long endTime) {
    ZonedDateTime start = Instant.ofEpochMilli(startTime).atZone(ZoneOffset.UTC);
    ZonedDateTime end = Instant.ofEpochMilli(endTime).atZone(ZoneOffset.UTC);

    long daysRange = ChronoUnit.DAYS.between(start, end);

    if (daysRange > 7 || daysRange < -7) {
      LOG.debug("Time period can not be more than 7 days, start: {}, end: {}.", start, end);
      throw new ParametersException("Time period can not be more than 7 days");
    }
  }
}
