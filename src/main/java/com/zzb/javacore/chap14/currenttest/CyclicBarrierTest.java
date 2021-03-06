package com.zzb.javacore.chap14.currenttest;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier, i * 10000).start();
        }
    }

}

class Writer extends Thread {
    private CyclicBarrier cyclicBarrier;
    private int pause;

    public Writer(CyclicBarrier cyclicBarrier, int pause) {
        this.cyclicBarrier = cyclicBarrier;
        this.pause = pause;
    }

    public void run() {
        System.out.println("Thread : " + Thread.currentThread().getName() + " writing data !");
        try {
            Thread.sleep(pause);
            System.out.println("Thread : " + Thread.currentThread().getName() + " done , and " +
                    "wait others .");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("All thread done !!");

    }
}
