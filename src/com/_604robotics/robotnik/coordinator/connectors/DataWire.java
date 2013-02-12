package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.data.DataAccess;

public class DataWire {
    private final ActionReference action;
    
    private final String fieldName;
    private final DataAccess data;
    
    public DataWire (ActionReference action, String fieldName, DataAccess data) {
        this.action = action;
        
        this.fieldName = fieldName;
        this.data = data;
    }

    public ActionReference getAction () {
        return this.action;
    }

    public DataAccess getData () {
        return this.data;
    }

    public String getFieldName () {
        return this.fieldName;
    }
}