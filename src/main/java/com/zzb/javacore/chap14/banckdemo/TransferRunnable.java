package com.zzb.javacore.chap14.banckdemo;

public class TransferRunnable implements Runnable {
    //    private Bank bank;
//    private SynchronizedBank bank;
    private ReentrantLockBank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY;

    public TransferRunnable(ReentrantLockBank bank, int fromAccount, double maxAmount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        service();
    }

    private void service() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
