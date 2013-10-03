package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Victor;

public class Drive extends Module {
    private final RobotDrive drive = new RobotDrive(new Victor(1), new Victor(7));
    
    public Drive () {
        drive.setInvertedMotor(MotorType.kFrontLeft, true);
        drive.setInvertedMotor(MotorType.kFrontRight, true);
        drive.setInvertedMotor(MotorType.kRearLeft, true);
        drive.setInvertedMotor(MotorType.kRearRight, true);
        
        this.set(new ElasticController() {{
            addDefault("Tank Drive", new Action(new FieldMap() {{
                define("left", 0D);
                define("right", 0D);
                
                define("scaled", false);
            }}) {
               public void run (ActionData data) {
                   if (data.is("scaled")) {
                       drive.tankDrive(MathUtils.pow(data.get("left"), 2), MathUtils.pow(data.get("right"), 2));
                   } else {
                       drive.tankDrive(data.get("left"), data.get("right"));
                   }
               }
               
               public void end (ActionData data) {
                   drive.tankDrive(0D, 0D);
               }
            });
        }});
    }
}
