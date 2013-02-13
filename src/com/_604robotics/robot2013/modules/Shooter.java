/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com._604robotics.robot2013.modules;

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
        victor.set(0);
    }
    
    public void on(){
        victor.set(speed);
    }
    public void off(){
        victor.set(0);
    }
}
