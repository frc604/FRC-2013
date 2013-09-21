package com._604robotics.robotnik.utils;

import edu.wpi.first.wpilibj.PIDOutput;

public class ThresholdPIDOutput implements PIDOutput {
    private final PIDOutput out;
    private final double threshold;
    
    private double last = 0D;
    
    public ThresholdPIDOutput (PIDOutput out, double threshold) {
        this.out = out;
        this.threshold = threshold;
    }
    
    public void pidWrite (double input) {
        if (input == 0D || Math.abs(this.last - input) > this.threshold) {
            this.out.pidWrite(input);
            this.last = input;
        }
    }
}
