package com.aston.javabase;

import java.util.*;
import java.util.stream.IntStream;

public class Homework {

    // Перевернуть строку и вывести на консоль
    //String string = "I love Java";
    public static String turnString(String string) {
        StringBuilder sb = new StringBuilder(string);
        return sb.reverse().toString();
    }

    // int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    // Удалить дубликаты из массива и вывести в консоль
    public static int[] getDistinctNumbers(int[] ints) {
        //int[] ints2 = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        Integer[] arr2 = Arrays.stream(ints).boxed().toArray(Integer[]::new);
        HashSet<Integer> set = new HashSet<>(Arrays.asList(arr2));
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // Дан массив, заполненный уникальными значениями типа int.
    // int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
    public static Integer findSecondMaxElement(int[] arr) {
        //int[] arr2 = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
        Arrays.sort(arr);
        //System.out.println(arr2[arr2.length - 2]);
        return arr[arr.length-2];
    }

    // Найти длину последнего слова в строке. В строке только буквы и пробелы.
    // "Hello world" - 5
    // "    fly me    to the moon    " - 4
    public static Integer lengthOfLastWord(String string) {
        String[] s2 = string.split(" ");
        return s2[s2.length-1].length();
    }

    // Определить, что строка является палиндромом
    // Сложность по памяти O(1), не создавать новые String, StringBuilder
    // Примеры:
    // abc - false
    // 112233 - false
    // aba - true
    // 112211 - true
    public static boolean isPalindrome(String string) {
        return IntStream.range(0, string.length() / 2 )
                .allMatch(i -> string.charAt(i) == string.charAt(string.length()-i-1));
        //return string.equals(Homework.turnString(string));
    }
}
