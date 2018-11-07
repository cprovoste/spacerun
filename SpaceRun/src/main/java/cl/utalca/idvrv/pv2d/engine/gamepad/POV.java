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
public class POV extends GamepadComponent
{
    public POV(String name, float value)
    {
        super(name, value);
    }

    public Direction getDirection()
    {
        return Direction.get(super.getValue());
    }
    
    public enum Direction
    {
        UP(0.25f),
        DOWN(0.75f),
        LEFT(1.0f),
        RIGHT(0.5f),
        UP_LEFT(0.125f),
        UP_RIGHT(0.375f),
        DOWN_LEFT(0.875f),
        DOWN_RIGHT(0.625f),
        NONE(0f);
   
        float value;

        private Direction(float value)
        {
            this.value = value;
        }
        
        static private Direction get(float value)
        {
            Direction[] directions = Direction.values();
            for (Direction direction : directions)
            {
                if(direction.value == value)
                {
                    return direction;
                }
            }
            return Direction.NONE;
        }
        
        
    }

}
