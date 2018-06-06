package com.emarsys.duedate;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Due date calculator starter app.
 *
 * @author NG
 */
public final class App {

    /**
     * Private constructor.
     */
    private App() {
    }

    /**
     * For case of command line run.
     *
     * @param args.
     *        First param is the reported time of the bug, format is 'yyyy.MM.dd HH:mm'
     *        Second param is the turnaround time in hours
     */
    public static void main(final String[] args) {
        if (args.length == 2) {
            LocalDateTime reportDatetime = validateDatetime(args[0]);
            int tunraroundTime = validateTunraroundTime(args[1]);

            LocalDateTime dueDate = DueDateCalculator.calculateDueDate(reportDatetime, tunraroundTime);

            System.out.println(String.format("The calculated due date: %s", dueDate));
        } else {
            System.out.println("invalid parameters. Use [datetime] [turnaround] format.");
        }
    }

    private static LocalDateTime validateDatetime(final String datetime) {
        LocalDateTime reportDatetime = null;

        try {
            reportDatetime = LocalDateTime.parse(datetime, DateUtil.getDatetimeFormatter());
        } catch (DateTimeParseException e) {
            System.out.println("invalid datetime format");
        }

        return reportDatetime;
    }

    private static int validateTunraroundTime(final String tunraroundTime) {
        int time = 0;

        try {
            time = Integer.valueOf(tunraroundTime);
        } catch (DateTimeParseException e) {
            System.out.println("invalid tunraround time");
        }

        return time;
    }

}
