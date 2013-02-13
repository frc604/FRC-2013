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
        this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "left", driveController.leftStick.Y));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "right", driveController.rightStick.Y));
        
        this.fill(new DataWire(modules.getModule("Rotation").getAction("Manual"), "power", manipController.leftStick.Y));
        
        // Bind shifting to manipulator controller
        this.bind(new Binding(modules.getModule("Shifter").getAction("High Power"), driveController.buttons.RT));
    }
}
