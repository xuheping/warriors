package com.hp.warriors.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String sdfFormatDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return sdf.format(date);
    }

    public static Date sdfParseDate(String date) {
        try {
            if (Objects.nonNull(date)) {
                return sdf.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
