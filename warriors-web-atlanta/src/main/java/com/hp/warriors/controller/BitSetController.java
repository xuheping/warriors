package com.hp.warriors.controller;

import java.util.BitSet;

public class BitSetController {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(Integer.MAX_VALUE);
        bitSet.set(100000,true);
        bitSet.set(1,true);
        System.out.println(bitSet);
    }
}
