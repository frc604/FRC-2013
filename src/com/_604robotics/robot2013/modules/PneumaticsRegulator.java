/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com._604robotics.robot2013.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Compressor;
/**
 *
 * @author Aaron Wang
 */
public class PneumaticsRegulator extends Module{
    private final Compressor compressor = new Compressor(5,5);
    //FIXME: Get real ports for Pressure switch and compressor relay
    
    public PneumaticsRegulator (){
        this.set(new ElasticController(){{
           addDefault("Compress", new Action() {
               public void begin (ActionData data) {
                   compressor.start();
               }
               public void end (ActionData data) {
                   compressor.stop();
               }
           });
        }});
    }
}
