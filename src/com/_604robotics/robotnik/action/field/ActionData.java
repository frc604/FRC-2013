package com._604robotics.robotnik.action.field;

import com._604robotics.robotnik.networking.IndexedTable;

public class ActionData {
    private final IndexedTable table;
    
    public ActionData (IndexedTable table) {
        this.table = table;
    }
    
    public double get (String name) {
        return this.table.getNumber(name, 0D);
    }
}
