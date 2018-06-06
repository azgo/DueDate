package emarsys.com.duedate;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.emarsys.duedate.DueDateCalculator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * Unit test for DueDateCalculator.
 */
@RunWith(JUnitParamsRunner.class)
public class DueDateCalculatorTest {

    private DueDateCalculator testSubject = new DueDateCalculator();
    private static DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Test
    @Parameters
    public void testCalculateDueDate(LocalDateTime reportedDateTime, int turnaroundTime, LocalDateTime expectedDueDateTime) {
        assertEquals(expectedDueDateTime, testSubject.calculateDueDate(reportedDateTime, turnaroundTime));
    }

    @SuppressWarnings("unused")
    private Object[] parametersForTestCalculateDueDate() {
        return new Object[] {
                new Object[] {LocalDateTime.parse("2018.06.05 11:10", datetimeFormatter), "5", LocalDateTime.parse("2018.06.05 16:10", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 11:00", datetimeFormatter), "8", LocalDateTime.parse("2018.06.06 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 11:00", datetimeFormatter), "16", LocalDateTime.parse("2018.06.07 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 11:00", datetimeFormatter), "24", LocalDateTime.parse("2018.06.08 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 11:00", datetimeFormatter), "32", LocalDateTime.parse("2018.06.11 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 11:00", datetimeFormatter), "88", LocalDateTime.parse("2018.06.20 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.08 11:00", datetimeFormatter), "8", LocalDateTime.parse("2018.06.11 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.08 11:00", datetimeFormatter), "16", LocalDateTime.parse("2018.06.12 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.08 15:00", datetimeFormatter), "1", LocalDateTime.parse("2018.06.08 16:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.08 15:00", datetimeFormatter), "2", LocalDateTime.parse("2018.06.08 17:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.08 15:00", datetimeFormatter), "3", LocalDateTime.parse("2018.06.11 10:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.07 11:00", datetimeFormatter), "16", LocalDateTime.parse("2018.06.11 11:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.06 09:00", datetimeFormatter), "8", LocalDateTime.parse("2018.06.06 17:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.06 09:00", datetimeFormatter), "16", LocalDateTime.parse("2018.06.07 17:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.07 09:00", datetimeFormatter), "8", LocalDateTime.parse("2018.06.07 17:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.07 09:00", datetimeFormatter), "9", LocalDateTime.parse("2018.06.08 10:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter), "0", LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter), "-10", LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter), "100", LocalDateTime.parse("2018.06.22 13:00", datetimeFormatter)}
        };
    }
}
