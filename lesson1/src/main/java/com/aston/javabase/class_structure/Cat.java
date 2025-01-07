package com.aston.javabase.class_structure;

public final class Cat {

    private final boolean isWild;
    private static final String description;
    private final String color;

    {
        isWild = true;
    }

    static {
        description = "Our description";
    }

    public Cat(String color) {
        this.color = color;
    }
}
