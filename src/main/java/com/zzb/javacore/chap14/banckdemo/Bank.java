package com.zzb.javacore.chap14.banckdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock banckLock;
    private Condition sufficientFuns;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }
        banckLock = new ReentrantLock();
        sufficientFuns = banckLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        banckLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFuns.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d ", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total Balance: %10.2f ", getTotalBalance());
            sufficientFuns.signalAll();
        } finally {
            banckLock.unlock();
        }
    }

    private double getTotalBalance() {
        banckLock.lock();
        try {
            double sum = 0;
            for (double a :
                    accounts) {
                sum += a;
            }
            return sum;
        } finally {
            banckLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}
