package com._604robotics.robotnik.action.chain;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class ChainTrigger implements TriggerAccess {
    private final Chain chain;
    private final int step;
    
    public ChainTrigger (Chain chain, int step) {
        this.chain = chain;
        this.step = step;
    }
    
    public boolean get () {
        System.out.println(this.step + ": " + this.chain.onStep(this.step));
        return this.chain.onStep(this.step);
    }
}
