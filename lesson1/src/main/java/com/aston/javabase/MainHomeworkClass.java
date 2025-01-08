package com.aston.javabase;

import java.util.Arrays;

public class MainHomeworkClass {
    public static void main(String[] args) {
        String string = "I love Java";
        System.out.println(Homework.turnString(string));

        int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(Homework.getDistinctNumbers(ints)));

        int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
        System.out.println(Homework.findSecondMaxElement(arr));

        String s = "Hello world";
        s = "    fly me    to the moon    ";
        System.out.println(Homework.lengthOfLastWord(s));

        s = "abc";
        // 112233 - falssad
        s = "aba";
        s = "112211";

        System.out.println(Homework.isPalindrome(s));
    }
}
