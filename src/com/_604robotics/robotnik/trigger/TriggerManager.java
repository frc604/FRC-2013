package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Enumeration;
import java.util.Hashtable;

public class TriggerManager {
    private final Hashtable triggerTable = new Hashtable();
    
    public TriggerManager (TriggerMap triggerMap, IndexedTable table) {
        final Enumeration triggerNames = triggerMap.getTriggerNames();
        
        String name;
        Trigger trigger;
        
        while (triggerNames.hasMoreElements()) {
            name = (String) triggerNames.nextElement();
            trigger = triggerMap.getTrigger(name);
            
            this.triggerTable.put(name, new TriggerReference(trigger, table, name));
        }
    }
    
    public TriggerReference getTrigger (String name) {
        TriggerReference ref = (TriggerReference) this.triggerTable.get(name);
        
        if (ref == null) {
            System.err.println("WARNING: Missing TriggerReference - " + name);
            new Error().printStackTrace();;
        }
        
        return ref;
    }
    
    public void begin () {
        final Enumeration i = this.triggerTable.elements();
        
        while (i.hasMoreElements()) {
            ((TriggerReference) i.nextElement()).begin();
        }
    }
    
    public void update () {
        final Enumeration i = this.triggerTable.elements();
        
        while (i.hasMoreElements()) {
            ((TriggerReference) i.nextElement()).update();
        }
    }
    
    public void end () {
        final Enumeration i = this.triggerTable.elements();
        
        while (i.hasMoreElements()) {
            ((TriggerReference) i.nextElement()).end();
        }
    }
}
