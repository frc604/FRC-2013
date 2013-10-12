package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.networking.IndexedTable.Slice;

public class TriggerReference implements TriggerAccess {
    private final Trigger trigger;
    private final Slice value;
    
    private TriggerAccess inverse = null;
    
    private class TriggerNot implements TriggerAccess {
        private final TriggerAccess source;
        
        public TriggerNot (TriggerAccess source) {
            this.source = source;
        }
        
        public boolean get () {
            return !source.get();
        }
    }
    
    public TriggerReference (Trigger trigger, Slice value) {
        this.trigger = trigger;
        this.value = value;
    }
    
    public TriggerAccess not () {
        if (this.inverse == null) {
            this.inverse = new TriggerNot(this);
        }
        
        return this.inverse;
    }
    
    public boolean get () {
        return this.value.getBoolean(false);
    }
    
    public void begin () {
        this.trigger.begin();
    }
    
    public void update () {
        this.value.putBoolean(this.trigger.run());
    }
    
    public void end () {
        this.trigger.end();
    }
}
