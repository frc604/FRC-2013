package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;

public class Targets extends Module {
    public Targets () {
        this.set(new DataMap() {{
            add("Top Angle", new DashboardData("Top Angle", -21.5));
            add("Middle Angle", new DashboardData("Middle Angle", -21D));
            add("Bottom Angle", new DashboardData("Bottom Angle", -20D));
        }});
    }
}