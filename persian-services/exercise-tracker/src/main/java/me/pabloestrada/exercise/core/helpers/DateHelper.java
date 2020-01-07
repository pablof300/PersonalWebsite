package me.pabloestrada.exercise.core.helpers;

import me.pabloestrada.exercise.core.runs.Run;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;

public final class DateHelper {
    public int getDaysInBetweenRuns(final Run startRun, final Run endRun) {
        return getDaysInBetweenRuns(startRun.getStartDate(), endRun.getStartDate());
    }

    public int getDaysInBetweenRuns(final Date startDate, final Date endDate) {
        final DateTime start = new DateTime(startDate);
        final DateTime end = new DateTime(endDate);
        return Days.daysBetween(start.withTimeAtStartOfDay(), end.withTimeAtStartOfDay()).getDays();
    }
}
