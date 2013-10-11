package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.networking.IndexedTable;
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
        final Iterator i = new Iterator(this.triggerTable);
        while (i.next()) ((TriggerReference) i.value).begin();
    }
    
    public void update () {
        final Iterator i = new Iterator(this.triggerTable);
        while (i.next()) ((TriggerReference) i.value).update();
    }
    
    public void end () {
        final Iterator i = new Iterator(this.triggerTable);
        while (i.next()) ((TriggerReference) i.value).end();
    }
}
