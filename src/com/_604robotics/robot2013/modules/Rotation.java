package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class Rotation extends Module {
    private final Victor victor = new Victor(3);
    
    // FIXME: Update with real-world port.
    private final DigitalInput hardStopSwitch = new DigitalInput(1);
    
    public Rotation () {
        this.set(new TriggerMap() {{
            add("At Hard Stop", new Trigger() {
                public boolean run () {
                    return hardStopSwitch.get();
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Manual", new Action(
                    new FieldMap() {{
                        define("power", 0D);
                    }}) {
                        public void run (ActionData data) {
                            victor.set(data.get("power"));
                        }
                        
                        public void end (ActionData data) {
                            victor.set(0D);
                        }
                    });
            
            add("Feed", new Action() {
                public void run (ActionData data) {
                    // FIXME: Check polarity and optimum motor speed.
                    if (!hardStopSwitch.get()) {
                        victor.set(-0.4D);
                    } else {
                        victor.set(0D);
                    }
                }
            });
        }});
    }
}
