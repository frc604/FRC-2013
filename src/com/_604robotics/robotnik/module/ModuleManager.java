package com._604robotics.robotnik.module;

import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.utils.InternalLogger;
import java.util.Hashtable;

public class ModuleManager {
    private final Hashtable moduleTable = new Hashtable();
    
    public ModuleManager (ModuleMap moduleMap, final IndexedTable table) {
        Repackager.repackage(moduleMap.iterate(), new Repackager() {
            public Object wrap (Object key, Object value) {
                return new ModuleReference((String) key, (Module) value, table.getSubTable((String) key));
            }
        });
    }
    
    public ModuleReference getModule (String name) {
        ModuleReference ref = (ModuleReference) this.moduleTable.get(name);
        if (ref == null) InternalLogger.missing("ModuleReference", name);
        return ref;
    }
    
    public void update () {
        final Iterator i = new Iterator(this.moduleTable);
        while (i.next()) ((ModuleReference) i.value).update();
    }
    
    public void execute () {
        final Iterator i = new Iterator(this.moduleTable);
        while (i.next()) ((ModuleReference) i.value).execute();
    }
}