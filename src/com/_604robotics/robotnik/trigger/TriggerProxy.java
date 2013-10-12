package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.utils.InternalLogger;

public class TriggerProxy {
    private static boolean active = true;
    
    public static void disable () { active = false; }
    
    protected static void update (String moduleName, String triggerName, TriggerReference trigger) {
        if (active) {
            try {
                trigger.update();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Data", triggerName, "run", ex);
            }
        } else {
            trigger.update();
        }
    }
}
