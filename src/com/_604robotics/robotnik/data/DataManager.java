package com._604robotics.robotnik.data;

import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataManager {
    private final Hashtable dataTable = new Hashtable();
    
    public DataManager (DataMap dataMap, IndexedTable table) {
        final Enumeration dataNames = dataMap.getDataNames();
        
        String name;
        Data data;
        
        while (dataNames.hasMoreElements()) {
            name = (String) dataNames.nextElement();
            data = dataMap.getData(name);
            
            this.dataTable.put(name, new DataReference(data, table, name));
        }
    }
    
    public DataReference getData (String name) {
        return (DataReference) this.dataTable.get(name);
    }
    
    public void update () {
        final Enumeration i = this.dataTable.elements();
        
        while (i.hasMoreElements()) {
            ((DataReference) i.nextElement()).update();
        }
    }
}
