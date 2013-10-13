package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Victor;

public class Drive extends Module {
    private final RobotDrive drive = new RobotDrive(new Victor(1), new Victor(7));
    
    private final Encoder leftEncoder  = new Encoder(2, 3);
    private final Encoder rightEncoder = new Encoder(6, 7);
    
    public Drive () {
        drive.setInvertedMotor(MotorType.kFrontLeft, true);
        drive.setInvertedMotor(MotorType.kFrontRight, true);
        drive.setInvertedMotor(MotorType.kRearLeft, true);
        drive.setInvertedMotor(MotorType.kRearRight, true);
        
        leftEncoder.start();
        rightEncoder.start();
        
        this.set(new DataMap() {{
            add("Left Encoder", new Data() {
                public double run () {
                    return leftEncoder.get();
                }
            });
            
            add("Right Encoder", new Data() {
                public double run () {
                    return rightEncoder.get();
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Tank Drive", new Action(new FieldMap() {{
                define("left", 0D);
                define("right", 0D);
                
                define("scaled", false);
            }}) {
               public void run (ActionData data) {
                   if (data.is("scaled")) {
                       drive.tankDrive(scale(data.get("left")), scale(data.get("right")));
                   } else {
                       drive.tankDrive(data.get("left"), data.get("right"));
                   }
               }
               
               public void end (ActionData data) {
                   drive.tankDrive(0D, 0D);
               }
               
               private double polarity (double value) {
                   return value < 0 ? -1D : 1D;
               }
               
               private double scale (double value) {
                   return MathUtils.pow(value, 2) * polarity(value);
               }
            });
        }});
    }
}
