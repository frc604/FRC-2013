package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Victor;

public class Feeder extends Module {
    private final Victor victor = new Victor(6);
    
    public Feeder () {
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
               public void run (ActionData data) {
                   victor.set(0D);
               }
            });
            
            add("In", new Action() {
               public void run (ActionData data) {
                   victor.set(1D);
               }
               
               public void end (ActionData data) {
                   victor.set(0D);
               }
            });
            
            add("Out", new Action() {
               public void run (ActionData data) {
                   victor.set(-1D);
               }
               
               public void end (ActionData data) {
                   victor.set(0D);
               }
            });
        }});
    }
}
