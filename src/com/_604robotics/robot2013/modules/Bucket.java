package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;

public class Bucket extends Module {
    private final DoubleSolenoid solenoid = new DoubleSolenoid(3, 4);
    private int count = 1;
    
    public Bucket () {
        this.set(new TriggerMap() {{
            add("Deployed", new Trigger() {
                public boolean run () {
                    return solenoid.get().equals(Value.kForward);
                }
            });
            
            add("Emptied", new Trigger() {
                public boolean run () {
                    return count > 4;
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
                    count = 1;
                    
                    timer.start();
                    solenoid.set(Value.kForward);
                }

                public void run (ActionData data) {
                    if (count > 4 || timer.get() % 0.375 > 0.125) {
                        if (solenoid.get() == Value.kForward) {
                            count++;
                        }

                        solenoid.set(Value.kReverse);
                    } else {
                        solenoid.set(Value.kForward);
                    }
                }

                public void end (ActionData data) {
                    count = 1;
                    
                    solenoid.set(Value.kReverse);
                }
            });
        }});
    }
}
