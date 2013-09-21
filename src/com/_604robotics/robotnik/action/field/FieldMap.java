package com._604robotics.robotnik.action.field;

import java.util.Enumeration;
import java.util.Vector;

public class FieldMap {
    private final Vector fields = new Vector();
    private final Vector fieldNames = new Vector();
    
    public void define (String name, double value) {
        this.fields.addElement(new Field(name, value));
        this.fieldNames.addElement(name);
    }
    
    public void define (String name, boolean value) {
        this.fields.addElement(new Field(name, value ? 1D : 0D));
        this.fieldNames.addElement(name);
    }
    
    public Enumeration getFields () {
        return this.fields.elements();
    }
    
    public Vector getFieldNames () {
        return this.fieldNames;
    }
}
