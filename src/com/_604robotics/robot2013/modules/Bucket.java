package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.measure.Measure;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.robotnik.trigger.TriggerReference;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;

public class Bucket extends Module {
    private final DoubleSolenoid solenoid = new DoubleSolenoid(3, 4);
    
    public Bucket () {
        this.set(new TriggerMap() {{
            add("Deployed", new Trigger() {
                public boolean run () {
                    return solenoid.get().equals(Value.kForward);
                }
            });
            
            add("Recharging", new Trigger() {
                private final Timer chargeTimer = new Timer();
                private boolean out;
                
                public void begin () {
                    this.chargeTimer.start();
                    this.out = false;
                }
                
                public boolean run () {
                    if (!this.out) {
                        if (solenoid.get().equals(Value.kForward)) {
                            out = true;
                        } else {
                            this.chargeTimer.reset();
                        }
                    } else {
                        if (chargeTimer.get() > 1.0) {
                            out = false;
                            this.chargeTimer.reset();
                        }
                    }
                    
                    return out;
                }
                
                public void end () {
                    this.chargeTimer.stop();
                    this.chargeTimer.reset();
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kReverse);
                }
            });
            
            add("Deploy", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kForward);
                }
            });
            
            add("Charge", new Action() {
                private final Timer restTimer = new Timer();
                
                public void begin (ActionData data) {
                    solenoid.set(Value.kForward);
                    
                    this.restTimer.start();
                }
                
                public void run (ActionData data) {
                    if (this.restTimer.get() > 0.125) {
                        solenoid.set(Value.kReverse);
                    }
                }
                
                public void end (ActionData data) {
                    this.restTimer.stop();
                    this.restTimer.reset();
                }
            });
        }});
    }
    
    public void apply (ModuleReference module) {
        // Force the piston to recharge after a shot
        this.bind(new Binding(module.getAction("Charge"), module.getTrigger("Recharging"), true));
        this.bind(new Binding(module.getAction("Charge"), module.getTrigger("Deployed"), true));
    }
    
    public static Measure generateLaunchMeasure (ModuleReference module, int count) {
        return new LaunchMeasure(module, count);
    }
    
    private static class LaunchMeasure extends Measure {
        private final TriggerReference deployed;
        
        private final int countTotal;
        private int countLaunched = 0;
        
        private boolean last = false;
        
        public LaunchMeasure (ModuleReference module, int count) {
            this.deployed = module.getTrigger("Deployed");
            
            this.countTotal = count;
        }
        
        protected boolean check () {
            final boolean current = this.deployed.get();
            
            if (this.last && !current) {
                this.countLaunched++;
            }
            
            this.last = current;
            
            return this.countLaunched >= this.countTotal;
        }
    }
}
