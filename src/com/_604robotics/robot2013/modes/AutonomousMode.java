package com._604robotics.robot2013.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.trigger.TriggerAlways;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;

public class AutonomousMode extends Coordinator {
    public void apply (ModuleManager modules) {
        this.bind(new Binding(modules.getModule("Shooter").getAction("On"), new TriggerToggle(modules.getModule("Bucket").getTrigger("Emptied"), true).on));
            
        this.fill(new DataWire(modules.getModule("Rotation").getAction("Angle"), "angle", modules.getModule("Targets").getData("Middle Angle")));
        this.bind(new Binding(modules.getModule("Rotation").getAction("Angle"), TriggerAlways.getInstance()));
    }
}
