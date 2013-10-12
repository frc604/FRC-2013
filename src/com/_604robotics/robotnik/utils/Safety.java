package com._604robotics.robotnik.utils;

import com._604robotics.robotnik.action.ActionProxy;
import com._604robotics.robotnik.coordinator.ConnectorProxy;
import com._604robotics.robotnik.data.DataProxy;
import com._604robotics.robotnik.trigger.TriggerProxy;

public class Safety {
    public static void disable () {
        DataProxy.disable();
        TriggerProxy.disable();
        ActionProxy.disable();
        
        ConnectorProxy.disable();
        
        Logger.warn("Exception protection has been disabled. Make sure you know what you're doing!");
    }
}
