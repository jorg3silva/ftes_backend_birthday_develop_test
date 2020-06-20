package com.latam.birthday.test.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class UtilHelperTest {

    @Test
    public void getDateDiffInYearToNowTest() {

        Date someDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(someDate);
        c.add(Calendar.YEAR, -10);

        assertEquals( 10, (int) UtilHelper.getDateDiffInYearToNow(c.getTime())  );
    }


    @Test
    public void getDateDiffInYearToNowTestFutureTest() {

        Date someDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(someDate);
        c.add(Calendar.YEAR, 10);

        assertEquals( -10, (int) UtilHelper.getDateDiffInYearToNow(c.getTime())  );
    }


    @Test
    public void getDateDiffInYearToNowTestTodayTest() {

        Date someDate = new Date();
        assertEquals( 0, (int) UtilHelper.getDateDiffInYearToNow(someDate)  );
    }


    @Test
    public void getDaysForNextDayOfMonthTest() {

        Date someDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(someDate);
        c.add(Calendar.DATE, 10);

        assertEquals( 10, (int) UtilHelper.getDaysForNextDayOfMonth(c.getTime())  );
    }


    @Test
    public void getDaysForNextDayOfMonthTodayTest() {

        Date someDate = new Date();
        int daysForBirthdate = UtilHelper.getDaysForNextDayOfMonth(someDate);

        assertTrue( daysForBirthdate == 365 || daysForBirthdate == 366 );
    }

    @Test
    public void getDaysForNextDayOfMonthBirthdayTest() {

        Date someDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(someDate);
        c.add(Calendar.YEAR, -10);

        assertEquals( 0, (int) UtilHelper.getDaysForNextDayOfMonth(c.getTime())  );
    }
}
