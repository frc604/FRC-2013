package com._604robotics.robotnik.prefabs.measure;

import com._604robotics.robotnik.action.measure.Measure;
import edu.wpi.first.wpilibj.Timer;

public class TimeMeasure extends Measure {
    private final Timer timer = new Timer();
    private final double time;
    
    public TimeMeasure (double time) {
        this.time = time;
    }
    
    protected void initialize () {
        this.timer.start();
    }

    protected boolean check () {
        return this.timer.get() > this.time;
    }
    
    protected void complete () {
        this.timer.stop();
    }
}
