package com._604robotics.robotnik.prefabs.devices;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class MultiVictor implements PIDOutput {
    private final Victor[] victors;
    
    public MultiVictor (Victor[] victors) {
        this.victors = victors;
    }
    
    public void set (double value) {
        for (int i = 0; i < this.victors.length; i++) {
            this.victors[i].set(value);
        }
    }

    public void pidWrite (double value) {
        this.set(value);
    }    
}
