package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;

public class Targets extends Module {
    public Targets () {
        this.set(new DataMap() {{
            add("Top Angle", new Data() {
                public double run () {
                    return -20D;
                }
            });
            
            add("Middle Angle", new Data() {
                public double run () {
                    return -16D;
                }
            });
            
            add("Bottom Angle", new Data() {
                public double run () {
                    return -10D;
                }
            });
        }});
    }
}