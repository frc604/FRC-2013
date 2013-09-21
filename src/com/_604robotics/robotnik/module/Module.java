package com._604robotics.robotnik.module;

import com._604robotics.robotnik.action.ActionController;
import com._604robotics.robotnik.action.controllers.DummyController;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.trigger.TriggerMap;
import java.util.Enumeration;
import java.util.Vector;

public abstract class Module {
    private DataMap dataMap = new DataMap();
    private TriggerMap triggerMap = new TriggerMap();
    
    private ActionController actionController = new DummyController();
    
    private final Vector triggerBindings = new Vector();
    private final Vector dataWires = new Vector();
    
    public void apply (ModuleReference module) {}
    
    protected void set (DataMap dataMap) {
        this.dataMap = dataMap;
    }
    
    protected void set (TriggerMap triggerMap) {
        this.triggerMap = triggerMap;
    }
    
    protected void set (ActionController actionController) {
        this.actionController = actionController;
    }
    
    protected void bind (Binding binding) {
        this.triggerBindings.addElement(binding);
    }
    
    protected void fill (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    protected DataMap getDataMap () {
        return this.dataMap;
    }
    
    protected TriggerMap getTriggerMap () {
        return this.triggerMap;
    }
    
    protected ActionController getActionController () {
        return this.actionController;
    }
    
    protected Enumeration getBindings () {
        return this.triggerBindings.elements();
    }
    
    protected Enumeration getWires () {
        return this.dataWires.elements();
    }
}
