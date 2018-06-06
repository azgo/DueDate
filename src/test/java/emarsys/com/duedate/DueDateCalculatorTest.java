package emarsys.com.duedate;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.emarsys.duedate.DueDateCalculator;

import junit.framework.TestCase;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * Unit test for simple DueDateCalculator.
 */
@RunWith(JUnitParamsRunner.class)
public class DueDateCalculatorTest extends TestCase {

    private DueDateCalculator testSubject = new DueDateCalculator();
    private static DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Test
    @Parameters
    public void testCalculateDueDate(LocalDateTime reportedDateTime, int turnaroundTime, LocalDateTime expectedDueDateTime) {
        assertEquals(expectedDueDateTime, testSubject.calculateDueDate(reportedDateTime, turnaroundTime));
    }

    /**
     * Test for private "getWorkDays" method. Not need but helped, leaving here.
     * 
     * @param turnaroundTime
     * @param expectedWorkDays
     * @throws Exception Generic Exception is enough here
     */
    @Test
    @Parameters({
            "0,0",
            "1,0",
            "5,0",
            "8,0",
            "10,1",
            "15,1",
            "16,1",
            "17,2",
            "20,2"
    })
    public void testGetWorkDays(int turnaroundTime, int expectedWorkDays) throws Exception {
        Method testSubjectMethod = getTestSubjectMethod("getWorkDays");
        assertEquals(expectedWorkDays, testSubjectMethod.invoke(testSubject, turnaroundTime));
    }

    /**
     * Test for private "getWorkHoursOverADay" method. Not need but helped, leaving here.
     * 
     * @param turnaroundTime
     * @param expectedWorkDays
     * @throws Exception Generic Exception is enough here
     */
    @Test
    @Parameters({
            "0,0",
            "5,5",
            "8,8",
            "10,2",
            "15,7",
            "16,8",
            "17,1",
            "20,4"
    })
    public void testGetWorkHoursOverADay(int turnaroundTime, int expectedWorkDays) throws Exception {
        Method testSubjectMethod = getTestSubjectMethod("getWorkHoursOverADay");
        assertEquals(expectedWorkDays, testSubjectMethod.invoke(testSubject, turnaroundTime));
    }

    private Object[] parametersForTestCalculateDueDate() {
        return new Object[] {
                new Object[] {LocalDateTime.parse("2018.06.07 09:00", datetimeFormatter), "8", LocalDateTime.parse("2018.06.07 17:00", datetimeFormatter)},

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
                new Object[] {LocalDateTime.parse("2018.06.07 09:00", datetimeFormatter), "9", LocalDateTime.parse("2018.06.08 10:00", datetimeFormatter)},
                new Object[] {LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter), "0", LocalDateTime.parse("2018.06.05 17:00", datetimeFormatter)}
        };
    }

    /**
     * Returns the method by its name from the testSubject class
     * 
     * @param methodName
     * @return The method to test
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    private Method getTestSubjectMethod(String methodName) throws NoSuchMethodException, SecurityException {
        Method methodSubject = testSubject.getClass().getDeclaredMethod(methodName, int.class);
        methodSubject.setAccessible(true);
        return methodSubject;
    }
}
