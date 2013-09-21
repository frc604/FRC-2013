package com._604robotics.robotnik;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.coordinator.ModeMap;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.module.ModuleMap;
import com._604robotics.robotnik.networking.IndexedTable;
import com._604robotics.robotnik.utils.TimeSampler;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {
    private final IndexedTable table = IndexedTable.getTable("robotnik");
    
    private final TimeSampler loopTime = new TimeSampler("Loop", 1D);
    
    private ModuleManager moduleManager;
    
    private CoordinatorList coordinatorList = null;
    private ModeMap modeMap = null;
    
    protected void set (ModuleMap moduleMap) {
        this.moduleManager = new ModuleManager(moduleMap, this.table.getSubTable("modules"));
    }
    
    protected void set (CoordinatorList coordinatorList) {
        this.coordinatorList = coordinatorList;
    }
    
    protected void set (ModeMap modeMap) {
        this.modeMap = modeMap;
    }
    
    public void robotInit () {
        if (this.coordinatorList != null) {
            this.coordinatorList.apply(this.moduleManager);
        }
    }
    
    public void autonomous () {
        System.out.println(" -- Autonomous mode begin.");
        
        if (this.modeMap != null) {
            this.modeMap.attach(this.moduleManager);
        }
        
        this.loopTime.start();
        
        Coordinator mode = null;
        
        if (this.modeMap != null) {
            mode = this.modeMap.getAutonomousMode();
        }
        
        this.moduleManager.begin();
        
        while (this.isEnabled() && this.isAutonomous()) {
            this.tick(mode);
            this.loopTime.sample();
        }
        
        this.moduleManager.end();
        
        this.loopTime.stop();

        System.out.println(" -- Autonomous mode end.");
    }
    
    public void operatorControl () {
        System.out.println(" -- Teleop mode begin.");
        
        if (this.modeMap != null) {
            this.modeMap.attach(this.moduleManager);
        }
        
        this.loopTime.start();
        
        Coordinator mode = null;
        
        if (this.modeMap != null) {
            mode = this.modeMap.getTeleopMode();
        }
        
        this.moduleManager.begin();
        
        while (this.isEnabled() && this.isOperatorControl()) {
            this.tick(mode);
            this.loopTime.sample();
        }
        
        this.moduleManager.end();
        
        this.loopTime.stop();
        
        System.out.println(" -- Teleop mode end.");
    }
    
    public void disabled () {
        System.out.println(" -- Disabled mode begin.");
        
        while (!this.isEnabled()) {
            this.moduleManager.update();
        }
        
        System.out.println(" -- Disabled mode end.");
    }
    
    private void tick (Coordinator mode) {
        this.moduleManager.update();

        if (this.coordinatorList != null) {
            this.coordinatorList.update();
        }
        
        if (mode != null) {
            mode.update();
        }
        
        this.moduleManager.execute();
    }
}