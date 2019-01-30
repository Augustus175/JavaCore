package com.zzb.javacore.chap14.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {
    public static final SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(dateFormate.format(new Date()));
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(dateFormate.format(new Date()));
                }
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(dateFormate.format(new Date()));
                }
            }
        };
        Thread thread4 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(dateFormate.format(new Date()));
                }
            }
        };
        Thread thread5 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(dateFormate.format(new Date()));
                }
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
