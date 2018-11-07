package cl.utalca.idvrv.pv2d.engine;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Window extends JFrame
{
    private final Canvas canvas;
    private BufferStrategy strategy;
    private boolean initialized;
    private Dimension canvasDimension;
    
    private final GraphicsDevice graphicsDevice;
    private boolean fullscreen;

    public Window(Dimension canvasDimension)
    {
        super("Space Run - 2018/2");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        this.fullscreen = false;
        
        this.canvasDimension = canvasDimension;
        this.initialized = false;
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(canvasDimension);
        super.getContentPane().add(this.canvas);      
        super.pack();
        
    }

    public Dimension getCanvasDimension()
    {
        return canvasDimension;
    }

    public void setCanvasDimension(Dimension canvasDimension)
    {
        this.canvasDimension = canvasDimension;
        this.canvas.setPreferredSize(canvasDimension);
        super.pack();
    }

    @Override
    public void paint(Graphics g) {
        
        super.paint(g); 
    }

    public Graphics2D getGraphics()
    {
        if(!this.initialized)
        {
            this.canvas.createBufferStrategy(2);
            this.strategy = this.canvas.getBufferStrategy();
            this.initialized = true;
        }
        
        Graphics2D g = (Graphics2D)this.strategy.getDrawGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        return g;
    }
    
    public void switchGraphics()
    {
        this.strategy.show();
    }
    
    
    public void showFullScreenFrame()
    {
        if(this.graphicsDevice.isFullScreenSupported())
        {
            this.setVisible(false);
            this.dispose();
            this.setUndecorated(true);
            this.setResizable(false);
            this.setAlwaysOnTop(true);
            
            DisplayMode mode = new DisplayMode(1366, 768, 32, 60);
            this.graphicsDevice.setFullScreenWindow(this);
            this.graphicsDevice.setDisplayMode(mode);
            
            this.validate();
            this.fullscreen = true;
        }
        else
        {
            this.showWindowedFrame();
        }
    }
    
    public void showWindowedFrame()
    {
        this.setVisible(false);
        this.dispose();
        this.setUndecorated(false);
        this.setVisible(true);
        this.setResizable(false);
        this.setAlwaysOnTop(false);
        
        this.setCanvasDimension(canvasDimension);
        this.pack();
        this.validate();
        this.fullscreen = false;
    }

    
}
