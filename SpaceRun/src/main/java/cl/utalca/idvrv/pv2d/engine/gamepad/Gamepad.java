/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.engine.gamepad;

import java.util.ArrayList;

/**
 *
 * @author Pablo Rojas
 */
public class Gamepad
{
    private final String name;
    private final ArrayList<POV> povs;
    private final ArrayList<Axis> axes;
    private final ArrayList<Button> buttons;
    
    public Gamepad(String name)
    {
        this.name = name;
        this.povs = new ArrayList<>();
        this.axes = new ArrayList<>();
        this.buttons = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }
    
    public int numberOfPOVs()
    {
        return this.povs.size();
    }
    
    public int numberOfAxes()
    {
        return this.axes.size();
    }
    
    public int numberOfButtons()
    {
        return this.buttons.size();
    }
    
    public void add(POV pov)
    {
        for (POV stored : this.povs)
        {
            if(stored.getName().equals(pov.getName()))
            {
                return;
            }
        }
        this.povs.add(pov);
    }
    
    public void add(Axis axis)
    {
        for (Axis stored : this.axes)
        {
            if(stored.getName().equals(axis.getName()))
            {
                return;
            }
        }
        this.axes.add(axis);
    }
    
    public void add(Button button)
    {
        for (Button stored : this.buttons)
        {
            if(stored.getName().equals(button.getName()))
            {
                return;
            }
        }
        this.buttons.add(button);
    }
    
    public POV getPOV(int index)
    {
        return this.povs.get(index);
    }
    
    public Axis getAxis(int index)
    {
        return this.axes.get(index);
    }
    
    public Button getButton(int index)
    {
        return this.buttons.get(index);
    }
    
    
    
    
    
    
    
    
    
    
    
}
