package com._604robotics.robotnik.utils;

import edu.wpi.first.wpilibj.PIDOutput;
import java.util.Enumeration;
import java.util.Vector;

public class AveragingPIDOutput implements PIDOutput {
    private final PIDOutput out;
    private final int samples;
    
    private Vector recorded;
    
    public AveragingPIDOutput (PIDOutput out, int samples) {
        this.out = out;
        this.samples = samples;
        this.recorded = new Vector(samples);
    }
    
    public void pidWrite (double input) {
        if (input == 0D) {
            this.recorded = new Vector(this.samples);
            this.out.pidWrite(0D);
            return;
        }
        
        this.recorded.addElement(Double.toString(input));
        
        while (this.recorded.size() > this.samples) {
            this.recorded.removeElementAt(0);
        }
        
        double sum = 0D;
        
        Enumeration data = this.recorded.elements();
        while(data.hasMoreElements()) {
            sum += Double.parseDouble((String) data.nextElement());
        }
        
        this.out.pidWrite(sum / this.recorded.size());
    }
}
