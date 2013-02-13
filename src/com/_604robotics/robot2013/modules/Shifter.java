package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Solenoid;

public class Shifter extends Module {
    // FIXME: Get the right port for the Single Action Solenoid.
    private final Solenoid solenoid = new Solenoid(1);
    
    public Shifter () {
        this.set(new ElasticController() {{
            addDefault("Low Power", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(false);
                }
            });
        }});
    }
}
