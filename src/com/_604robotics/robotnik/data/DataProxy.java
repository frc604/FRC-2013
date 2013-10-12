package com._604robotics.robotnik.data;

import com._604robotics.robotnik.utils.InternalLogger;

public class DataProxy {
    private static boolean active = true;
    
    public static void disable () { active = false; }
    
    protected static void update (String moduleName, String dataName, DataReference data) {
        if (active) {
            try {
                data.update();
            } catch (Exception ex) {
                InternalLogger.caught(moduleName, "Data", dataName, "run", ex);
            }
        } else {
            data.update();
        }
    }
}
