package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.data.sources.DataTriggerAdapter;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class DataWire {
    private final DataRecipient recipient;
    
    private final String fieldName;
    private final DataAccess data;
    
    public DataWire (DataRecipient recipient, String fieldName, DataAccess data) {
        this.recipient = recipient;
        
        this.fieldName = fieldName;
        this.data = data;
    }
    
    public DataWire (DataRecipient recipient, String fieldName, TriggerAccess trigger) {
        this.recipient = recipient;
        
        this.fieldName = fieldName;
        this.data = new DataTriggerAdapter(trigger);
    }

    public DataRecipient getRecipient () {
        return this.recipient;
    }

    public DataAccess getData () {
        return this.data;
    }

    public String getFieldName () {
        return this.fieldName;
    }
}