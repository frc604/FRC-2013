package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Module {
    private final Victor victor = new Victor(4);
    private final Encoder encoder = new Encoder(4, 5);
    
    public Shooter(){
        encoder.setDistancePerPulse(1);
        encoder.start();
        
        this.set(new DataMap() {{
            add("Speed", new Data() {
                public double run () {
                    return encoder.getRate();
                };
            });
        }});
        
        this.set(new TriggerMap() {{
            add("Charged", new Trigger() {
                public boolean run () {
                    return encoder.getRate() > 20000;
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
                    victor.set(1D);
                }
                
                public void end (ActionData data) {
                    victor.set(0D);
                }
            });
        }});
    }
}
