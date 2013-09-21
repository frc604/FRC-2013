package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.networking.IndexedTable;

public class TriggerReference implements TriggerAccess {
    private final Trigger trigger;
    
    private final IndexedTable table;
    private final String name;
    
    private final boolean not;
    private TriggerReference notReference = null;
    
    public TriggerReference (Trigger trigger, IndexedTable table, String name) {
        this.trigger = trigger;
        
        this.table = table;
        this.name = name;
        
        this.not = false;
    }
    
    private TriggerReference (Trigger trigger, IndexedTable table, String name, boolean not) {
        this.trigger = trigger;
        
        this.table = table;
        this.name = name;
        
        this.not = not;
    }
    
    public TriggerReference not () {
        if (this.notReference == null) {
            this.notReference = new TriggerReference(this.trigger, this.table, this.name, true);
        }
        
        return this.notReference;
    }
    
    public boolean get () {
        if (this.not) {
            return !this.table.getBoolean(this.name, false);
        } else {
            return this.table.getBoolean(this.name, false);
        }
    }
    
    public void begin () {
        this.trigger.begin();
    }
    
    public void update () {
        this.table.putBoolean(this.name, this.trigger.run());
    }
    
    public void end () {
        this.trigger.end();
    }
}
