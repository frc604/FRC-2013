package com._604robotics.robotnik.action.field;

import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Vector;

public class ActionData {
    private final Vector fieldNames;
    private final IndexedTable table;
    
    public ActionData (Vector fieldNames, IndexedTable table) {
        this.fieldNames = fieldNames;
        this.table = table;
    }
    
    public double get (String name) {
        if (!this.fieldNames.contains(name)) {
            System.err.println("WARNING: Missing Field referenced - " + name);
            new Error().printStackTrace();
        }
        
        return this.table.getNumber(name, 0D);
    }
    
    public boolean is (String name) {
        if (!this.fieldNames.contains(name)) {
            System.err.println("WARNING: Missing Field referenced - " + name);
            new Error().printStackTrace();
        }
        
        return this.table.getNumber(name, 0D) > 0;
    }
}
