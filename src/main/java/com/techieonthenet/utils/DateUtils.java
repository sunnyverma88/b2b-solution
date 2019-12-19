package com.techieonthenet.utils;

import java.time.LocalDate;

public class DateUtils {

    public static LocalDate getLastMonthEndDate() {
        LocalDate date = LocalDate.now();
        date = date.minusDays(date.getDayOfMonth());
        return date;
    }

    public static LocalDate getLastMonthStartDate() {
        LocalDate date = getLastMonthEndDate();
        date = date.minusDays(date.getDayOfMonth() - 1);
        return date;
    }


}
