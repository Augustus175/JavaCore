package com.zzb.javacore.chap14.test;

public class TestInterrupt extends Thread {
    @Override
    public void run() {
        myTusk();
    }

    public void myTusk() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        TestInterrupt testInterrupt = new TestInterrupt();
        testInterrupt.start();
//        testInterrupt.interrupt();
//        testInterrupt.interrupt();
        System.out.println(testInterrupt.getState());
        System.out.println(testInterrupt.getStackTrace());
        System.out.println(testInterrupt.isInterrupted());
    }
}
