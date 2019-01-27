package com.zzb.javacore.chap14.test;

public class UncaughtExceptionTest {

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
    }
}

class TestThread extends Thread {
    TestThread() {
        Thread.setDefaultUncaughtExceptionHandler(new MyCrashHandler());
    }

    @Override
    public void run() {
        this.work();
    }

    private void work() {
        String str = null;
        try {
//            System.out.println(100 / 0);
            str.split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(222);
    }
}

class MyCrashHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {
        System.out.println("23333333");
    }
}
