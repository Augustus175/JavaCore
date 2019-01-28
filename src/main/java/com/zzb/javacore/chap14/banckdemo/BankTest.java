package com.zzb.javacore.chap14.banckdemo;

public class BankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(bank, i, INITAL_BALANCE);
            Thread t = new Thread(r);
            t.setName("Thread " + i);
            t.start();
        }
    }
}
