package com._604robotics.robotnik.action;

import com._604robotics.robotnik.action.field.Field;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.InternalLogger;
import java.util.Enumeration;

public class ActionData {
    private final FieldMap map;
    private final IndexedTable table;
    
    public ActionData (FieldMap map, IndexedTable table) {
        this.map = map;
        this.table = table;
    }
    
    public double get (String name) {
        return this.lookup(name);
    }
    
    public boolean is (String name) {
        return this.lookup(name) > 0;
    }
    
    protected void reset () {
        final Enumeration fields = map.enumerate();
        Field field;
        
        while (fields.hasMoreElements()) {
            field = (Field) fields.nextElement();
            this.table.putNumber(field.getName(), field.getDefaultValue());
        }
    }
    
    private double lookup (String name) {
        if (!this.table.knowsAbout(name)) InternalLogger.missing("Field", name);
        return this.table.getNumber(name, 0D);
    }
}
