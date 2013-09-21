package com._604robotics.robotnik.action;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class ActionController {
    private final Hashtable actionTable = new Hashtable();
    
    private String defaultAction = "";
    
    protected abstract String pickAction (String lastAction, String triggeredAction);
    
    public void add (String name, Action action) {
        this.actionTable.put(name, action);
    }
    
    public void addDefault (String name, Action action) {
        this.add(name, action);
        this.defaultAction = name;
    }
    
    protected String getDefaultAction () {
        return this.defaultAction;
    }
    
    protected Enumeration getActionNames () {
        return this.actionTable.keys();
    }
    
    protected Action getAction (String name) {
        return (Action) this.actionTable.get(name);
    }
}