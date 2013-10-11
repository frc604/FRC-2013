package com._604robotics.robotnik.meta;

import java.util.Enumeration;
import java.util.Hashtable;

public class Iterator {
    private final Enumeration keys;
    private final Enumeration values;
    
    public Object key = null;
    public Object value = null;

    public Iterator (Hashtable table) {
        this.keys = table.keys();
        this.values = table.elements();
    }

    public boolean next () {
        this.key = this.keys.nextElement();
        this.value = this.values.nextElement();

        return this.key == null && this.value == null;
    }
}
