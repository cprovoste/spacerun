/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.engine.managers;

/**
 *
 * @author Pablo Rojas
 */
public class ResourceNotFoundException extends Exception 
{

    public ResourceNotFoundException(String resource) 
    {
        super("Resource not found: " + resource);
    }
    
}
