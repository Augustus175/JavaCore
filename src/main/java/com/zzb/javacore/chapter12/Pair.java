package com.zzb.javacore.chapter12;

//public class Pair<T extends Persion> {
public class Pair<T> {

    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static void main(String[] args) {
        Pair<Persion> pair1 = new Pair<>();
        Pair<Persion> pair2 = new Pair<>();
        System.out.println(pair1.getClass() == pair2.getClass());
    }
}

class Persion {
}

class Student extends Persion {
}
