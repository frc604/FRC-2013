package com._604robotics.robotnik.prefabs.controller;

import com._604robotics.robotnik.data.DataAccess;
import edu.wpi.first.wpilibj.Joystick;

public class ControllerAxis implements DataAccess {
    private final Joystick joystick;
    private final int axis;
    
    public ControllerAxis (Joystick joystick, int axis) {
        this.joystick = joystick;
        this.axis = axis;
    }
    
    public double get () {
        return this.joystick.getRawAxis(this.axis);
    }
}
