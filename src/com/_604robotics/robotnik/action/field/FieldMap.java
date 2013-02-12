package com._604robotics.robotnik.action.field;

import java.util.Enumeration;
import java.util.Vector;

public class FieldMap {
    private final Vector fields = new Vector();
    
    public void define (String name, double value) {
        this.fields.addElement(new Field(name, value));
    }
    
    public Enumeration getFields () {
        return this.fields.elements();
    }
}
