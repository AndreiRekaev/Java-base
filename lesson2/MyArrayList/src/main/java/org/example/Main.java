package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int iterations = 50000;
        int size = 100;

        // Сравнение добавления
        System.out.println("Сравнение добавления:");
        long startTime = System.nanoTime();
        List<Integer> originalList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            originalList.add(new Random().nextInt(size));
        }
        long endTime = System.nanoTime();
        double avgTimeOriginal = ((endTime - startTime) / 1e9) / iterations;

        startTime = System.nanoTime();
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i < iterations; i++) {
            myArrayList.add(new Random().nextInt(size));
        }
        endTime = System.nanoTime();
        double avgTimeMyArrayList = ((endTime - startTime) / 1e9) / iterations;

        System.out.printf("Average time for ArrayList: %.9f sec%n", avgTimeOriginal);
        System.out.printf("Average time for MyArrayList: %.9f sec%n", avgTimeMyArrayList);
        System.out.printf("Ratio: %.2f%n", avgTimeOriginal / avgTimeMyArrayList);

        // Сравнение получения элементов
        System.out.println("\nСравнение получения элементов:");
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            originalList.get(i % originalList.size());
        }
        endTime = System.nanoTime();
        double avgTimeGetOriginal = ((endTime - startTime) / 1e9) / iterations;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            myArrayList.get(i % myArrayList.size());
        }
        endTime = System.nanoTime();
        double avgTimeGetMyArrayList = ((endTime - startTime) / 1e9) / iterations;

        System.out.printf("Average time for ArrayList get: %.9f sec%n", avgTimeGetOriginal);
        System.out.printf("Average time for MyArrayList get: %.9f sec%n", avgTimeGetMyArrayList);
        System.out.printf("Ratio: %.2f%n", avgTimeGetOriginal / avgTimeGetMyArrayList);

        // Сравнение сортировки
        System.out.println("\nСравнение сортировки:");
        startTime = System.nanoTime();
        List<Integer> originalListSorted = new ArrayList<>(originalList);
        myArrayList.clear();
        myArrayList.addAll(originalListSorted);
        Collections.sort(originalListSorted); // Оригинальная сортировка ArrayList
        endTime = System.nanoTime();
        double avgTimeSortOriginal = ((endTime - startTime) / 1e9);

        startTime = System.nanoTime();
        myArrayList.sort(); // Сортировка с использованием интерфейса Comparable
        endTime = System.nanoTime();
        double avgTimeSortMyArrayListComparable = ((endTime - startTime) / 1e9);

        startTime = System.nanoTime();
        myArrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }); // Сортировка с использованием компаратора
        endTime = System.nanoTime();
        double avgTimeSortMyArrayListComparator = ((endTime - startTime) / 1e9);

        System.out.printf("Average time for ArrayList sort: %.9f sec%n", avgTimeSortOriginal);
        System.out.printf("Average time for MyArrayList sort (Comparable): %.9f sec%n", avgTimeSortMyArrayListComparable);
        System.out.printf("Average time for MyArrayList sort (Comparator): %.9f sec%n", avgTimeSortMyArrayListComparator);
        System.out.printf("Ratio (Comparable): %.2f%n", avgTimeSortOriginal / avgTimeSortMyArrayListComparable);
        System.out.printf("Ratio (Comparator): %.2f%n", avgTimeSortOriginal / avgTimeSortMyArrayListComparator);

        // Проверка равенства коллекций после сортировки
        boolean eq = true;
        for (int i = 0; i < originalListSorted.size(); i++) {
            if (!Objects.equals(originalListSorted.get(i), myArrayList.get(i))) {
                eq = false;
                break;
            }
        }
        System.out.println("Collections equals: " + eq);

        // Сравнение удаления элементов
        System.out.println("\nСравнение удаления элементов:");
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            originalList.remove(i % originalList.size());
        }
        endTime = System.nanoTime();
        double avgTimeRemoveOriginal = ((endTime - startTime) / 1e9) / iterations;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            myArrayList.remove(i % myArrayList.size());
        }
        endTime = System.nanoTime();
        double avgTimeRemoveMyArrayList = ((endTime - startTime) / 1e9) / iterations;

        System.out.printf("Average time for ArrayList remove: %.9f sec%n", avgTimeRemoveOriginal);
        System.out.printf("Average time for MyArrayList remove: %.9f sec%n", avgTimeRemoveMyArrayList);
        System.out.printf("Ratio: %.2f%n", avgTimeRemoveOriginal / avgTimeRemoveMyArrayList);

        // Сравнение очистки
        for (int i = 0; i < iterations; i++) {
            originalList.add(new Random().nextInt(size));
        }
        for (int i = 0; i < iterations; i++) {
            myArrayList.add(new Random().nextInt(size));
        }
        System.out.println("\nСравнение очистки:");
        startTime = System.nanoTime();
        originalList.clear();
        endTime = System.nanoTime();
        double avgTimeClearOriginal = ((endTime - startTime) / 1e9);

        startTime = System.nanoTime();
        myArrayList.clear();
        endTime = System.nanoTime();
        double avgTimeClearMyArrayList = ((endTime - startTime) / 1e9);

        System.out.printf("Average time for ArrayList clear: %.9f sec%n", avgTimeClearOriginal);
        System.out.printf("Average time for MyArrayList clear: %.9f sec%n", avgTimeClearMyArrayList);
        System.out.printf("Ratio: %.2f%n", avgTimeClearOriginal / avgTimeClearMyArrayList);

        // Проверка работы с различными типами данных
        String[] types = {"Integer", "Double", "String"};
        for (String type : types) {
            System.out.println("\nПроверка работы с " + type + ":");
            MyArrayList<Object> myArrayListGeneric = new MyArrayList<>();

            for (int i = 0; i < 5; i++) {
                myArrayListGeneric.add(randomValue(type));
            }

            System.out.printf(myArrayListGeneric.toString());
        }
    }

    private static Object randomValue(String type) {
        switch (type) {
            case "Integer":
                return new Random().nextInt(100);
            case "Double":
                return new Random().nextDouble();
            case "String":
                return "Random string";
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
