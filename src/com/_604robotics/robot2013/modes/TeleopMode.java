package com._604robotics.robot2013.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;

public class TeleopMode extends Coordinator {
    private final XboxController driveController = new XboxController(1);
    private final XboxController manipController = new XboxController(2);
    
    public void apply (ModuleManager modules) {
        // Bind joysticks to Tank Drive
        this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "left", driveController.leftStick.Y));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "right", driveController.rightStick.Y));
        
        // Bind manual shooter rotation to manipulator joystick
        this.fill(new DataWire(modules.getModule("Rotation").getAction("Manual"), "power", manipController.leftStick.Y));
        
        // Bind frisbee stucking to manipulator button
        this.bind(new Binding(modules.getModule("Feeder").getAction("On"), manipController.buttons.LB));
    }
}
