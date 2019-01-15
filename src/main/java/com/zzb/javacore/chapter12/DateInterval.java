package com.zzb.javacore.chapter12;

import java.util.Date;

public class DateInterval extends Pair<Date> {
    @Override
    public void setSecond(Date second) {
        if (second.compareTo(getFirst()) > 0) {
            super.setSecond(second);
        }
    }

    public static void main(String[] args) {
        DateInterval interval = new DateInterval();
        Pair<Date> pair = interval;
        Date earlyDate = new Date(1547559863);
        Date date = new Date();
        pair.setFirst(earlyDate);
        pair.setSecond(date);
    }
}
