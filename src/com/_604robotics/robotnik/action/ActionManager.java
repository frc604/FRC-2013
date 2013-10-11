package com._604robotics.robotnik.action;

import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.meta.Scorekeeper;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Enumeration;
import java.util.Hashtable;

public class ActionManager {
    private final Hashtable actionTable;
    
    private final ActionController actionController;
    
    private final IndexedTable statusTable;
    private final IndexedTable triggerTable;
    
    public ActionManager (final ModuleReference module, ActionController actionController, final IndexedTable table) {
        this.actionController = actionController;
        
        this.statusTable = table.getSubTable("status");
        this.triggerTable = table.getSubTable("triggers");
        
        this.statusTable.putString("triggeredAction", "");
        this.statusTable.putString("lastAction", "");
        
        this.actionTable = Repackager.repackage(actionController.iterate(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new ActionReference(module, (Action) value, table, (String) key);
           }
        });
    }
    
    public ActionReference getAction (String name) {
        ActionReference ref = (ActionReference) this.actionTable.get(name);
        
        if (ref == null) {
            System.err.println("WARNING: Missing ActionReference - " + name);
            new Error().printStackTrace();
        }
        
        return ref;
    }
    
    public void reset () {
        final Iterator i = new Iterator(this.actionTable);
        
        while (i.next()) {
            ((ActionReference) i.value).reset();
        }
    }
    
    public void update () {
        final Scorekeeper r = new Scorekeeper();
        final Iterator i = actionController.iterate();
        
        while (i.next()) {
            r.consider(i.key, this.triggerTable.getNumber((String) i.key, 0D));
        }
        
        this.statusTable.putString("triggeredAction", r.score > 0 ? (String) r.victor : "");
    }
    
    public void execute () {
        final String triggeredAction = this.statusTable.getString("triggeredAction", "");
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        final String selectedAction = this.actionController.pickAction(lastAction, triggeredAction);
        
        if (!lastAction.equals("")) {
            if (!lastAction.equals(selectedAction)) {
                this.getAction(lastAction).end();
            }
        }

        if (!selectedAction.equals("")) {
            final ActionReference action = this.getAction(selectedAction);

            if (lastAction.equals("") || !lastAction.equals(selectedAction)) {
                action.begin();
            }
            
            action.run();
        }
        
        this.statusTable.putString("lastAction", selectedAction);
    }
    
    public void end () {
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        if (!lastAction.equals("")) {
            this.getAction(lastAction).end();
        }
        
        this.statusTable.putString("lastAction", "");
    }
}