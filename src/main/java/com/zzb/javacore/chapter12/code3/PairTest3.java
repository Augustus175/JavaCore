package com.zzb.javacore.chapter12.code3;

import com.zzb.javacore.chapter12.Pair;

import javax.sound.midi.Soundbank;

public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("CEO");
        Manager cfo = new Manager("CFO");
        Pair<Manager> buddies = new Pair<>(ceo, cfo);

        ceo.setBonus(1000);
        cfo.setBonus(100);
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
    }
}

class PairAlg {

}
