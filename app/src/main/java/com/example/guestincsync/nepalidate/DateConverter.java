package com.example.guestincsync.nepalidate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


public class DateConverter {
    private DateConverter(){}

    private static Date getReferenceAD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1943, Calendar.APRIL, 14, 0, 0, 0);
        return calendar.getTime();
    }

    private static DateBS getReferenceBS() {
        return new DateBS(2000, CalendarBS.BAISAKH, 1);
    }

    /**
     * converts {@link Date} to corresponding {@link DateBS}
     * @param date {@link Date}
     * @return date in BS
     */
    public static DateBS convertADToBS(Date date) {
        LocalDate startDate = getReferenceAD().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysBetween = startDate.until(endDate, ChronoUnit.DAYS);

        if (daysBetween < 0)
            return null;

        return CalendarBS.addXDays(getReferenceBS(), daysBetween);
    }

    /**
     * converts {@link DateBS} to corresponding {@link Date}
     * @param date {@link DateBS}
     * @return date in AD
     */
    public static Date convertBSToAD(DateBS date) {
        DateBS startDate = getReferenceBS();
        int days = CalendarBS.daysBetween(startDate, date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getReferenceAD());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
