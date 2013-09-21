package com._604robotics.robotnik.module;

import java.util.Enumeration;
import java.util.Hashtable;

public class ModuleMap {
    private final Hashtable moduleTable = new Hashtable();
    
    protected void add (String name, Module module) {
        this.moduleTable.put(name, module);
    }
    
    protected Enumeration getModuleNames () {
        return this.moduleTable.keys();
    }
    
    protected Module getModule (String name) {
        return (Module) this.moduleTable.get(name);
    }
}
