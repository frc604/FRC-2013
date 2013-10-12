package com._604robotics.robotnik.module;

import com._604robotics.robotnik.action.ActionManager;
import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.data.DataManager;
import com._604robotics.robotnik.data.DataReference;
import com._604robotics.robotnik.networking.IndexedTable;
import com._604robotics.robotnik.trigger.TriggerManager;
import com._604robotics.robotnik.trigger.TriggerReference;
import java.util.Enumeration;

public class ModuleReference {
    private final Module module;
    
    private final DataManager dataManager;
    private final TriggerManager triggerManager;
    
    private final ActionManager actionManager;
    
    public ModuleReference (Module module, IndexedTable table) {
        this.dataManager = new DataManager(module.getDataMap(), table.getSubTable("data"));
        this.triggerManager = new TriggerManager(module.getTriggerMap(), table.getSubTable("triggers"));
        
        this.actionManager = new ActionManager(this, module.getActionController(), table.getSubTable("actions"));
        
        this.module = module;
        module.apply(this);
    }
    
    public DataReference getData (String name) {
        return this.dataManager.getData(name);
    }
    
    public TriggerReference getTrigger (String name) {
        return this.triggerManager.getTrigger(name);
    }
    
    public ActionReference getAction (String name) {
        return this.actionManager.getAction(name);
    }
    
    public void update () {
        this.dataManager.update();
        this.triggerManager.update();
        
        this.actionManager.reset();
        
        final Enumeration wires = this.module.enumerateWires();
        DataWire wire;
        
        while (wires.hasMoreElements()) {
            wire = (DataWire) wires.nextElement();
            
            wire.getRecipient().sendData(wire.getFieldName(), wire.getData().get());
        }
        
        final Enumeration bindings = this.module.enumerateBindings();
        Binding binding;
        
        while (bindings.hasMoreElements()) {
            binding = (Binding) bindings.nextElement();
            
            if (binding.getTrigger().get()) {
                binding.getRecipient().sendTrigger(binding.isSafety() ? 2D : 1D);
            }
        }
    }
    
    public void begin () {
        this.dataManager.begin();
        this.triggerManager.begin();
    }
    
    public void execute () {
        this.actionManager.update();
        this.actionManager.execute();
    }
    
    public void end () {
        this.actionManager.end();
        this.triggerManager.end();
        this.dataManager.end();
    }
}