package com._604robotics.robotnik.action.turnpike;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class TurnpikeTrigger implements TriggerAccess {
    private final Turnpike turnpike;
    
    public TurnpikeTrigger (Turnpike turnpike) {
        this.turnpike = turnpike;
    }
    
    public boolean get () {
        return this.turnpike.getTriggerValue();
    }
}
