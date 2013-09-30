package com._604robotics.robot2013.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.prefabs.outputs.DashboardOutput;
import com._604robotics.robotnik.module.ModuleManager;

public class DashboardSystem extends Coordinator {
    public void apply (ModuleManager modules) {
        this.fill(new DataWire(DashboardOutput.asDouble(), "currentAngle", modules.getModule("Rotation").getData("Angle"))); 
        
        this.fill(new DataWire(DashboardOutput.asBoolean(), "rotationAimed", modules.getModule("Rotation").getTrigger("Aimed"))); 
        this.fill(new DataWire(DashboardOutput.asBoolean(), "rotationReady", modules.getModule("Rotation").getTrigger("Ready"))); 

        this.fill(new DataWire(DashboardOutput.asBoolean(), "shooterCharged", modules.getModule("Shooter").getTrigger("Charged"))); 
        this.fill(new DataWire(DashboardOutput.asBoolean(), "bucketDeployed", modules.getModule("Bucket").getTrigger("Deployed"))); 
        
        this.fill(new DataWire(DashboardOutput.asBoolean(), "compressorCharged", modules.getModule("Regulator").getTrigger("Charged")));
        
        this.fill(new DataWire(modules.getModule("Rotation").getAction("Load"), "angle", modules.getModule("Dashboard").getData("Feeder Station Angle")));
        this.fill(new DataWire(modules.getModule("Rotation").getAction("Angle"), "alignTime", modules.getModule("Dashboard").getData("Rotation Alignment Time")));
        this.fill(new DataWire(modules.getModule("Shooter").getAction("On"), "chargeTime", modules.getModule("Dashboard").getData("Shooter Charge Time")));
    }
}
