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
public class Axis extends GamepadComponent
{
    private final float deadZone;
    
    public Axis(String name, float value, float deadZone)
    {
        super(name, value);
        this.deadZone = deadZone;
    }

    public float getDeadZone()
    {
        return deadZone;
    }
}
