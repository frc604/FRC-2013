package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.robotnik.utils.AS5145B;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rotation extends Module {
    private final double NEUTRAL = -20D;
    
    private final Victor victor = new Victor(3);
    private final AS5145B encoder = new AS5145B(1);
    
    private final PIDController pid = new PIDController(0D, 0D, 0D, encoder, victor, 0.01);

    private boolean ready = false;
    
    public Rotation () {
        this.encoder.setZero(250D);
        
        this.pid.setOutputRange(-0.5D, 0.5D);
        this.pid.setAbsoluteTolerance(0.25D);
        
        SmartDashboard.putData("Rotation PID", pid);
        
        this.set(new DataMap() {{
            add("Angle", new Data() {
                public double run () {
                    return encoder.getAngle();
                }
            });
        }});
        
        this.set(new TriggerMap() {{
            add("Aimed", new Trigger() {
                public boolean run () {
                    return pid.onTarget();
                }
            });

            add("Ready", new Trigger() {
                public boolean run () {
                    return ready;
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Manual", new Action(new FieldMap() {{
                define("power", 0D);
            }}) {
                public void begin (ActionData data) {
                    ready = true;
                }

                public void run (ActionData data) {
                    victor.set(data.get("power") * -0.5);
                }
                
                public void end (ActionData data) {
                    victor.set(0D);

                    ready = false;
                }
            });
            
            add("Stow", new Action() {
                public void begin (ActionData data) {
                    ready = true;

                    pid.setPID(-.025, 0, -.020);
                    pid.setSetpoint(-2D);
                    pid.enable();
                }
                
                public void end (ActionData data) {
                    ready = false;

                    pid.reset();
                }
            });
            
            add("Load", new Action() {
                public void begin (ActionData data) {
                    ready = true;

                    pid.setPID(-.025, 0, -.020);
                    pid.setSetpoint(-10D);
                    pid.enable();
                }
                
                public void end (ActionData data) {
                    ready = false;

                    pid.reset();
                }
            });
            
            add("Angle", new Action(new FieldMap() {{
                define("angle", 0D);
            }}) {
                private final Timer timer = new Timer();
                private boolean aligned = false;
                
                public void begin (ActionData data) {
                    ready = false;
                    aligned = false;
                    
                    pid.setPID(-0.030, 0D, -0.050);
                    pid.setSetpoint(NEUTRAL);
                    pid.enable();
                }

                public void run (ActionData data) {
                    if (!aligned) {
                        if (pid.onTarget()) {
                            aligned = true;
                            
                            pid.reset();
                            pid.setSetpoint(data.get("angle"));
                            pid.enable();

                            timer.start();
                        }
                    } else {
                        final double angle = data.get("angle");

                        if (angle != pid.getSetpoint()) {
                            ready = false;
                            aligned = false;
                            
                            pid.reset();
                            pid.setSetpoint(NEUTRAL);
                            pid.enable();
                            
                            timer.stop();
                            timer.reset();
                        }
                        
                        ready = timer.get() > 3D;
                    }
                }

                public void end (ActionData data) {
                    pid.reset();
                    
                    timer.stop();
                    timer.reset();
                    
                    ready = false;
                    aligned = false;
                }
            });
        }});
    }
}
