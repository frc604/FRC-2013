package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;

public class Dashboard extends Module {
    public Dashboard () {
        this.set(new DataMap() {{
            add("Manual Aim Angle", new DashboardData("Manual Aim Angle", 0D));
        }});
    }
}
