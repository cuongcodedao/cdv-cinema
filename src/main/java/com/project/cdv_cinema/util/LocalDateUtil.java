package com.project.cdv_cinema.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateUtil {
    public static String convertLocalDateToString(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }
}
