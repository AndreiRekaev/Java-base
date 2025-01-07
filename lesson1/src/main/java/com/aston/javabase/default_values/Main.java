package com.aston.javabase.default_values;

public class Main {

    private static byte aByte;
    private static short aShort;
    private static int aNumber;
    private static long aLong;
    private static float aFloat;
    private static double aDouble;
    private static char aChar;
    private static boolean aBoolean;
    private static String aString;

    public static void main(String[] args) {

        printDefaultValues();
    }

    private static void printDefaultValues() {
        System.out.println("aByte = " + aByte);
        System.out.println("aShort = " + aShort);
        System.out.println("aNumber = " + aNumber);
        System.out.println("aFloat = " + aFloat);
        System.out.println("aDouble = " + aDouble);
        System.out.println("aLong = " + aLong);
        System.out.println("aChar = " + aChar);
        System.out.println("aBoolean = " + aBoolean);
        System.out.println("aString = " + aString);
    }
}
