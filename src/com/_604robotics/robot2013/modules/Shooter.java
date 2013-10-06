package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Module {
    private final Victor victor = new Victor(4);
    
    private boolean charged;
    
    public Shooter(){
        this.set(new TriggerMap() {{
            add("Charged", new Trigger() {
                public boolean run () {
                    return charged;
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void begin (ActionData data) {
                    victor.set(0);
                }
            });
            
            add("On", new Action(new FieldMap() {{
                define("power", 1D);
                define("time", 2D);
            }}) {
                private final Timer timer = new Timer();
                
                public void begin (ActionData data) {                    
                    charged = false;
                    timer.start();
                    
                    victor.set(data.get("power"));
                }
                
                public void run (ActionData data) {
                    victor.set(data.get("power"));
                    charged = timer.get() > data.get("time");
                }
                
                public void end (ActionData data) {
                    victor.set(0D);
                    
                    charged = false;

                    timer.stop();
                    timer.reset();
                }
            });
        }});
    }
}
