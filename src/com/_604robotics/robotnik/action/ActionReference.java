package com._604robotics.robotnik.action;

import com._604robotics.robotnik.action.field.ActionData;
import com._604robotics.robotnik.action.field.Field;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.networking.IndexedTable;
import com._604robotics.robotnik.prefabs.trigger.TriggerManual;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;
import java.util.Enumeration;

public class ActionReference implements DataRecipient, TriggerRecipient {
    private final Action action;
    
    private final IndexedTable triggerTable;
    private final String name;
    
    private final IndexedTable dataTable;
    private final ActionData actionData;
    
    private final TriggerManual activeTrigger = new TriggerManual(false);
    
    public ActionReference (ModuleReference module, Action action, IndexedTable table, String name) {
        this.action = action;
        action.apply(module);
        
        this.triggerTable = table.getSubTable("triggers");
        this.name = name;
        
        this.dataTable = table.getSubTable("data").getSubTable(name);        
        this.actionData = new ActionData(this.action.getFieldNames(), this.dataTable);
    }
    
    public void reset () {
        this.triggerTable.putNumber(this.name, 0D);
        
        final Enumeration fields = this.action.getFields();
        
        Field field;
        
        while (fields.hasMoreElements()) {
            field = (Field) fields.nextElement();
            
            this.dataTable.putNumber(field.getName(), field.getDefaultValue());
        }
    }
    
    public void sendTrigger (double precedence) {
        final double current = this.triggerTable.getNumber(this.name, 0D);
        
        if (precedence > current) {
            this.triggerTable.putNumber(this.name, precedence);
        }
    }
    
    public void sendData (String fieldName, double dataValue) {
        this.dataTable.putNumber(fieldName, dataValue);
    }
    
    public void begin () {
        this.action.begin(this.actionData);
        this.activeTrigger.set(true);
    }
    
    public void run () {
        this.action.run(this.actionData);
    }
    
    public void end () {
        this.action.end(this.actionData);
        this.activeTrigger.set(false);
    }
    
    public TriggerAccess getActiveTrigger () {
        return (TriggerAccess) this.activeTrigger;
    }
}