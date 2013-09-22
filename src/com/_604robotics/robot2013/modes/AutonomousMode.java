package com._604robotics.robot2013.modes;

import com._604robotics.robot2013.modules.Bucket;
import com._604robotics.robotnik.action.measure.Measure;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;

public class AutonomousMode extends Coordinator {
    public void apply (ModuleManager modules) {
        this.bind(new Binding(modules.getModule("Shooter").getAction("On"), launchMeasure.no));
            
        this.fill(new DataWire(modules.getModule("Rotation").getAction("Angle"), "angle", modules.getModule("Targets").getData("Top Angle")));
        this.bind(new Binding(modules.getModule("Rotation").getAction("Angle"), TriggerAlways.getInstance()));
    }
}
