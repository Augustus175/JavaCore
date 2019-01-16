package com.zzb.javacore.chapter12;

import java.util.ArrayList;
import java.util.List;

public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min is " + mm.getFirst());
        System.out.println("max is " + mm.getSecond());
        double middle = ArrayAlg.getMiddle(3.14, 1729D, 0D);
        Pair<String>[] table = new Pair[10];
        List<Pair<String>> list = new ArrayList<>();
        list.add(new Pair<>());
        Pair<String> pair = list.get(0);
        String str = pair.getFirst();
    }

}

class ArrayAlg {
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }

        return new Pair<String>(min, max);
    }

    static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    static <T extends Comparable> T min(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T smallest = a[0];
        for (int i = 0; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {
                smallest = a[i];
            }
        }
        return smallest;
    }
}
