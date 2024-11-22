package com.project.cdv_cinema.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeUtil {
    public static String convertLocalTime(LocalTime localTime, DateTimeFormatter formatter) {
        return localTime.format(formatter);
    }
}
