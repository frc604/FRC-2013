package com._604robotics.robotnik.logging;

import com.sun.squawk.microedition.io.FileConnection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import javax.microedition.io.Connector;

public class Logger {
    public static void log (String message) {
        record(System.out, "[INFO] " + message);
    }
    
    public static void warn (String message) {
        record(System.err, "[WARN] " + message);
        trace(new Exception());
    }
    
    public static void error (String message, Exception ex) {
        record(System.err, "[ERROR] " + message + ": (" + ex.getClass().getName() + ") " + ex.getMessage());
        trace(ex);
    }
    
    private static void record (PrintStream std, String message) {
        final String line = System.currentTimeMillis() + message;
        
        std.println(line);
        if (logFile != null) logFile.println(message);
    }
    
    private static void trace (Exception ex) {
        ex.printStackTrace();
        if (logFile != null) logFile.println(ex.toString());
    }
    
    private static final PrintStream logFile;
    
    static {
        PrintStream result = null;
        Exception error = null;
        
        try {
            final FileConnection file = (FileConnection) Connector.open("file:///" + new Date().toString() + ".txt", Connector.WRITE);
            file.create();
            
            result = new PrintStream(file.openDataOutputStream());
        } catch (IOException ex) {
            error = ex;
        }
        
        logFile = result;
        
        if (error != null) {
            error("Could not open log file", error);
        }
    }
}
