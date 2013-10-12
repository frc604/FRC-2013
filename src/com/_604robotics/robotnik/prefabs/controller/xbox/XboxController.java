package com._604robotics.robotnik.prefabs.controller.xbox;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController {
    public final XboxControllerButtons buttons;
    
    public final XboxControllerStick leftStick;
    public final XboxControllerStick rightStick;
    
    public XboxController (int port) {
        final Joystick joystick = new Joystick(port);
        
        this.buttons = new XboxControllerButtons(joystick);
        
        this.leftStick  = new XboxControllerStick(joystick, 1, 2);
        this.rightStick = new XboxControllerStick(joystick, 4, 5);
    }
}