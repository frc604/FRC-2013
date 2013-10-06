package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import edu.wpi.first.wpilibj.Compressor;

public class Regulator extends Module {
    private final Compressor compressor = new Compressor(1, 1);
    
    public Regulator () {
        this.set(new TriggerMap(){{
            add("Charged", new Trigger() {
                public boolean run (){
                    return compressor.getPressureSwitchValue();
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("On", new Action() {
                public void begin (ActionData data) {
                    compressor.start();
                }

                public void end (ActionData data) {
                    compressor.stop();
                }
            });
            
            add("Off", new Action() {
                public void begin (ActionData data) {
                    compressor.stop();
                }
            });
        }});
    }
}
