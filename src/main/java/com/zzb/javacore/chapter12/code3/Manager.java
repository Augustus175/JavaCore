package com.zzb.javacore.chapter12.code3;

public class Manager extends Employee {
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "This is Manager " + this.name;
    }
}
