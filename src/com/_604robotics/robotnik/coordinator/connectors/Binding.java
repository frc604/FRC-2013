package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;

public class Binding {
    private final TriggerRecipient recipient;
    private final TriggerAccess trigger;
    
    private final boolean safety;
    
    public Binding (TriggerRecipient recipient, TriggerAccess trigger) {
        this.recipient = recipient;
        this.trigger = trigger;
        
        this.safety = false;
    }
    
    public Binding (ActionReference action, TriggerAccess trigger, boolean safety) {
        this.recipient = action;
        this.trigger = trigger;
        
        this.safety = safety;
    }

    public TriggerRecipient getRecipient () {
        return this.recipient;
    }

    public TriggerAccess getTrigger () {
        return this.trigger;
    }

    public boolean isSafety () {
        return this.safety;
    }
}