package cl.utalca.idvrv.pv2d.engine;



import cl.utalca.idvrv.pv2d.engine.managers.ImageManager;
import cl.utalca.idvrv.pv2d.engine.managers.ResourceNotFoundException;
import cl.utalca.idvrv.pv2d.engine.util.Converter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public abstract class GameEngine implements KeyEventDispatcher
{
    private boolean finish;
    private long targetTime;
    private boolean showFPS;
    private float avgFPS;
    
    private Window window;
    private BoundingBox viewport;
    
    private HashMap<Integer, Boolean> keyState;
    
    protected Graphics2D buffer;
    
    public GameEngine(Dimension canvasDimension, BoundingBox viewport)
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( this );
        
        this.finish = false;
        this.targetTime = 16; //aumentar valor para compus lentos!!!
        this.showFPS = false;
        this.avgFPS = 60;
        this.window = new Window(canvasDimension);
        this.viewport = viewport;
        this.keyState = new HashMap<>();
        this.initKeyStateMap();
    }
    
    private void initKeyStateMap()
    {
        Field[] fields = KeyEvent.class.getDeclaredFields();
        for (Field field : fields) 
        {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) 
            {
                try
                {
                    this.keyState.put(field.getInt(null), Boolean.FALSE);
                } 
                catch (Exception ex)
                {   
                }
            }
        }
    }

    public BoundingBox getViewport()
    {
        return viewport;
    }

    public void setViewport(BoundingBox viewport)
    {
        this.viewport = viewport;
    }
    
    
    
    public void run()
    {
        //this.window.setVisible(true);
        this.window.showWindowedFrame();
        
        int counter = 0;
        long sum = 0;
        
        
        
        this.finish = false;
        while(!finish)
        {
            long t1 = System.currentTimeMillis();
            this.processInput();
            this.gameUpdate();
            this.render();
            long t2 = System.currentTimeMillis();
            
            long elapsedTime = t2 - t1;
            long difference = this.targetTime - elapsedTime;
            if( difference <= 0 )
            {
                difference = 1;
            }
            
            try
            {
                Thread.sleep(difference);
            } 
            catch (InterruptedException ex)
            {
                
            }
            
            counter++;
            sum += elapsedTime + difference;
            if(counter == 30)
            {
                long avg = sum/counter;
                counter = 0;
                sum = 0;
                avgFPS = 1000/avg;
            }
            
        }
    }
    
    public Graphics2D getGraphics()
    {
        return this.window.getGraphics();
    }
    
    public void switchGraphics()
    {
        if(this.showFPS)
        {
            Graphics2D g = this.getGraphics();
            g.setColor(Color.white);
            g.drawString(String.format("%.2f", this.avgFPS), 50, 50);
            
        }
        
                    
        this.window.switchGraphics();
    }
    
    //Primitivas básicas de dibujo
    public void drawOval( Graphics2D g, double x, double y, double w, double h )
    {
        BoundingBox bbWorld = new BoundingBox(x, y, w, h);
        BoundingBox bbWindow = Converter.toWindow(bbWorld, viewport, this.window.getCanvasDimension());
        g.fillOval( (int)bbWindow.getX(), (int)bbWindow.getY(), (int)bbWindow.getWidth(), (int)bbWindow.getHeight());
    }
    
    public void drawRectangle( Graphics2D g, double x, double y, double w, double h )
    {
        BoundingBox bbWorld = new BoundingBox(x, y, w, h);
        BoundingBox bbWindow = Converter.toWindow(bbWorld, viewport, this.window.getCanvasDimension());
        g.setColor(Color.white);
        g.drawRect((int)bbWindow.getX(), (int)bbWindow.getY(), (int)bbWindow.getWidth(), (int)bbWindow.getHeight());
    }
    
    public void drawImage( Graphics2D g, double x, double y, double w, double h, String filename ) throws ResourceNotFoundException
    {
        BoundingBox bbWorld = new BoundingBox(x, y, w, h);
        BoundingBox bbWindow = Converter.toWindow(bbWorld, viewport, this.window.getCanvasDimension());
        g.drawImage(ImageManager.get(filename), 
                (int)bbWindow.getX(), 
                (int)bbWindow.getY(), 
                (int)bbWindow.getWidth(), 
                (int)bbWindow.getHeight(), 
                null);
    }
    
    
    

    public abstract void processInput();
    public abstract void gameUpdate();
    public abstract void render();
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent e)
    {
        this.keyState.put(e.getKeyCode(), !(e.getID() == KeyEvent.KEY_RELEASED) );
//        
//        if( e.getID() == KeyEvent.KEY_PRESSED )
//        {
//            this.keyState.put(e.getKeyCode(), true);
//        }
//        else if( e.getID() == KeyEvent.KEY_RELEASED )
//        {
//            this.keyState.put(e.getKeyCode(), false);
//        }
//        else if( e.getID() == KeyEvent.KEY_TYPED )
//        {
//            this.keyState.put(e.getKeyCode(), true);
//        }
        return true;
    }
    
    public boolean isKeyPressed( Integer key )
    {
        Boolean state = this.keyState.get(key);
        if(state == null)
        {
            state = Boolean.FALSE;
        }
        return state;
    }

    public void setShowFpsStatus(boolean showFPS) 
    {
        this.showFPS = showFPS;
    }
}
