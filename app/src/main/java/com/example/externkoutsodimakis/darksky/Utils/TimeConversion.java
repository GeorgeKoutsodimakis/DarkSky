package com.example.externkoutsodimakis.darksky.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConversion {

    public static final String epochToTime(Integer time) {
        Date date = new java.util.Date(time * 1000l);
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("E dd/MM/yy ");
        simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return simpleDateFormat.format(date);
    }
}
