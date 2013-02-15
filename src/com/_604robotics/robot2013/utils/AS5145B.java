package com._604robotics.robot2013.utils;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDSource;

public final class AS5145B implements PIDSource {
    private final AnalogChannel analogChannel;
    //FIXME: Get the acutal analog port number of the shooter encoder

    public AS5145B(int analogport){
        this.analogChannel = new AnalogChannel(analogport);
    }

    public double getAngle() {
        return (this.analogChannel.getAverageVoltage()*72D);
    }
    
    public double pidGet() {
        return (this.getAngle());
    }
}