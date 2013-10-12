package com._604robotics.robotnik;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.coordinator.ModeMap;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.module.ModuleMap;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.utils.TimeSampler;
import edu.wpi.first.wpilibj.SimpleRobot;

public class Robot extends SimpleRobot {
    private final IndexedTable table = IndexedTable.getTable("robotnik");
    private final TimeSampler loopTime = new TimeSampler("Loop", 1D);
    
    private ModuleManager moduleManager = new ModuleManager(new ModuleMap(), this.table.getSubTable("modules"));
    private CoordinatorList coordinatorList = new CoordinatorList();
    private ModeMap modeMap = new ModeMap();
    
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
        this.coordinatorList.attach(this.moduleManager);
        this.modeMap.attach(this.moduleManager);
    }
    
    public void autonomous () {
        Logger.log(" -- Autonomous mode begin.");
        
        this.loopTime.start();
        
        final Coordinator mode = this.modeMap.getAutonomousMode();
        while (this.isEnabled() && this.isAutonomous()) {
            RobotProxy.tick(mode, moduleManager, coordinatorList);
            this.loopTime.sample();
        }
        
        this.loopTime.stop();

        Logger.log(" -- Autonomous mode end.");
    }
    
    public void operatorControl () {
        Logger.log(" -- Teleop mode begin.");
        
        this.loopTime.start();
        
        final Coordinator mode = this.modeMap.getTeleopMode();
        while (this.isEnabled() && this.isOperatorControl()) {
            RobotProxy.tick(mode, moduleManager, coordinatorList);
            this.loopTime.sample();
        }
        
        this.loopTime.stop();
        
        Logger.log(" -- Teleop mode end.");
    }
    
    public void disabled () {
        Logger.log(" -- Disabled mode begin.");
        
        while (!this.isEnabled()) this.moduleManager.update();
        
        Logger.log(" -- Disabled mode end.");
    }
}