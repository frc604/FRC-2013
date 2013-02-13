/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Aaron Wang
 */
public class Shifter extends Module {
    private final Solenoid solenoid = new Solenoid(1);
    //FIXME: Get the right port for the Single Action Solenoid.
    public Shifter () {
        this.set(new ElasticController(){{
            addDefault ("Low Power", new Action(){
                public void begin (ActionData data) {
                    solenoid.set(false);
                }
            });
        }});
    }
}
