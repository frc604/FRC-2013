package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import java.util.Enumeration;
import java.util.Vector;

public class Coordinator {
    private final Vector triggerBindings = new Vector();
    private final Vector dataWires = new Vector();
    
    protected void apply (ModuleManager modules) {}
    
    public void attach (ModuleManager modules) {
        this.triggerBindings.removeAllElements();
        this.dataWires.removeAllElements();
        
        this.apply(modules);
    }
    
    protected void bind (Binding binding) {
        this.triggerBindings.addElement(binding);
    }
    
    protected void fill (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    public void update () {
        final Enumeration wires = this.dataWires.elements();
        while (wires.hasMoreElements()) ConnectorProxy.pipe((DataWire) wires.nextElement());
        
        final Enumeration bindings = this.triggerBindings.elements();
        while (bindings.hasMoreElements()) ConnectorProxy.pipe((Binding) bindings.nextElement());
    }
}