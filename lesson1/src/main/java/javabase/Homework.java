package com.aston.javabase;

import java.util.Arrays;
import java.util.HashSet;

public class Homework {
    // Проверку вставим в онлайн-компилятор или создадим класс Main
//    public static void main(String[] args) {
//        turnString("I love Java");
//        int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
//        getDistinctNumbers(ints);
//        int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
//        System.out.println(findSecondMaxElement(arr));
//        lengthOfLastWord("    fly me    to the moon    ");
//        System.out.println(isPalindrome("112233"));
//    }
    // Перевернуть строку и вывести на консоль
    //  String string = "I love Java";
    public static void turnString(String string) {
        String result = "";
        for (int i = string.length() - 1; i >=0; i--) {
            result = result + string.charAt(i);
        }
        System.out.println(result);
//        StringBuilder sb = new StringBuilder(string);
//        System.out.println(sb.reverse());
// ============================================
//        if (string.isEmpty()) {
//            return;
//        }
//        char lastChar = string.charAt(string.length() - 1);
//        System.out.print(lastChar);
//        turnString(string.substring(0, string.length() - 1));
    }

    // int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    // Удалить дубликаты из массива и вывести в консоль
    public static void getDistinctNumbers(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[i] == ints[j]) {
                    ints[j] = -1;
                }
            }
        }
        for (int i = 0; i < ints.length - 1; i++) {
            if (ints[i] != -1) {
                System.out.print(ints[i] + ", ");
            }
        }
        System.out.print(ints[ints.length - 1]);
        System.out.println();
//        HashSet<Integer> uniqueNumbers = new HashSet<>();
//        for (int num : ints) {
//            uniqueNumbers.add(num);
//        }
//        System.out.println(uniqueNumbers);
    }

    // Дан массив, заполненный уникальными значениями типа int.
    // int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
    public static Integer findSecondMaxElement(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr[arr.length - 2];
//        if (arr.length < 2) {
//            return null;
//        }
//        Arrays.sort(arr);
//        return arr[arr.length - 2];
    }

    // Найти длину последнего слова в строке. В строке только буквы и пробелы.
    // "Hello world" - 5
    // "    fly me    to the moon    " - 4
    public static Integer lengthOfLastWord(String string) {
        int cnt = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) != ' ') {
                while (string.charAt(i) != ' ') {
                    cnt++;
                    i--;
                }
                return cnt;
            }
        }
        return null;
    }

    // Определить, что строка является палиндромом
    // Сложность по памяти O(1), не создавать новые String, StringBuilder
    // Примеры:
    // abc - false
    // 112233 - false
    // aba - true
    // 112211 - true
    public static boolean isPalindrome(String string) {
        int left = 0;
        int right = string.length() - 1;

        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
