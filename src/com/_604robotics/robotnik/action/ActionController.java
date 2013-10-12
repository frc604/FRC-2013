package com._604robotics.robotnik.action;

import com._604robotics.robotnik.meta.Iterator;
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
    
    protected Action getAction (String name) {
        return (Action) this.actionTable.get(name);
    }
    
    protected Iterator iterate () {
        return new Iterator(this.actionTable);
    }
}