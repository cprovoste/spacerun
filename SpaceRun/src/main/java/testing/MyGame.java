package testing;



import cl.utalca.idvrv.pv2d.UI.Menu;
import cl.utalca.idvrv.pv2d.engine.BoundingBox;
import cl.utalca.idvrv.pv2d.engine.GameEngine;
import cl.utalca.idvrv.pv2d.engine.gamepad.Gamepad;
import cl.utalca.idvrv.pv2d.engine.gamepad.Gamepads;
import cl.utalca.idvrv.pv2d.engine.gamepad.POV;
import cl.utalca.idvrv.pv2d.engine.managers.ResourceNotFoundException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MyGame extends GameEngine
{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean enter;
    
    private int vidas = 4;
    private int enemy_killed = 0;
    
    private float dx;
    private float dy;
    
    int axisID = 4;
    int gamepadID = 0;
    
    Random ran = new Random();
    private float speed;
    
    Graphics2D g = super.getGraphics();

    private Menu menu;
    protected boolean gameOver = false;
    
    public static int state = 0;
    
    boolean bounds;
    
    ArrayList<Obstacle> obs = new ArrayList<>();
    
    Ship player = new Ship(120, 20, 25, 25, g);
    ScrollingBackground one = new ScrollingBackground( 0 , 0 , 300, 300, g);
    ScrollingBackground two = new ScrollingBackground( 0 , 0 , 300, 300, g);
    Rectangle BBship;
    Rectangle BBmeteor;
    
    public MyGame(boolean showFPS)
    {
        super(new Dimension(800, 600), new BoundingBox(0, 0, 300, 300));
        super.setShowFpsStatus(showFPS);
       
        for (int i = 0; i < 11; i++) {
            obs.add(new Obstacle(0,0,18,18,"meteorBrown_tiny1.png"));
            obs.add(new Obstacle(0,0,26,26,"meteorBrown_med1.png"));
            obs.add(new Obstacle(0,0,50,50,"meteorBrown_big3.png"));
            //this.obs.get(i).setFilename("meteorBrown_tiny1.png");
            this.menu = new Menu();
        }
    }

    @Override
    public void processInput()
    {

        this.left = super.isKeyPressed(KeyEvent.VK_LEFT);
        this.right = super.isKeyPressed(KeyEvent.VK_RIGHT);
        this.up = super.isKeyPressed(KeyEvent.VK_UP);
        this.down = super.isKeyPressed(KeyEvent.VK_DOWN);
        this.enter = super.isKeyPressed(KeyEvent.VK_ENTER);

        
        if( Gamepads.size() > 0 )
        {
            Gamepad gamepad = Gamepads.get(this.gamepadID);
            if(gamepad.numberOfPOVs() > 0)
            {
                if(gamepad.getPOV(0).getDirection() == POV.Direction.RIGHT)
                {
                    this.right = true;
                }
                
                if(gamepad.getPOV(0).getDirection() == POV.Direction.LEFT)
                {
                    this.left = true;
                }
                if(gamepad.getPOV(0).getDirection() == POV.Direction.UP)
                {
                    this.up = true;
                }
                if(gamepad.getPOV(0).getDirection() == POV.Direction.DOWN)
                {
                    this.down = true;
                }
                if(gamepad.getPOV(0).getDirection() == POV.Direction.UP_RIGHT)
                {
                    this.up = true;
                    this.right = true;
                }
                if(gamepad.getPOV(0).getDirection() == POV.Direction.UP_LEFT)
                {
                    this.up = true;
                    this.left = true;
                }
                if(gamepad.getPOV(0).getDirection() == POV.Direction.DOWN_RIGHT)
                {
                    this.down = true;
                    this.right = true;
                }
                if(gamepad.getPOV(0).getDirection() == POV.Direction.DOWN_LEFT)
                {
                    this.down = true;
                    this.left = true;
                }
            }
            if(gamepad.numberOfAxes() > 0)
            {
                float deadZone = 0.1f;
                this.dx = gamepad.getAxis(axisID).getValue();
                
                if( this.dx > deadZone )
                {
                    this.right = true;
                }
                else if( this.dx < -deadZone )
                {
                    this.left = true;
                }
                if( this.dy > deadZone )
                {
                    this.up = true;
                }
                else if( this.dy < -deadZone )
                {
                    this.down = true;
                }
            }
            
        }
        
        
        
    }

    @Override
    public void gameUpdate()
    {
        if(this.enter)
        {
            if(state == 0)
            {
                state=1;
            }
        }
        if(state == 2){
           if( this.left )
            {
                if(this.dx == 0)
                {
                    this.dx = -1;
                }
                this.player.setX(this.player.getX() + this.dx);
                if(this.player.getX() <= 0){
                    this.player.setX(0);
                }
            this.dx = 0;
        }
        
        if( this.right )
        {
            if(this.dx == 0)
            {
                this.dx = +1;
            }
            this.player.setX(this.player.getX() + this.dx);
            if(this.player.getX() >= 300 - 25){
                this.player.setX(300 - 25);
            }
            this.dx = 0;
        }
        
        if( this.up )
        {
             if(this.dy == 0)
            {
                this.dy = 1;
            }
            this.player.setY(this.player.getY() + this.dy);
            if(this.player.getY() >= super.getViewport().getY()+super.getViewport().getHeight()-this.player.getHeight()){
                this.player.setY(super.getViewport().getY()+super.getViewport().getHeight()-this.player.getHeight());
            }
            this.dy=0;
        }
        
        if( this.down )
        {
             if(this.dy == 0)
            {
                this.dy = -1;
            }
            this.player.setY(this.player.getY() + this.dy);
            if(this.player.getY() <= super.getViewport().getY()){
                this.player.setY(super.getViewport().getY());
            }
            this.dy=0;
           // super.getViewport().setY( super.getViewport().getY() + 1 );
        }
        
        
        this.player.setY(this.player.getY() + 0.6);
        super.getViewport().setY(super.getViewport().getY() + 0.6);
        this.BBship= new Rectangle((int)this.player.getX(), (int)this.player.getY(), (int)this.player.getWidth(), (int)this.player.getHeight());
        for (int i = 0; i < obs.size(); i++) {
            
            this.BBmeteor = new Rectangle((int)obs.get(i).getX(),(int)obs.get(i).getY(),(int)obs.get(i).getWidth(),(int)obs.get(i).getHeight());   
            if(BBship.intersects(BBmeteor))
            {
                obs.remove(i);
                vidas--;
                System.out.println(vidas);
            }
        }
       } 
    }
        
       
    @Override
    public void render()
    {
        try 
        {
            super.drawImage(g, 0, 0, 300, 300, "stars.png");
            if(state == 2)
            {
                for(int suma=0; suma <= 3000; suma+=300)
                {
                    super.drawImage( g, one.getX(), suma, one.getWidth(), one.getHeight(), "darkPurple.png");
                    super.drawImage( g, two.getX(), suma+300, two.getWidth(), two.getHeight(), "darkPurple.png");
                    for (Obstacle ob : obs) {
                        super.drawImage(g, ob.getX(), ob.getY(), ob.getWidth(), ob.getHeight(), ob.getFilename());
                        super.drawRectangle(g, ob.getX(), ob.getY(), ob.getWidth(), ob.getHeight());
                    }
                }
                super.drawImage(g, player.getX(), player.getY(), player.getWidth(), player.getHeight(), "player.png");
                super.drawRectangle(g,player.getX(), player.getY(), player.getWidth(), player.getHeight());
            }else if(state == 0){
                menu.render(g);
            }
            
        }catch (ResourceNotFoundException ex) 
        {
            System.out.println("Error al leer una imagen");
        }
            super.switchGraphics();
        }
}
