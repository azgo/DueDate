package com.emarsys.duedate;

import java.time.LocalDateTime;

/**
 * Due date calculation helper for reported bugs.
 *
 * @author NG
 */
public final class DueDateCalculator {

    /**
     * Calculates and returns the due date from the given reported time and turnaround hours for the work.
     *
     * @param reportedDateTime The bug report datetime
     * @param turnaroundHours The turnaround hours needed.
     * @return Due date.
     */
    public static LocalDateTime calculateDueDate(final LocalDateTime reportedDateTime, final int turnaroundHours) {
        int workDays = DateUtil.getWorkDays(turnaroundHours);
        int workHoursPlus = DateUtil.getWorkHoursOverADay(turnaroundHours);

        LocalDateTime dueDate = reportedDateTime;
        dueDate = DateUtil.addWorkDays(dueDate, workDays);
        dueDate = DateUtil.addWorkHoursPlus(dueDate, workHoursPlus);

        return dueDate;
    }

}
