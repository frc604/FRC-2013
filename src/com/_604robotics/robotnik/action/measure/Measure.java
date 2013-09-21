package com._604robotics.robotnik.action.measure;

import com._604robotics.robotnik.trigger.TriggerAccess;
import java.util.Enumeration;
import java.util.Vector;

public abstract class Measure {
    private final Vector awaitList = new Vector();
    
    public final TriggerAccess yes;
    public final TriggerAccess no;
    
    private boolean ready = false;
    private boolean flipped = false;
    
    private boolean initialized = false;
    
    public Measure () {
        this.yes = new MeasureTrigger(this, true);
        this.no = new MeasureTrigger(this, false);
    }
    
    protected abstract boolean check ();
    
    protected void initialize () {};
    protected void complete () {};
    
    protected boolean get () {
        if (!this.initialized) {
            this.initialize();
            this.initialized = true;
        }
        
        final boolean last = this.flipped;
        
        this.flipped = this.isReady() && (this.flipped || this.check());
        
        if (!last && this.flipped) {
            this.complete();
        }
        
        return this.flipped;
    }
    
    public void await (TriggerAccess trigger) {
        this.awaitList.addElement(trigger);
    }
    
    private boolean isReady () {
        if (!this.ready) {
            this.ready = true;
            
            if (!this.awaitList.isEmpty()) {
                Enumeration triggers = this.awaitList.elements();
                TriggerAccess trigger;
                
                while (triggers.hasMoreElements()) {
                    trigger = (TriggerAccess) triggers.nextElement();
                    
                    if (!trigger.get()) {
                        this.ready = false;
                        break;
                    }
                }
            }
        }
        
        return this.ready;
    }
}
