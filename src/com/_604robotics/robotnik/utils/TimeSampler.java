package com._604robotics.robotnik.utils;

import edu.wpi.first.wpilibj.Timer;

public class TimeSampler {
    private final Timer timer = new Timer();
    
    private final String name;
    private final double time;
    
    private int samples;
    
    public TimeSampler (String name, double time) {
        this.name = name;
        this.time = time;
    }
    
    public void start () {
        this.samples = 0;
        this.timer.start();
    }
    
    public void sample () {
        this.samples++;
        
        if (this.timer.get() >= this.time) {
            System.out.println(" --- " + name + " time: " + (this.timer.get() / this.samples) + " ms");

            this.samples = 0;
            this.timer.reset();
        }
    }
    
    public void stop () {
        this.timer.stop();
        this.timer.reset();
    }
}
