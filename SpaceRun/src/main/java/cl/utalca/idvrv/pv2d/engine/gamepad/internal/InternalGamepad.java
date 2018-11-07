/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.engine.gamepad.internal;


import java.util.ArrayList;
import java.util.Iterator;
import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 *
 * @author Pablo Rojas
 */
public class InternalGamepad implements Iterable<GamepadComponent>
{
    private final Controller controller;
    private final ArrayList<GamepadComponent> components;
    
    public InternalGamepad(Controller controller)
    {
        this.controller = controller;
        this.components = new ArrayList<>();
        this.initialize();
    }
    
    public String getName()
    {
        return this.controller.getName();
    }
    
    private void initialize()
    {
        Component[] controllerComponents = this.controller.getComponents();
        for (Component component : controllerComponents) 
        {
            if(component.getIdentifier() instanceof Component.Identifier.Button)
            {
                this.add(component.getName(), ComponentType.BUTTON);
            }
            else if(component.getIdentifier() instanceof Component.Identifier.Axis ) 
            {
                if(component.getIdentifier() != Component.Identifier.Axis.POV)
                {
                    this.add(component.getName(), ComponentType.AXIS);
                }
                else
                {
                    this.add(component.getName(), ComponentType.POV);
                }
            }
        }
    }
    
    public void add(String name, ComponentType type)
    {
        this.components.add(new GamepadComponent(name, type));
    }
    
    public GamepadComponent get(String name, ComponentType type) throws ComponentNotFoundException
    {
        for (GamepadComponent component : components)
        {
            if(component.getName().equals(name) && component.getType() == type)
            {
                return component;
            }
        }
        throw new ComponentNotFoundException(name);
    }
    
    public void poll()
    {
        Component[] components = this.controller.getComponents();
        this.controller.poll();
        for (Component component : components) 
        {
            try
            {
                ComponentType type = ComponentType.AXIS;
                
                if(component.getIdentifier() instanceof Component.Identifier.Axis )
                {
                    if(component.getIdentifier() == Component.Identifier.Axis.POV)
                    {
                        type = ComponentType.POV;
                    }
                    else
                    {
                        type = ComponentType.AXIS;
                    }
                }
                else if(component.getIdentifier() instanceof Component.Identifier.Button)
                {
                    type = ComponentType.BUTTON;
                }
                
                this.get(component.getName(), type).setValue(component.getPollData());
                this.get(component.getName(), type).setDeadZone(component.getDeadZone());
            }
            catch(ComponentNotFoundException ex)
            {
            }
        }
    }

    @Override
    public Iterator<GamepadComponent> iterator()
    {
        return this.components.iterator();
    }
}
