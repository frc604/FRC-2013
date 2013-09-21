package com._604robotics.robot2013;

import com._604robotics.robot2013.modes.AutonomousMode;
import com._604robotics.robot2013.modes.TeleopMode;
import com._604robotics.robot2013.modules.Bucket;
import com._604robotics.robot2013.modules.Dashboard;
import com._604robotics.robot2013.modules.Drive;
import com._604robotics.robot2013.modules.Regulator;
import com._604robotics.robot2013.modules.Shifter;
import com._604robotics.robot2013.modules.Shooter;
import com._604robotics.robot2013.modules.Feeder;
import com._604robotics.robot2013.modules.PneumaticHanger;
import com._604robotics.robot2013.modules.Pickup;
import com._604robotics.robot2013.modules.Rotation;
import com._604robotics.robot2013.modules.Targets;
import com._604robotics.robot2013.systems.DashboardSystem;
import com._604robotics.robot2013.systems.ShootingSystem;
import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.coordinator.ModeMap;
import com._604robotics.robotnik.module.ModuleMap;

public class Robot2013 extends Robot {
    public Robot2013 () {
        this.set(new ModuleMap() {{
            add("Dashboard", new Dashboard());
            add("Targets", new Targets());
            add("Drive", new Drive());
            add("Rotation", new Rotation());
            add("Regulator", new Regulator());
            add("Shifter", new Shifter());
            add("Shooter", new Shooter());
            add("Feeder", new Feeder());
            add("Pickup", new Pickup());
            add("Bucket", new Bucket());
            add("Pneumatic Hanger", new PneumaticHanger());
        }});
        
        this.set(new CoordinatorList() {{
            add(new DashboardSystem());
            add(new ShootingSystem());
        }});
        
        this.set(new ModeMap() {{
            setAutonomousMode(new AutonomousMode());
            setTeleopMode(new TeleopMode());
        }});
    }
}
