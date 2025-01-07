package com.aston.javabase.if_else_and_switch;

public class SwitchExample {

    public static void main(String[] args) {

        String result = exampleOfSwitch("DOG");
        System.out.println(result);
    }

    public static String exampleOfSwitch(String animal) {
        return switch (animal) {
            case "DOG", "CAT" -> "domestic animal";
            case "TIGER" -> "wild animal";
            default -> "unknown animal";
        };
    }
}
