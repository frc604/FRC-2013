package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Module {
    private final Victor victor = new Victor(4);
    private final Timer timer = new Timer();
    
    public Shooter(){
        this.set(new TriggerMap() {{
            add("Charged", new Trigger() {
                public boolean run () {
                    return timer.get() > 2;
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void begin (ActionData data) {
                    victor.set(0);
                }
            });
            
            add("On", new Action() {
                public void begin (ActionData data) {
                    timer.start();
                    victor.set(1D);
                }
                
                public void end (ActionData data) {
                    victor.set(0D);

                    timer.stop();
                    timer.reset();
                }
            });
        }});
    }
}
