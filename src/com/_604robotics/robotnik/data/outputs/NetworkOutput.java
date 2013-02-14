package com._604robotics.robotnik.data.outputs;

import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.trigger.TriggerRecipient;
import com.sun.squawk.util.StringTokenizer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class NetworkOutput implements DataRecipient {
    private final ITable table;
    
    public NetworkOutput (String namespace) {
        final StringTokenizer tokens = new StringTokenizer(namespace, ".");
        
        ITable workingTable = NetworkTable.getTable(tokens.nextToken());
        
        while (tokens.hasMoreTokens()) {
            workingTable = workingTable.getSubTable(tokens.nextToken());
        }
        
        this.table = workingTable;
    }

    public void sendData (String fieldName, double dataValue) {
        this.table.putNumber(fieldName, dataValue);
    }
}