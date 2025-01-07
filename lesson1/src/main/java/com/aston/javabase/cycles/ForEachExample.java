package com.aston.javabase.cycles;

public class ForEachExample {

    public static void main(String[] args) {

        String[] daysOfWeek = { "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье" };


        for (String dayOfWeek : daysOfWeek) {
            System.out.println(dayOfWeek);
        }
    }
}

//Понедельник
//Вторник
//Среда
//Четверг
//Пятница
//Суббота
//Воскресенье
