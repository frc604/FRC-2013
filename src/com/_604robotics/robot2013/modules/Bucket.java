package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Bucket extends Module {
    // FIXME: Get the right port for the Single Action Solenoid.
    private final Solenoid solenoid = new Solenoid(1);
    
    public Bucket () {
        this.set(new ElasticController() {{
            addDefault("Low Power", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(false);
                }
            });
            
            add("High Power", new Action() {
                private Timer timer = new Timer();
                public void begin (ActionData data) {
                    timer.start();
                }
                public void run (ActionData data) {
                    if((timer.get()%3)>=2){
                        solenoid.set(true);
                    }
                    else{
                        solenoid.set(false);
                    }
                }
                public void end (ActionData data) {
                    timer.stop();
                    timer.reset();
                    solenoid.set(false);
                }
            });
        }});
    }
}
