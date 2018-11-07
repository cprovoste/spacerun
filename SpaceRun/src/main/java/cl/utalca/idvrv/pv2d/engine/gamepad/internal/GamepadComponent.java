/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.engine.gamepad.internal;

/**
 *
 * @author Pablo Rojas
 */
public class GamepadComponent
{
    private final String name;
    private final ComponentType type;
    private float value;
    private float deadZone;
    
    public GamepadComponent(String name, ComponentType type)
    {
        this.name = name;
        this.type = type;
        this.value = 0;
        this.deadZone = 0;
    }

    public String getName()
    {
        return name;
    }

    public ComponentType getType()
    {
        return type;
    }
    
    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }

    public float getDeadZone()
    {
        return deadZone;
    }

    public void setDeadZone(float deadZone)
    {
        this.deadZone = deadZone;
    }
    
    
}
