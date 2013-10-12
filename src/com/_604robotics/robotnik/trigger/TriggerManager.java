package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.utils.Logger;
import java.util.Hashtable;

public class TriggerManager {
    private final Hashtable triggerTable;
    
    public TriggerManager (TriggerMap triggerMap, final IndexedTable table) {
        this.triggerTable = Repackager.repackage(triggerMap.iterate(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new TriggerReference((Trigger) value, table.getSlice((String) key));
           }
        });
    }
    
    public TriggerReference getTrigger (String name) {
        TriggerReference ref = (TriggerReference) this.triggerTable.get(name);
        if (ref == null) Logger.missing("TriggerRefernece", name);
        return ref;
    }
    
    public void update () {
        final Iterator i = new Iterator(this.triggerTable);
        while (i.next()) ((TriggerReference) i.value).update();
    }
}
