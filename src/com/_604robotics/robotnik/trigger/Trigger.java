package com._604robotics.robotnik.trigger;

public abstract class Trigger {
    public void begin () {}
    public abstract boolean run ();
    public void end () {}
}
