package com.techieonthenet.utils;

import java.time.LocalDate;

/**
 * The type Date utils.
 */
public class DateUtils {

    /**
     * Gets last month end date.
     *
     * @return the last month end date
     */

    public static LocalDate getCurrentMonthEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        return startOfMonth;
    }

    public static LocalDate getCurrentMonthStartDate() {
        LocalDate today = LocalDate.now();
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        return endOfMonth;
    }



    public static LocalDate getLastMonthEndDate() {
        LocalDate date = LocalDate.now();
        date = date.minusDays(date.getDayOfMonth());
        return date;
    }

    /**
     * Gets last month start date.
     *
     * @return the last month start date
     */
    public static LocalDate getLastMonthStartDate() {
        LocalDate date = getLastMonthEndDate();
        date = date.minusDays(date.getDayOfMonth() - 1);
        return date;
    }


}
