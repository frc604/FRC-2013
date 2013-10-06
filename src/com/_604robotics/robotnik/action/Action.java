package com._604robotics.robotnik.action;

import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.ModuleReference;
import java.util.Enumeration;
import java.util.Vector;

public abstract class Action {
    private final FieldMap fieldMap;
    
    public Action () {
        this.fieldMap = new FieldMap();
    }
    
    public Action (FieldMap fieldMap) {
        this.fieldMap = fieldMap;
    }
    
    public void begin (ActionData data) {}
    public void run (ActionData data) {}
    public void end (ActionData data) {}
    
    public void apply (ModuleReference module) {}
    
    protected Enumeration getFields () {
        return this.fieldMap.getFields();
    }
    
    protected Vector getFieldNames () {
        return this.fieldMap.getFieldNames();
    }
}