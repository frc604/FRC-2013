package com._604robotics.robotnik.networking;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.util.Set;
import edu.wpi.first.wpilibj.tables.ITable;
import java.util.Hashtable;

public class IndexedTable {
    private static final Hashtable cache = new Hashtable();
    
    public static IndexedTable getTable (String key) {
        return getTable(NetworkTable.getTable(key));
    }
    
    private static IndexedTable getTable (ITable table) {
        if (cache.containsKey(table)) {
            return (IndexedTable) cache.get(table);
        } else {
            final IndexedTable result = new IndexedTable(table);
            cache.put(table, result);
            
            return result;
        }
    }
    
    private final Set keys = new Set();
    private final ITable table;
    
    public boolean knowsAbout (String key) {
        return this.keys.contains(key);
    }
    
    private IndexedTable (ITable table) {
        this.table = table;
    }
    
    public String getString (String key, String defaultValue) {
        return this.table.getString(key, defaultValue);
    }
    
    public double getNumber (String key, double defaultValue) {
        return this.table.getNumber(key, defaultValue);
    }
    
    public boolean getBoolean (String key, boolean defaultValue) {
        return this.table.getBoolean(key, defaultValue);
    }
    
    public IndexedTable getSubTable(String key) {
        this.addKey(key);
        return IndexedTable.getTable(this.table.getSubTable(key));
    }
    
    public void putString (String key, String value) {
        this.table.putString(key, value);
        this.addKey(key);
    }
    
    public void putNumber (String key, double value) {
        this.table.putNumber(key, value);
        this.addKey(key);
    }
    
    public void putBoolean (String key, boolean value) {
        this.table.putBoolean(key, value);
        this.addKey(key);
    }
    
    private void addKey (String key) {
        if (!this.keys.contains(key)) {
            this.keys.add(key);
            
            final String keyList = this.table.getString("__index", "");
            
            if (keyList.equals("")) {
                this.table.putString("__index", key);
            } else {
                this.table.putString("__index", keyList + ";" + key);
            }
        }
    }
}
