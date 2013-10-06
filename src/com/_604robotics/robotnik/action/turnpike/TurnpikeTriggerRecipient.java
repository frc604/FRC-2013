package com._604robotics.robotnik.action.turnpike;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;

public class TurnpikeTriggerRecipient implements TriggerRecipient {
    private final Turnpike turnpike;
    
    private final DataAccess data;
    private final TriggerAccess trigger;
    
    public TurnpikeTriggerRecipient (Turnpike turnpike, DataAccess value) {
        this.turnpike = turnpike;
        
        this.data = value;
        this.trigger = null;
    }
    
    public TurnpikeTriggerRecipient (Turnpike turnpike, TriggerAccess value) {
        this.turnpike = turnpike;
        
        this.data = null;
        this.trigger = value;
    }
    
    public void sendTrigger (double precedence) {
        double value;
        
        if (this.data != null) {
            value = this.data.get();
        } else {
            value = this.trigger.get() ? 1D : 0D;
        }
        
        this.turnpike.sendTrigger(precedence > 0, value);
    }
}
