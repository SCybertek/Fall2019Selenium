package com.automation.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtilities {
    /**
     * This method returns current date as a String
     * Provide a format as a parameter
     *
     * MM - to specify months like : 02 03
     * MMM - to specify months like : Jan, Feb
     * dd - to specify days like : 01, 02
     * yyyy - to specify year like : 2010 , 2020
     *
     * @param format for example MMM dd, yyyy
     * @return current date as a String
     */
    public static String getCurrentDate(String format) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * This metod will return difference between end and start time
     * @param start
     * @param end
     * @param format
     * @return
     */ //without static it was not visible in my test
    public static long getTimeDifference(String start, String end, String format){
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(format));
        //we created an object of LocalTime
        //convert string into local time object
        //and the parse it
        //to parse we have to know the format

        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(format));
        return ChronoUnit.HOURS.between(startTime, endTime);
        //ChronoUnit class will provide the difference between time and date
    }
}
