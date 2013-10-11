package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Enumeration;
import java.util.Hashtable;

public class TriggerManager {
    private final Hashtable triggerTable;
    
    public TriggerManager (TriggerMap triggerMap, final IndexedTable table) {
        this.triggerTable = Repackager.repackage(triggerMap.iterate(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new TriggerReference((Trigger) value, table, (String) key);
           }
        });
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
