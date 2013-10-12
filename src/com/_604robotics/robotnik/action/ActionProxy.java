package com._604robotics.robotnik.action;

import com._604robotics.robotnik.utils.InternalLogger;
import com._604robotics.robotnik.utils.Logger;

public class ActionProxy {
    private static boolean active = true;
    
    public static void disable () { active = false; }
    
    protected static void begin (String moduleName, String actionName, ActionReference action) {
        if (active) {
            try {
                action.begin();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Action", actionName, "begin", ex);
            }
        } else {
            action.begin();
        }
    }
    
    protected static void run (String moduleName, String actionName, ActionReference action) {
        if (active) {
            try {
                action.run();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Action", actionName, "run", ex);
            }
        } else {
            action.run();
        }
    }
    
    protected static void end (String moduleName, String actionName, ActionReference action) {
        if (active) {
            try {
                action.end();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Action", actionName, "end", ex);
            }
        } else {
            action.end();
        }
    }
}
