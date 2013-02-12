package com._604robotics.robotnik.data;

import java.util.Enumeration;
import java.util.Hashtable;

public class DataMap {
    private final Hashtable dataTable = new Hashtable();
    
    protected void add (String name, Data data) {
        this.dataTable.put(name, data);
    }
    
    protected Enumeration getDataNames () {
        return this.dataTable.keys();
    }
    
    protected Data getData (String name) {
        return (Data) this.dataTable.get(name);
    }
}
