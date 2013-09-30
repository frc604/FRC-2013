package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;

public class Dashboard extends Module {
    public Dashboard () {
        this.set(new DataMap() {{
            add("Manual Aim Angle", new DashboardData("Manual Aim Angle", 0D));
            add("Feeder Station Angle", new DashboardData("Feeder Station Angle", -10D));
            
            add("Rotation Alignment Time", new DashboardData("Rotation Alignment Time", 3D));
            add("Shooter Charge Time", new DashboardData("Shooter Charge Time", 2D));
        }});
    }
}
