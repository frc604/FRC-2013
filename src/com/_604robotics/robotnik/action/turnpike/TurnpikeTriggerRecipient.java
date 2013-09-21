package com._604robotics.robotnik.action.turnpike;

import com._604robotics.robotnik.data.DataReference;
import com._604robotics.robotnik.trigger.TriggerRecipient;
import com._604robotics.robotnik.trigger.TriggerReference;

public class TurnpikeTriggerRecipient implements TriggerRecipient {
    private final Turnpike turnpike;
    
    private final DataReference dataRef;
    private final TriggerReference triggerRef;
    
    public TurnpikeTriggerRecipient (Turnpike turnpike, DataReference value) {
        this.turnpike = turnpike;
        
        this.dataRef = value;
        this.triggerRef = null;
    }
    
    public TurnpikeTriggerRecipient (Turnpike turnpike, TriggerReference value) {
        this.turnpike = turnpike;
        
        this.dataRef = null;
        this.triggerRef = value;
    }
    
    public void sendTrigger (double precedence) {
        double value;
        
        if (this.dataRef != null) {
            value = this.dataRef.get();
        } else {
            value = this.triggerRef.get() ? 1D : 0D;
        }
        
        this.turnpike.sendTrigger(precedence > 0, value);
    }
}
