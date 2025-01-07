package com.aston.javabase.class_structure;

public class Dog extends Animal {

    private String name;
    private int age;


    // constructors

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(int age) {
        this.age = age;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printAnimalName() {
        System.out.println(super.name);
    }

    public void printDogName() {
        System.out.println(this.name);
    }

    public void eat() {
        System.out.println("Dog eating...");
    }
}
