package com._604robotics.robotnik.action.field;

public class Field {
    private final String name;
    private final double value;
    
    public Field (String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public double getValue() {
        return this.value;
    }
}
