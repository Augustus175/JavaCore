package com.zzb.javacore.chapter12.code3;

import com.zzb.javacore.chapter12.Pair;

import javax.sound.midi.Soundbank;

public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("CEO", 1000);
        Manager cfo = new Manager("CFO", 100);
        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);
        ceo.setBonus(1000);
        cfo.setBonus(100);
        Manager[] managers = {ceo, cfo};
        Pair<Employee> result = new Pair<>();
        minnaxBonus(managers, result);
        Employee employee = result.getFirst();
        String name = employee.getName();
        System.out.println("frst : " + result.getFirst().getName()
                + " second " + result.getSecond().getName());
        maxminBonus(managers, result);
        System.out.println("frst : " + result.getFirst().getName()
                + " second " + result.getSecond().getName());
    }

    public static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first + " and " + second + " are buddies.");
    }

    public static void minnaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a == null || a.length == 0) {
            return;
        }
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.getBonus() > a[i].getBonus()) {
                min = a[i];
            }
            if (max.getBonus() < a[i].getBonus()) {
                max = a[i];
            }

        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minnaxBonus(a, result);
        PairAlg.swap(result);
    }
}

class PairAlg {
    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p) {
        swapHelper(p);
    }

    public static <T> void swapHelper(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}
