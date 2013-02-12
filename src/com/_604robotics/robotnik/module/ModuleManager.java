package com._604robotics.robotnik.module;

import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Enumeration;
import java.util.Hashtable;

public class ModuleManager {
    private final Hashtable moduleTable = new Hashtable();
    
    public ModuleManager (ModuleMap moduleMap, IndexedTable table) {
        final Enumeration moduleNames = moduleMap.getModuleNames();
        
        String name;
        Module module;
        
        while (moduleNames.hasMoreElements()) {
            name = (String) moduleNames.nextElement();
            module = moduleMap.getModule(name);
            
            this.moduleTable.put(name, new ModuleReference(module, table.getSubTable(name)));
        }
    }
    
    public ModuleReference getModule (String name) {
        return (ModuleReference) this.moduleTable.get(name);
    }
    
    public void update () {
        final Enumeration i = this.moduleTable.elements();
        
        while (i.hasMoreElements()) {
            ((ModuleReference) i.nextElement()).update();
        }
    }
    
    public void execute () {
        final Enumeration i = this.moduleTable.elements();
        
        while (i.hasMoreElements()) {
            ((ModuleReference) i.nextElement()).execute();
        }
    }
    
    public void end () {
        final Enumeration i = this.moduleTable.elements();
        
        while (i.hasMoreElements()) {
            ((ModuleReference) i.nextElement()).end();
        }
    }
}