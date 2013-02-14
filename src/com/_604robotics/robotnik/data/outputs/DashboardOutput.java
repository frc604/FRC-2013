package com._604robotics.robotnik.data.outputs;

import com._604robotics.robotnik.data.DataRecipient;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput implements DataRecipient {
    private static DashboardOutput instance = null;
    
    public static DashboardOutput getInstance () {
        if (instance == null) {
            instance = new DashboardOutput();
        }
        
        return instance;
    }
    
    private DashboardOutput () {}
    
    public void sendData (String fieldName, double dataValue) {
        SmartDashboard.putNumber(fieldName, dataValue);
    }
}
