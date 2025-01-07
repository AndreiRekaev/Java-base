package com.aston.javabase.order_of_initialization;

public class Solution {
    public static void main(String[] args) {
        new ChildClass();
        System.out.println();
        new ChildClass();
    }
}
