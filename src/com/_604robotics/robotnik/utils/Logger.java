package com._604robotics.robotnik.utils;

public class Logger {
    public static void missing (String type, String name) {
        warn("WARNING: Missing " + type + " - " + name);
    }
    
    public static void log (String message) {
        System.out.println("[INFO] " + message);
    }
    
    public static void warn (String message) {
        System.err.println("[WARN] " + message);
        new Error().printStackTrace();
    }
    
    public static void error (String message, Error error) {
        System.err.println("[ERROR] " + message);
        error.printStackTrace();
    }
}
