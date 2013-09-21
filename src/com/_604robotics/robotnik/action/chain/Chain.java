package com._604robotics.robotnik.action.chain;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.trigger.TriggerAccess;
import java.util.Vector;

public class Chain implements DataAccess {
    private final TriggerAccess trigger;
    
    private Vector items = new Vector();
    private int step = 0;
    
    private boolean last = false;
    
    public Chain (TriggerAccess trigger) {
        this.trigger = trigger;
    }
        
    public ChainTrigger generate (double value) {
        this.items.addElement(Double.toString(value));
        return new ChainTrigger(this, this.items.size() - 1);
    }
    
    public boolean onStep (int step) {
        return this.step == step;
    }
    
    public double get () {
        final boolean current = this.trigger.get();
        
        if (!this.last && current) {
            this.step++;
        }
        
        this.last = current;
        
        if (this.step >= this.items.size()) {
            return 0D;
        } else {
            return Double.parseDouble((String) this.items.elementAt(this.step));
        }
    }
}
