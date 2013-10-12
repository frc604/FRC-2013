package com._604robotics.robotnik.utils;

public class Logger {
    public static void missing (String type, String name) {
        System.err.println("WARNING: Missing " + type + " - " + name);
        new Error().printStackTrace();
    }
}
