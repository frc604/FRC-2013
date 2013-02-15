/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author alan
 */
public class Shooter extends Module {
    private final Victor victor = new Victor(1/*<Victor port goes here>*/);
    private final double speed = 0;/*<Speed for shooter goes here*/
    
    public Shooter(){
        this.set(new ElasticController() {{
            addDefault("off", new Action() {
                public void begin (ActionData data) {
                    victor.set(0);
                }
            });
            
            add("on", new Action() {
                public void begin (ActionData data) {
                    victor.set(speed);
                }
            });
        }});
    }
}
