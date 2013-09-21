package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PneumaticHanger extends Module {
    private final DoubleSolenoid solenoid = new DoubleSolenoid(5, 6);
    
    public PneumaticHanger() {
        this.set(new StateController() {{
            addDefault("Off", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kReverse);
                }
            });
            
            add("On", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kForward);
                }
                
                public void end (ActionData data) {
                    solenoid.set(Value.kReverse);
                }
            });
        }});
    }
}
