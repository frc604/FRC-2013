package com._604robotics.robotnik.data;

import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.networking.IndexedTable;
import com._604robotics.robotnik.utils.Logger;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataManager {
    private final Hashtable dataTable;
    
    public DataManager (DataMap dataMap, final IndexedTable table) {
        this.dataTable = Repackager.repackage(dataMap.iterate(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new DataReference((Data) value, table.getSlice((String) key));
           }
        });
    }
    
    public DataReference getData (String name) {
        final DataReference ref = (DataReference) this.dataTable.get(name);
        if (ref == null) Logger.missing("DataReference", name);
        return ref;
    }
    
    public void begin () {
        final Enumeration i = this.dataTable.elements();
        
        while (i.hasMoreElements()) {
            ((DataReference) i.nextElement()).begin();
        }
    }
    
    public void update () {
        final Enumeration i = this.dataTable.elements();
        
        while (i.hasMoreElements()) {
            ((DataReference) i.nextElement()).update();
        }
    }
    
    public void end () {
        final Enumeration i = this.dataTable.elements();
        
        while (i.hasMoreElements()) {
            ((DataReference) i.nextElement()).end();
        }
    }
}
