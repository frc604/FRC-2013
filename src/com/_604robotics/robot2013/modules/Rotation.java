package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Victor;

public class Rotation extends Module {
    private final Victor victor = new Victor(3);
    
    public Rotation () {
        this.set(new ElasticController() {{
            addDefault("Manual", new Action (
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
        }});
    }
}
