/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.engine.gamepad;

/**
 *
 * @author Pablo Rojas
 */
class GamepadComponent
{
    private final String name;
    private float value;
    
    public GamepadComponent(String name, float value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }
    
    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }
}
