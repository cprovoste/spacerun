package cl.utalca.idvrv.pv2d.engine.gamepad;

import cl.utalca.idvrv.pv2d.engine.gamepad.internal.GamepadComponent;
import cl.utalca.idvrv.pv2d.engine.gamepad.internal.GamepadEnvironment;
import cl.utalca.idvrv.pv2d.engine.gamepad.internal.InternalGamepad;



/**
 *
 * @author Pablo Rojas
 */
public class Gamepads
{
    static public int size()
    {
        return GamepadEnvironment.getInstance().size();
    }
    
    static public Gamepad get(int index)
    {
        return  Gamepads.toPublicGamepad(GamepadEnvironment.getInstance().get(index));
    }
    
    static private Gamepad toPublicGamepad(InternalGamepad internalGamepad)
    {
        Gamepad gamepad = new Gamepad(internalGamepad.getName());
        for (GamepadComponent component : internalGamepad)
        {
            String name = component.getName();
            float value = component.getValue();
            float deadZone = component.getDeadZone();
            
            switch(component.getType())
            {
                case AXIS  : gamepad.add( new Axis(name, value, deadZone) ); break;
                case POV   : gamepad.add(new POV(name, value));    break;
                case BUTTON: gamepad.add(new Button(name, value)); break;
            }
        }
        return gamepad;
    }
}
