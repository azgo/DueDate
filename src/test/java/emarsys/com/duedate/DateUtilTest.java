package emarsys.com.duedate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.emarsys.duedate.DateUtil;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * Unit test for DateUtil util class.
 */
@RunWith(JUnitParamsRunner.class)
public class DateUtilTest {

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
            "20,2",
            "-1,0"
    })
    public void testGetWorkDays(int turnaroundTime, int expectedWorkDays) throws Exception {
        assertEquals(expectedWorkDays, DateUtil.getWorkDays(turnaroundTime));
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
            "20,4",
            "-1,0"
    })
    public void testGetWorkHoursOverADay(int turnaroundTime, int expectedWorkDays) throws Exception {
        assertEquals(expectedWorkDays, DateUtil.getWorkHoursOverADay(turnaroundTime));
    }
}
