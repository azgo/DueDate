package com.emarsys.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Due date calculation helper for reported bugs.
 * 
 * @author NG
 */
public class DueDateCalculator {

    int workHoursPerDay = 8;
    int startWorkHour = 9;
    int endWorkHour = 17;

    public LocalDateTime calculateDueDate(LocalDateTime reportedDateTime, int turnaroundHours) {
        int workDays = getWorkDays(turnaroundHours);
        int workHoursPlus = getWorkHoursOverADay(turnaroundHours);

        LocalDateTime dueDate = reportedDateTime;
        dueDate = addWorkDays(dueDate, workDays);
        dueDate = addWorkHoursPlus(dueDate, workHoursPlus);

        return dueDate;
    }

    private LocalDateTime addWorkDays(LocalDateTime reportedDateTime, int workDays) {
        LocalDateTime dueDate = reportedDateTime;

        for (int i = 0; i < workDays; i++) {
            dueDate = skipWeekend(dueDate.plusDays(1));
        }

        return dueDate;
    }

    private LocalDateTime skipWeekend(LocalDateTime date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            date = date.plusDays(2);
        }

        return date;
    }

    private LocalDateTime skipWorkdayOver(LocalDateTime date) {
        if (date.getHour() > endWorkHour) {
            date = date.plusDays(1).withHour(startWorkHour + date.getHour() - endWorkHour);
        }

        return date;
    }

    private LocalDateTime addWorkHoursPlus(LocalDateTime reportedDateTime, int workHoursPlus) {
        LocalDateTime dueDate = reportedDateTime;

        dueDate = skipWeekend(skipWorkdayOver(dueDate.plusHours(workHoursPlus)));

        return dueDate;
    }

    private int getWorkDays(int turnaroundHours) {
        int workDays = 0;

        if (turnaroundHours > 0) {
            workDays = turnaroundHours / workHoursPerDay;
            // if turnaround hours are multiple of workhours per day than take workdays minus 1, since the remaining hours of a whole day will also be
            // calculated into
            workDays = turnaroundHours % workHoursPerDay == 0 ? turnaroundHours / workHoursPerDay - 1 : turnaroundHours / workHoursPerDay;
        }

        return workDays;
    }

    private int getWorkHoursOverADay(int turnaroundHours) {
        int workHours = turnaroundHours;

        if (turnaroundHours > 0) {
            // if turnaround hours are multiple of workhours per day than take a whole workday in hours
            workHours = turnaroundHours % workHoursPerDay == 0 ? workHoursPerDay : turnaroundHours % workHoursPerDay;
        }

        return workHours;
    }

}
