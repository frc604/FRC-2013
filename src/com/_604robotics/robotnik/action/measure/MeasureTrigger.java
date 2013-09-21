package com._604robotics.robotnik.action.measure;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class MeasureTrigger implements TriggerAccess {
    private final Measure measure;
    private final boolean polarity;
    
    public MeasureTrigger (Measure measure, boolean polarity) {
        this.measure = measure;
        this.polarity = polarity;
    }
    
    public boolean get () {
        return this.measure.get() == this.polarity;
    }
}
