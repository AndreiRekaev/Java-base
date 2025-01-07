package com.aston.javabase.class_structure;

public class ExampleSuperAndThis {

    public static void main(String args[]) {
        B b = new B();
    }
}

class A {

    A() {
        System.out.println("Конструктор без аргументов класса A");
    }

    A(String args){
        System.out.println("Конструктор с одним аргументом класса A");
    }
}

class B extends A {

    B() {
        this("");
        System.out.println("Конструктор без аргументов класса B");
    }

    B(String args) {
        super("");
        System.out.println("Конструктор с одним аргументом класса B");
    }
}

//Конструктор с одним аргументом класса A
//Конструктор с одним аргументом класса B
//Конструктор без аргументов класса B
