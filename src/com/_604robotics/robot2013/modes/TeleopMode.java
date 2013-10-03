package com._604robotics.robot2013.modes;

import com._604robotics.robotnik.action.turnpike.Turnpike;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;

public class TeleopMode extends Coordinator {
    private final XboxController driveController = new XboxController(1);
    private final XboxController manipController = new XboxController(3 /* 2 */);
    
    public TeleopMode () {
        this.driveController.leftStick.Y.setFactor(-1D);
        this.driveController.rightStick.Y.setFactor(-1D);
        
        this.manipController.leftStick.Y.setDeadband(0.2);
    }
    
    public void apply (ModuleManager modules) {
        /* Drive Controller */
        {
            /* Drive */
            {
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "left",  driveController.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "right", driveController.rightStick.Y));
                
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "scaled", driveController.buttons.LB));
            }
            
            /* Hanging */
            {
                final TriggerToggle pneumaticHanger = new TriggerToggle(driveController.buttons.RT, false);
                
                this.bind(new Binding(modules.getModule("Pneumatic Hanger").getAction("Off"), pneumaticHanger.off));
                this.bind(new Binding(modules.getModule("Pneumatic Hanger").getAction("On"), pneumaticHanger.on));
            }
            
            /* Shifting */
            {
                final TriggerToggle highGear = new TriggerToggle(driveController.buttons.RB, false);
                this.bind(new Binding(modules.getModule("Shifter").getAction("High Power"), highGear.on));
            }
            
        }
        
        /* Manipulator Controller */
        {
            /* Shooter Rotation */
            {
                /* Manual */
                {
                    this.fill(new DataWire(modules.getModule("Rotation").getAction("Manual"), "power", manipController.leftStick.Y));
                }
                
                /* Stow */
                {
                    this.bind(new Binding(modules.getModule("Rotation").getAction("Stow"), manipController.buttons.B));
                }
            }
            
            /* Feeding */
            {
                /* Manual */
                {
                    this.bind(new Binding(modules.getModule("Feeder").getAction("Out"), manipController.buttons.LT));
                    this.bind(new Binding(modules.getModule("Feeder").getAction("In"), manipController.buttons.RT));
                }
                
                /* Station */
                {
                    this.bind(new Binding(modules.getModule("Feeder").getAction("In"), manipController.buttons.RightStick));
                    this.bind(new Binding(modules.getModule("Rotation").getAction("Load"), manipController.buttons.RightStick));
                }
            }
            
            /* Shooting */
            {
                /* Manual */
                {
                    this.bind(new Binding(modules.getModule("Shooter").getAction("On"), manipController.buttons.LB));
                }
                
                /* High, Mid, Low */
                {
                    final Turnpike angles = new Turnpike();
                    
                    this.bind(new Binding(angles.generate(modules.getModule("Targets").getData("Top Angle")), manipController.buttons.Y));
                    this.bind(new Binding(angles.generate(modules.getModule("Targets").getData("Middle Angle")), manipController.buttons.X));
                    this.bind(new Binding(angles.generate(modules.getModule("Targets").getData("Bottom Angle")), manipController.buttons.A));
                    this.bind(new Binding(angles.generate(modules.getModule("Dashboard").getData("Manual Aim Angle")), manipController.buttons.Back));
                    
                    this.bind(new Binding(modules.getModule("Rotation").getAction("Angle"), angles.getTrigger()));
                    this.fill(new DataWire(modules.getModule("Rotation").getAction("Angle"), "angle", angles));
                    
                    this.bind(new Binding(modules.getModule("Shooter").getAction("On"), angles.getTrigger()));
                }
            }
        }
    }
}
