package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Module {
    private final Victor victor = new Victor(4);
    private final Encoder encoder  = new Encoder(4, 5);
    
    private boolean charged;
    
    public Shooter(){
        encoder.setDistancePerPulse(1D);
        encoder.start();
        
        this.set(new DataMap() {{
            add("Shooter Speed", new Data() {
                public double run () {
                    return encoder.getRate();
                }
            });
        }});
        
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
