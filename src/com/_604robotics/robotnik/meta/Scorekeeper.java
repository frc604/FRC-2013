package com._604robotics.robotnik.meta;

public class Scorekeeper {
    public Object victor = null;
    public double score;
    
    public void consider (Object item, double score) {
        if (this.victor == null || score > this.score) {
            this.victor = item;
            this.score = score;
        }
    }
}
