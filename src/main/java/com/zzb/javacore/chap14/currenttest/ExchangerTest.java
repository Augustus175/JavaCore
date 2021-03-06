package com.zzb.javacore.chap14.currenttest;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        Bike bike = new Bike(10000, exchanger);
        Car car = new Car(10, exchanger);
        bike.start();
        car.start();
        System.out.println("------end------");
    }

}

class Bike extends Thread {
    private Exchanger exchanger;
    private int pause;

    public Bike(int pause, Exchanger exchanger) {
        this.pause = pause;
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            Thread.sleep(pause);
            System.out.println("bike done !");
            Car car = (Car) exchanger.exchange(this);
            System.out.println("now bike exchange " + exchanger.exchange(this));
            car.printCar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "the Bike";
    }

    public void printBike() {
        System.out.println("This is bike !");
    }

}

class Car extends Thread {
    private Exchanger exchanger;
    private int pause;

    public Car(int pause, Exchanger exchanger) {
        this.pause = pause;
        this.exchanger = exchanger;
    }

    public void run() {
        try {
            Thread.sleep(pause);
            System.out.println("car done !");
            Bike bike = (Bike) exchanger.exchange(this);
            System.out.println("now car exchange " + exchanger.exchange(this));
            bike.printBike();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "the Car";
    }

    public void printCar() {
        System.out.println("This is bike !");
    }

}
