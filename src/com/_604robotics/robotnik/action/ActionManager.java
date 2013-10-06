package com._604robotics.robotnik.action;

import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.networking.IndexedTable;
import java.util.Enumeration;
import java.util.Hashtable;

public class ActionManager {
    private final Hashtable actionTable = new Hashtable();
    
    private final ActionController actionController;
    
    private final IndexedTable statusTable;
    private final IndexedTable triggerTable;
    
    public ActionManager (ModuleReference module, ActionController actionController, IndexedTable table) {
        this.actionController = actionController;
        
        this.statusTable = table.getSubTable("status");
        this.triggerTable = table.getSubTable("triggers");
        
        this.statusTable.putString("triggeredAction", "");
        this.statusTable.putString("lastAction", "");
        
        final Enumeration actionNames = actionController.enumerateNames();
        
        String name;
        Action action;
        
        while (actionNames.hasMoreElements()) {
            name = (String) actionNames.nextElement();
            action = actionController.getAction(name);
            
            this.actionTable.put(name, new ActionReference(module, action, table, name));
        }
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
        final Enumeration i = this.actionTable.elements();
        
        while (i.hasMoreElements()) {
            ((ActionReference) i.nextElement()).reset();
        }
    }
    
    public void update () {
        String topAction = "";
        double topPrecedence = 0D;
        
        final Enumeration actionNames = actionController.enumerateNames();
        
        String name;
        double precedence;
        
        while (actionNames.hasMoreElements()) {
            name = (String) actionNames.nextElement();
            precedence = this.triggerTable.getNumber(name, 0D);
            
            if (precedence > 0 && precedence > topPrecedence) {
                topAction = name;
                topPrecedence = precedence;
            }
        }
        
        this.statusTable.putString("triggeredAction", topAction);
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