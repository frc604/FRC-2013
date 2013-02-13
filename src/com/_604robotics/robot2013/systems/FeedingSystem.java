package com._604robotics.robot2013.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.module.ModuleManager;

public class FeedingSystem extends Coordinator {
    public void apply (ModuleManager modules) {
        // Force the shooter Rotation to go to the hard-stop position when feeding
        this.bind(new Binding(modules.getModule("Rotation").getAction("Feed"), modules.getModule("Feeder").getAction("On").getActiveTrigger(), true));
        
        // Only turn the Pickup on when the shooter Rotation is at the hard-stop position
        this.bind(new Binding(modules.getModule("Pickup").getAction("On"), modules.getModule("Rotation").getTrigger("At Hard Stop")));
    }
}
