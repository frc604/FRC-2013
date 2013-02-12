package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class Binding {
    private final ActionReference action;
    private final TriggerAccess trigger;
    
    private final boolean safety;
    
    public Binding (ActionReference action, TriggerAccess trigger) {
        this.action = action;
        this.trigger = trigger;
        
        this.safety = false;
    }
    
    public Binding (ActionReference action, TriggerAccess trigger, boolean safety) {
        this.action = action;
        this.trigger = trigger;
        
        this.safety = safety;
    }

    public ActionReference getAction () {
        return this.action;
    }

    public TriggerAccess getTrigger () {
        return this.trigger;
    }

    public boolean isSafety () {
        return this.safety;
    }
}