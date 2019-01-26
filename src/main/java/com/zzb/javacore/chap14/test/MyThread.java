package com.zzb.javacore.chap14.test;

public class MyThread extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("+++++++++++++");
        }
        System.out.println("=========================");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
    }
}
