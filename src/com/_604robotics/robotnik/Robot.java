package com._604robotics.robotnik;

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
        
        if (this.modeMap != null) {
            this.modeMap.apply(this.moduleManager);
        }
    }
    
    public void autonomous () {
        this.loopTime.start();
        
        while (this.isEnabled() && this.isAutonomous()) {
            this.moduleManager.update();
            
            if (this.coordinatorList != null) {
                this.coordinatorList.update();
            }
            
            if (this.modeMap != null && this.modeMap.getAutonomousMode() != null) {
                this.modeMap.getAutonomousMode().update();
            }
            
            this.moduleManager.execute();
            
            this.loopTime.sample();
        }
        
        this.moduleManager.end();
        
        this.loopTime.stop();
    }
    
    public void operatorControl () {
        this.loopTime.start();
        
        while (this.isEnabled() && this.isOperatorControl()) {
            this.moduleManager.update();
            
            if (this.coordinatorList != null) {
                this.coordinatorList.update();
            }
            
            if (this.modeMap != null && this.modeMap.getTeleopMode() != null) {
                this.modeMap.getTeleopMode().update();
            }
            
            this.moduleManager.execute();
            
            this.loopTime.sample();
        }
        
        this.moduleManager.end();
        
        this.loopTime.stop();
    }
    
    public void disabled () {}
}