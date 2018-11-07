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
public class ComponentNotFoundException extends Exception
{

    public ComponentNotFoundException(String name)
    {
        super("Gamepad component not found : " + name);
    }
    
}
