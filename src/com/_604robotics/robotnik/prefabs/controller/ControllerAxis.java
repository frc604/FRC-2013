package com._604robotics.robotnik.prefabs.controller;

import com._604robotics.robotnik.data.DataAccess;
import edu.wpi.first.wpilibj.Joystick;

public class ControllerAxis implements DataAccess {
    private final Joystick joystick;
    private final int axis;
    
    private double deadband = 0D;
    
    public ControllerAxis (Joystick joystick, int axis) {
        this.joystick = joystick;
        this.axis = axis;
    }
    
    public double get () {
        final double value = this.joystick.getRawAxis(this.axis);
        
        return Math.abs(value) < this.deadband ? 0D : value;
    }
    
    public void setDeadband (double deadband) {
        this.deadband = deadband;
    }
}
