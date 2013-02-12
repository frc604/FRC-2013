package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class TriggerAnd implements TriggerAccess {
    private final TriggerAccess[] triggers;
    
    public TriggerAnd (TriggerAccess[] triggers) {
        this.triggers = triggers;
    }
    
    public boolean get () {
        boolean process = true;
        
        for (int i = 0; i < this.triggers.length; i++) {
            process = this.triggers[i].get() && process;
        }
        
        return process;
    }
}