package com.emarsys.duedate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    /**
     * For case of command line run
     * 
     * @param args.
     *        First param is the reported time of the bug, format is 'yyyy.MM.dd HH:mm'
     *        Second param is the turnaround time in hours
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            validateDatetime(args[0]);
            // validateTunraroundTime(args[1]);

        } else {
            LOG.error("invalid parameters. Use [datetime],[turnaround] format.");
        }
    }

    private static void validateDatetime(String datetime) {
        try {
            LocalDateTime.parse(datetime, datetimeFormatter);
        } catch (DateTimeParseException e) {
            LOG.error("invalid datetime format");
        }
    }
}
