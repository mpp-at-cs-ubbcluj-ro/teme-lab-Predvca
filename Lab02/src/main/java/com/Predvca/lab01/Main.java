package com.Predvca.lab01;

import com.google.common.base.Joiner;

public class Main {
    public static void main(String[] args) {
        String rezultat = Joiner.on(", ").join("1", "2", "3", "4");
        System.out.println(rezultat);
    }
}
