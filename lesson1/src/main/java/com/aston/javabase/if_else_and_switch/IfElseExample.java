package com.aston.javabase.if_else_and_switch;

public class IfElseExample {

    public static void main(String[] args) {

        String result = exampleOfIF("DOG");
        System.out.println(result);
    }

    public static String exampleOfIF(String animal) {

        String result;

        if (animal.equals("DOG") || animal.equals("CAT")) {
            result = "domestic animal";
        } else if (animal.equals("TIGER")) {
            result = "wild animal";
        } else {
            result = "unknown animal";
        }
        return result;
    }
}
