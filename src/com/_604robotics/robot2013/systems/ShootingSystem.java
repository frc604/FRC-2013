package com._604robotics.robot2013.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class ShootingSystem extends Coordinator {
    public void apply (ModuleManager modules) {
        // Force the compressor off when shooting
        this.bind(new Binding(modules.getModule("Regulator").getAction("Off"), modules.getModule("Shooter").getAction("On").getActiveTrigger(), true));

        // Force the shooter off until rotation is ready
        this.bind(new Binding(modules.getModule("Shooter").getAction("Off"), modules.getModule("Rotation").getTrigger("Ready").not(), true));
        
        // Trigger the bucket piston when the shooter's charged
        this.bind(new Binding(modules.getModule("Bucket").getAction("On"), modules.getModule("Shooter").getTrigger("Charged")));
    }
}
