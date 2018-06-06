package com.emarsys.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper util class for date calculation.
 * 
 * @author NG
 */
public final class DateUtil {

    /** Default datetime formatter */
    private static DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    /** Working hours per day */
    private static int workHoursPerDay = 8;
    /** Workday start hour */
    private static int startWorkHour = 9;
    /** Workday end hour */
    private static int endWorkHour = 17;

    /**
     * Private constructor.
     */
    private DateUtil() {
    }

    /**
     * Returns the due date calculated from the given reported datetime, incremented by the given working days and skipping weekends.
     *
     * @param reportedDateTime The reported datetime
     * @param workDays Working days to increment with
     * @return Due date
     */
    public static LocalDateTime addWorkDays(final LocalDateTime reportedDateTime, final int workDays) {
        LocalDateTime dueDate = reportedDateTime;

        for (int i = 0; i < workDays; i++) {
            dueDate = skipWeekend(dueDate.plusDays(1));
        }

        return dueDate;
    }

    /**
     * Returns the due date calculated from the given reported datetime, incremented by the given working hours and skipping weekends.
     *
     * @param reportedDateTime The reported datetime
     * @param workHoursPlus Working hours to increment with
     * @return Due date
     */
    public static LocalDateTime addWorkHoursPlus(final LocalDateTime reportedDateTime, final int workHoursPlus) {
        LocalDateTime dueDate = reportedDateTime;

        dueDate = skipWeekend(skipWorkdayOver(dueDate.plusHours(workHoursPlus)));

        return dueDate;
    }

    /**
     * Skips to the next working day if given datetime is weekend.
     *
     * @param date The datetime to check.
     * @return if it's not weekend, the same datetime as in param
     *         otherwise, the next working datetime
     */
    public static LocalDateTime skipWeekend(final LocalDateTime date) {
        LocalDateTime dateSkipped = date;

        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            dateSkipped = date.plusDays(2);
        }

        return dateSkipped;
    }

    /**
     * Skips to the next working day if given time is over the working hours (also skipping weekends).
     *
     * @param date The datetime to check.
     * @return if it's not over the working hours, the same datetime as in param
     *         otherwise, the next working datetime with remaining hours from previous workday.
     */
    public static LocalDateTime skipWorkdayOver(final LocalDateTime date) {
        LocalDateTime dateSkipped = date;
        if (date.getHour() > endWorkHour) {
            dateSkipped = date.plusDays(1).withHour(startWorkHour + date.getHour() - endWorkHour);
        }

        return dateSkipped;
    }

    /**
     * Returns the number of work days calculated from turnaround hours.
     *
     * @param turnaroundHours The turnaround hours
     * @return Number of work days
     */
    public static int getWorkDays(final int turnaroundHours) {
        int workDays = 0;

        if (turnaroundHours > 0) {
            workDays = turnaroundHours / workHoursPerDay;
            // if turnaround hours are multiple of workhours per day than take workdays minus 1, since the remaining hours of a whole day will also be
            // calculated into
            workDays = turnaroundHours % workHoursPerDay == 0 ? turnaroundHours / workHoursPerDay - 1 : turnaroundHours / workHoursPerDay;
        }

        return workDays;
    }

    /**
     * Returns the number of work hours over a full workday calculated from turnaround hours.
     *
     * @param turnaroundHours The turnaround hours
     * @return Number of work hours
     */
    public static int getWorkHoursOverADay(final int turnaroundHours) {
        int workHours = turnaroundHours > 0 ? turnaroundHours : 0;

        if (turnaroundHours > 0) {
            // if turnaround hours are multiple of workhours per day than take a whole workday in hours
            workHours = turnaroundHours % workHoursPerDay == 0 ? workHoursPerDay : turnaroundHours % workHoursPerDay;
        }

        return workHours;
    }

    /**
     * Returns the default datetime formatter.
     * 
     * @return the datetimeFormatter
     */
    public static DateTimeFormatter getDatetimeFormatter() {
        return datetimeFormatter;
    }
}
