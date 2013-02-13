package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Compressor;

public class Regulator extends Module {
    // FIXME: Get real ports for Pressure switch and compressor relay
    private final Compressor compressor = new Compressor(5, 5);

    public Regulator () {
        this.set(new ElasticController() {{
            addDefault("Compress", new Action() {
                public void begin (ActionData data) {
                    compressor.start();
                }

                public void end (ActionData data) {
                    compressor.stop();
                }
            });
        }});
    }
}
