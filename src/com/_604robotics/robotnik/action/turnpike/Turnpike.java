package com._604robotics.robotnik.action.turnpike;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.trigger.TriggerAccess;

// FIXME: I need a severe cleaning up!
public class Turnpike implements DataAccess {
    private final TriggerAccess refTrigger = new TurnpikeTrigger(this);
    
    private double value = 0D;
    
    private boolean active = false;
    private boolean last = false;
    
    private int totalCount = 0;
    private int receivedCount = 0;
    
    public TurnpikeTriggerRecipient generate (DataAccess ref) {
        this.totalCount++;
        return new TurnpikeTriggerRecipient(this, ref);
    }
    
    public TurnpikeTriggerRecipient generate (TriggerAccess ref) {
        this.totalCount++;
        return new TurnpikeTriggerRecipient(this, ref);
    }
    
    protected void sendTrigger (boolean active, double value) {
        this.receivedCount++;
        
        if (active) {
            this.value = value;
            this.active = true;
        }
        
        if (this.totalCount == this.receivedCount) {
            if (!this.active) {
                this.value = 0D;
            }
            
            this.last = this.active;
        }
    }
    
    protected boolean getTriggerValue () {
        return this.last;
    }
    
    public double get () {
        if (!this.active) {
            this.value = 0D;
        }
        
        this.last = this.active;
        this.active = false;
        
        return this.value;
    }
    
    public TriggerAccess getTrigger () {
        return this.refTrigger;
    }
}