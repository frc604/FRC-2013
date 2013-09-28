package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;

public class Bucket extends Module {
    private final DoubleSolenoid solenoid = new DoubleSolenoid(3, 4);
    
    public Bucket () {
        this.set(new TriggerMap() {{
            add("Deployed", new Trigger() {
                public boolean run () {
                    return solenoid.get().equals(Value.kForward);
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kReverse);
                }
            });
            
            add("On", new Action() {
                private final Timer timer = new Timer();

                public void begin (ActionData data) {
                    timer.start();
                    solenoid.set(Value.kForward);
                }

                public void run (ActionData data) {
                    if (timer.get() % 0.375 > 0.125) {
                        solenoid.set(Value.kReverse);
                    } else {
                        solenoid.set(Value.kForward);
                    }
                }

                public void end (ActionData data) {
                    solenoid.set(Value.kReverse);
                }
            });
        }});
    }
}
