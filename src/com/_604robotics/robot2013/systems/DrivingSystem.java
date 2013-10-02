package com._604robotics.robot2013.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;

public class DrivingSystem extends Coordinator {
    public void apply (ModuleManager modules) {
        // Activate "powered" mode of Racecard Drive when in High Gear
        this.fill(new DataWire(modules.getModule("Drive").getAction("Racecar Drive"), "powered", modules.getModule("Shifter").getAction("High Power").getActiveTrigger()));
    }
}
