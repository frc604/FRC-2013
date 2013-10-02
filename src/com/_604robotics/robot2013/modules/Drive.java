package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Drive extends Module {
    private final RobotDrive drive = new RobotDrive(new Victor(7), new Victor(1));
    
    public Drive () {
        drive.setInvertedMotor(MotorType.kFrontLeft, true);
        drive.setInvertedMotor(MotorType.kFrontRight, true);
        drive.setInvertedMotor(MotorType.kRearLeft, true);
        drive.setInvertedMotor(MotorType.kRearRight, true);
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
               public void run (ActionData data) {
                   drive.tankDrive(0D, 0D);
               }
            });
            
            add("Tank Drive", new Action(new FieldMap() {{
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
            
            add("Racecar Drive", new Action (new FieldMap() {{
                define("wheel", 0D);
                define("throttle", 0D);
                
                define("powered", false);
                define("sharp", false);
            }}) {
                private final Timer timer = new Timer();
                private double lastWheel;
                
                public void begin (ActionData data) {
                    lastWheel = data.get("wheel");
                    
                    drive.tankDrive(0D, 0D);
                    
                    timer.reset();
                    timer.start();
                }
                
                public void run (ActionData data) {
                    if (timer.get() < 0.02) return;
                    timer.reset();
                        
                    final boolean powered = data.is("powered");
                    final boolean sharp   = data.is("sharp");
                    
                    double wheel = data.get("wheel");
                    final double throttle = -data.get("throttle");
                    
                    {
                        wheel  = smoothWheel(wheel, powered);
                        wheel += calculateInertia(wheel - lastWheel, wheel, powered);
                    }
                    
                    double left = throttle;
                    double right = throttle;

                    {
                        if (!sharp) {
                            wheel = calculateAngularPower(wheel, throttle, powered);
                        }

                        left  += wheel;
                        right -= wheel;

                        if (sharp) {
                            final double _left  = left;
                            final double _right = right;

                            left  = counterbalance(_left,  _right);
                            right = counterbalance(_right, _left);
                        }
                    }
                    
                    drive.tankDrive(limitOutput(left), limitOutput(right));
                    
                    lastWheel = wheel;
                }
                
                public void end (ActionData data) {
                    drive.tankDrive(0D, 0D);
                    
                    timer.stop();
                }
                
                private double smoothWheel (double wheel, boolean powered) {
                    final int iterations = powered ? 2 : 3;
                    final double factor = powered ? 0.6 : 0.5;
                    
                    for (int i = 0; i < iterations; i++) {
                        wheel = Math.sin(Math.PI / 2D * factor * wheel) /
                                Math.sin(Math.PI / 2D * factor);
                    }
                    
                    return wheel;
                }
                
                private double calculateInertia (double delta, double wheel, boolean powered) {
                    if      (powered               ) return delta * 5D;
                    else if (wheel * delta   > 0   ) return delta * 2.5;
                    else if (Math.abs(wheel) > 0.65) return delta * 5D;
                    else                             return delta * 3D;    
                }
                
                private double calculateAngularPower (double wheel, double throttle, boolean powered) {
                    return Math.abs(throttle) * wheel * (powered ? 0.85 : 0.75);
                }
                
                private double limitOutput (double output) {
                    return Math.min(Math.max(output, -1D), 1D);
                }
                
                private double counterbalance (double weight, double counter) {
                    final double magnitude = Math.abs(counter);
                    final double polarity = counter / magnitude;
                    
                    if (magnitude > 1D) {
                        return weight - ((magnitude - 1) * polarity);
                    }
                    
                    return weight;
                }
            });
        }});
    }
}
