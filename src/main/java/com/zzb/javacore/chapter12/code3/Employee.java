package com.zzb.javacore.chapter12.code3;

public class Employee {
    private String name;
    private int bonus;

    public Employee() {
    }

    public Employee(String name, int bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "This is " + this.getClass().getSimpleName() + this.name;
    }
}
