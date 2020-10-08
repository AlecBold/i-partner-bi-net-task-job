package com.example.i_partner_binet_task_job.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static Date dateFromSeconds(long time) {
        return new Date(time * 1000);
    }

    public static String humanReadableDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String humanReadableDateFromSeconds(long time) {
        return humanReadableDate(dateFromSeconds(time));
    }
}
