package com.zzb.javacore;

public class StaticEntityTest {
    static class StaticEntity {
    }

    public static void main(String[] args) {
        StaticEntity staticEntity1 = new StaticEntity();
        StaticEntity staticEntity2 = new StaticEntity();
        System.out.println(staticEntity1);
        System.out.println(staticEntity2);
        System.out.println(staticEntity1 == staticEntity2);
    }
}

