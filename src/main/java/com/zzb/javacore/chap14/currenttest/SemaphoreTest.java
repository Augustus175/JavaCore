package com.zzb.javacore.chap14.currenttest;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        int N = 8;
        int pause = 10;
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore, i).start();
        }
    }

}

class Worker extends Thread {
    private int num;
    private Semaphore semaphore;
    private int pause;
    private Random random;

    public Worker(int num, Semaphore semaphore, int pause) {
        this.num = num;
        this.semaphore = semaphore;
        this.pause = pause;
        random = new Random(10);
    }


    public void run() {
        try {
            semaphore.acquire();
            System.out.println("The worker : " + num
                    + " acquire a machine !");
            int p = pause * pause* pause * 100;
            System.out.println("now the " + num + " sleep " + p + " ms!");
            Thread.sleep(p);
            semaphore.release();
            System.out.println("The worker : " + num
                    + " release the machine !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
