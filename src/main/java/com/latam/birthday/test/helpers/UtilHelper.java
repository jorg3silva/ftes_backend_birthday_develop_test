package com.latam.birthday.test.helpers;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilHelper {

    /**
     * Instantiates a new message error helper.
     */
    private UtilHelper(){
        super();
    }


    /**
     *
     *      Get diff from any date to now in years
     *
     * @param date some date
     * @return age Integer
     */
    public static final Integer getDateDiffInYearToNow(Date date) {

        LocalDate firstDate = UtilHelper.getLocaleFromDate(date);
        LocalDate secondDate = LocalDate.now();

        Period period = Period.between(firstDate, secondDate);

        return period.getYears();
    }


    /**
     *
     *      get left days for next day of month, in this year or next.
     *
     *
     * @param date Date some date
     * @return days quantity
     */
    public static final Integer getDaysForNextDayOfMonth(Date date) {

        LocalDate firstDate = UtilHelper.getLocaleFromDate(date);
        LocalDate secondDate = LocalDate.now();

        LocalDate firstDateYear = firstDate.withYear(secondDate.getYear()); // set year of actual



        int days = 0;

        if (secondDate.isAfter(firstDateYear) || secondDate.isEqual(firstDate)) {
            firstDateYear = firstDateYear.plusYears(1);
        } else if (secondDate.getDayOfMonth() == firstDateYear.getDayOfMonth() && secondDate.getMonth() == firstDateYear.getMonth() ){
            return  days; // 0 = today.
        }

        return (int) ChronoUnit.DAYS.between(secondDate, firstDateYear);
    }


    /**
     *
     *      Transform Date to LocalDate
     *
     * @param date Date some date
     * @return localDate obj
     */
    private static final LocalDate getLocaleFromDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return LocalDate.of(year, month, day);
    }
}
