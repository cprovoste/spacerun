
package cl.utalca.idvrv.pv2d.engine.gamepad;

/**
 *
 * @author Pablo Rojas
 */
public class Button extends GamepadComponent
{
    public Button(String name, float value)
    {
        super(name, value);
    }
    
    public boolean isPressed()
    {
        return super.getValue() != 0;
    }
    
}
