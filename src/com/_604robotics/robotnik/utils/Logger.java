package com._604robotics.robotnik.utils;

public class Logger {
    public static void missing (String type, String name) {
        System.err.println("WARNING: Missing " + type + " - " + name);
        new Error().printStackTrace();
    }
    
    public static void log (String message) {
        System.out.println("[INFO] " + message);
    }
}
