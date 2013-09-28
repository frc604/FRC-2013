package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Victor;

public class Drive extends Module {
    private final RobotDrive drive = new RobotDrive(new Victor(7), new Victor(1));
    
    public Drive () {
        drive.setInvertedMotor(MotorType.kFrontLeft, true);
        drive.setInvertedMotor(MotorType.kFrontRight, true);
        drive.setInvertedMotor(MotorType.kRearLeft, true);
        drive.setInvertedMotor(MotorType.kRearRight, true);
        
        this.set(new ElasticController() {{
            addDefault("Tank Drive", new Action(new FieldMap() {{
                define("left", 0D);
                define("right", 0D);
            }}) {
               public void run (ActionData data) {
                   drive.tankDrive(data.get("right") * -1, data.get("left") * -1);
               }
               
               public void end (ActionData data) {
                   drive.tankDrive(0D, 0D);
               }
            });

            add("Off", new Action() {
               public void run (ActionData data) {
                   drive.tankDrive(0D, 0D);
               }
            });
        }});
    }
}
