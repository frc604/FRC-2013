package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.module.ModuleManager;

public class ModeMap {
    private Coordinator autonomousMode = null;
    private Coordinator teleopMode = null;
    
    public void apply (ModuleManager modules) {
        if (this.autonomousMode != null) {
            this.autonomousMode.apply(modules);
        }
        
        if (this.teleopMode != null) {
            this.teleopMode.apply(modules);
        }
    }
    
    protected void setAutonomousMode (Coordinator autonomousMode) {
        this.autonomousMode = autonomousMode;
    }
    
    protected void setTeleopMode (Coordinator teleopMode) {
        this.teleopMode = teleopMode;
    }

    public Coordinator getAutonomousMode () {
        return this.autonomousMode;
    }

    public Coordinator getTeleopMode () {
        return this.teleopMode;
    }
}