package com._604robotics.robotnik.trigger;

import java.util.Enumeration;
import java.util.Hashtable;

public class TriggerMap {
    private final Hashtable triggerTable = new Hashtable();
    
    protected void add (String name, Trigger trigger) {
        this.triggerTable.put(name, trigger);
    }
    
    protected Trigger getTrigger (String name) {
        return (Trigger) this.triggerTable.get(name);
    }
    
    protected Enumeration enumerateNames () {
        return this.triggerTable.keys();
    }
}
