package com.aston.javabase.order_of_initialization;

public class ParentClass {
    static {
        System.out.println("Статический блок №1 родительского класса.");
    }

    public static String parentStatic = setParentStatic();

    static {
        System.out.println("Статический блок №2 родительского класса.");
    }

    {
        System.out.println("Блок инициализации №1 родительского класса.");
    }

    public String parentNonStatic = setParentNonStatic();

    {
        System.out.println("Блок инициализации №2 родительского класса.");
    }

    public ParentClass() {
        System.out.println("Конструктор родительского класса.");
    }

    private String setParentNonStatic() {
        System.out.println("Не статическое поле родительского класса.");
        return "parentNonStatic";
    }

    private static String setParentStatic() {
        System.out.println("Статическое поле родительского класса.");
        return "parentStatic";
    }

    public String setChildNonStatic1() {
        System.out.println("Не статическое поле дочернего класса №1.");
        return "childNonStatic2" + parentNonStatic;
    }
}
