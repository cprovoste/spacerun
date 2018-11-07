package cl.utalca.idvrv.pv2d.engine.gamepad.internal;

import java.util.ArrayList;
import java.util.HashMap;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.ControllerEvent;
import net.java.games.input.ControllerListener;

public class GamepadEnvironment 
{
    static private GamepadEnvironment INSTANCE;
    
    static public GamepadEnvironment getInstance()
    {
        if( GamepadEnvironment.INSTANCE == null )
        {
            GamepadEnvironment.INSTANCE = new GamepadEnvironment();
        }
        GamepadEnvironment.INSTANCE.poll();
        return GamepadEnvironment.INSTANCE;
    }
    
    private final ArrayList<InternalGamepad> gamepadsList;
    private final HashMap<Controller, InternalGamepad> gamepadsMap;
    private final GamepadEnvironmentListener listener;
    
    private GamepadEnvironment()
    {
        this.gamepadsList = new ArrayList<>();
        this.gamepadsMap = new HashMap<>();
        this.listener = new GamepadEnvironmentListener();
        
        ControllerEnvironment environment = ControllerEnvironment.getDefaultEnvironment();
        environment.addControllerListener(listener);
        Controller[] controllers = environment.getControllers();
        for (Controller controller : controllers)
        {
            if(controller.getType() == Type.GAMEPAD)
            {
                this.add(controller);
            }
        }
    }
    
    private void add(Controller controller)
    {
        synchronized(this)
        {
            if(this.gamepadsMap.get(controller) == null )
            {
                InternalGamepad gamepad = new InternalGamepad(controller);
                this.gamepadsMap.put(controller, gamepad);
                this.gamepadsList.add(gamepad);
            }
        }
    }
    
    private void remove(Controller controller)
    {
        synchronized(this)
        {
            InternalGamepad gamepad = this.gamepadsMap.remove(controller);
            if(gamepad != null )
            {
                this.gamepadsList.remove(gamepad);
            }
        }
    }
    
    public void poll()
    {
        for(InternalGamepad gamepad : this.gamepadsMap.values())
        {
            gamepad.poll();
        }
    }
    
    public int size()
    {
        return this.gamepadsList.size();
    }
    
    public InternalGamepad get(int index)
    {
        synchronized(this)
        {
            return this.gamepadsList.get(index);
        }
    }
    
    class GamepadEnvironmentListener implements ControllerListener
    {
        @Override
        public void controllerRemoved(ControllerEvent ce)
        {
            System.out.flush();
            GamepadEnvironment.this.remove(ce.getController());
        }

        @Override
        public void controllerAdded(ControllerEvent ce)
        {
            System.out.flush();
            GamepadEnvironment.this.add(ce.getController());
        }
    }
    
    

    
}
