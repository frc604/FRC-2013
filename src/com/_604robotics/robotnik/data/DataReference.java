package com._604robotics.robotnik.data;

import com._604robotics.robotnik.networking.IndexedTable;

public class DataReference implements DataAccess {
    private final Data data;
    
    private final IndexedTable table;
    private final String name;
    
    public DataReference (Data data, IndexedTable table, String name) {
        this.data = data;
        
        this.table = table;
        this.name = name;
    }
    
    public double get () {
        return this.table.getNumber(this.name, 0D);
    }
    
    public void begin () {
        this.data.begin();
    }
    
    public void update () {
        this.table.putNumber(this.name, this.data.run());
    }
    
    public void end () {
        this.data.end();
    }
}
