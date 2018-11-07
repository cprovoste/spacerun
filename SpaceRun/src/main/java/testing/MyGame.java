package testing;

import cl.utalca.idvrv.pv2d.UI.Menu;
import cl.utalca.idvrv.pv2d.engine.BoundingBox;
import cl.utalca.idvrv.pv2d.engine.GameEngine;
import cl.utalca.idvrv.pv2d.engine.gamepad.Gamepad;
import cl.utalca.idvrv.pv2d.engine.gamepad.Gamepads;
import cl.utalca.idvrv.pv2d.engine.gamepad.POV;
import cl.utalca.idvrv.pv2d.engine.managers.ResourceNotFoundException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MyGame extends GameEngine
{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean enter;
    
    private int vidas = 3;
    protected int score = 0;
    protected boolean gameOver = false;
    public static int state = 0;
    private boolean shield = false;

    private float dx;
    private float dy;
    
    int axisID = 4;
    int gamepadID = 0;
    int button = 1;
    private double pos;
    Graphics2D g = super.getGraphics();
    
    Random ran = new Random();
    private float speed;
    private Menu menu;
    boolean bounds;
    
    ArrayList<Obstacle> obs = new ArrayList<>();
    ArrayList<PowerUp> ups = new ArrayList<>();
    
    
    Ship player = new Ship(120, 20, 25, 25, g);
    ScrollingBackground one = new ScrollingBackground( 0 , 0 , 300, 300, g);
    ScrollingBackground two = new ScrollingBackground( 0 , 0 , 300, 300, g);
    Rectangle BBship;
    Rectangle BBmeteor;
    Rectangle BBpowerup;

    ArrayList<BoundingBox> lives = new ArrayList<>();
    BoundingBox life_icon = new BoundingBox(10,280,10,10);
    BoundingBox shield_icon = new BoundingBox(this.player.getX(),this.player.getY(),30,30);
    
    public MyGame(boolean showFPS)
    {
        super(new Dimension(800, 600), new BoundingBox(0, 0, 300, 300));
        super.setShowFpsStatus(showFPS); 
        this.initObstacles();
        this.initLives();
        this.initPowerUps();
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
        if(vidas == 0)
        {
            init();
            
        }if(this.enter && state == 0)
        {
            vidas = 3;
            state=2;   
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

            }
            this.player.setY(this.player.getY() + 1);
            this.shield_icon.setY(this.player.getY());
            this.shield_icon.setX(this.player.getX()-2);
            super.getViewport().setY(super.getViewport().getY() + 1);
            this.life_icon.setY(this.life_icon.getY() + 1);
            this.BBship= new Rectangle((int)this.player.getX(), (int)this.player.getY(), (int)this.player.getWidth(), (int)this.player.getHeight());
            for (BoundingBox hp : lives) {
                hp.setY(hp.getY()+1);
                pos = hp.getY();
            }
            
            for (int i = 0; i < obs.size(); i++) {
                obs.get(i).setY(obs.get(i).getY()-obs.get(i).speed);
                this.BBmeteor = new Rectangle((int)obs.get(i).getX(),(int)obs.get(i).getY(),(int)obs.get(i).getWidth(),(int)obs.get(i).getHeight());   
                if(BBship.intersects(BBmeteor) && !this.shield)
                {
                    obs.remove(i);
                    vidas--;
                    lives.remove(vidas);
                }else if(BBship.intersects(BBmeteor) && this.shield)
                {
                    obs.remove(i);
                    this.shield=false;
                }
                
            }
            for (int i = 0; i < ups.size(); i++) {
                this.BBpowerup = new Rectangle((int)ups.get(i).getX(),(int)ups.get(i).getY(),(int)ups.get(i).getWidth(),(int)ups.get(i).getHeight());   
                if(BBship.intersects(BBpowerup))
                {
                    ups.remove(i);
                    this.shield = true;
                }
                
            }
           score++;
        }
        
    }
        
       
    @Override
    public void render()
    {
        try 
        {
           if(state == 0){
                super.drawImage(g, 0, 0, 300, 300, "stars.png");
                menu.render(g);
            }else if(state == 2){
               
                for(int suma=0; suma <= 3000; suma+=300)
                {
                    super.drawImage( g, one.getX(), suma, one.getWidth(), one.getHeight(), "darkPurple.png");
                    super.drawImage( g, two.getX(), suma+300, two.getWidth(), two.getHeight(), "darkPurple.png");
                    for (Obstacle ob : obs) {
                        super.drawImage(g, ob.getX(), ob.getY(), ob.getWidth(), ob.getHeight(), ob.getFilename());
                    }
                }
                super.drawImage(g, player.getX(), player.getY(), player.getWidth(), player.getHeight(), "player.png");
                g.drawString(Integer.toString(score), 700,80);
                if(this.shield == true)
                {
                    super.drawImage(g, this.shield_icon.getX(), this.shield_icon.getY(), this.shield_icon.getWidth(), this.shield_icon.getHeight(), "shield3.png");
                }
                for (BoundingBox hp : lives) {
                   super.drawImage(g, hp.getX(), hp.getY(), hp.getWidth(), hp.getHeight(), "playerLife3_green.png");
               }
                for (PowerUp up : ups) {
                   super.drawImage(g, up.getX(), up.getY(), up.getWidth(), up.getHeight(), "shield_gold.png");
               }
            }
            
        }catch (ResourceNotFoundException ex) 
        {
            System.out.println("Error al leer una imagen");
        }
        super.switchGraphics();
    }
    void init(){
        super.getViewport().setY(0);
        this.obs.clear();
        this.lives.clear();
        this.score = 0;
        this.gameOver = false;
        this.shield = false;
        this.shield_icon.setX(120);
        this.shield_icon.setY(20);
        this.player.setX(120);
        this.player.setY(20);
        initObstacles();
        initLives();
        state=0;
    }
     public void initObstacles()
    {
        for (int i = 0; i < 15; i++) {
            obs.add(new Obstacle(0,0,18,18,"meteorBrown_tiny1.png"));
            obs.add(new Obstacle(0,0,26,26,"meteorBrown_med1.png"));
            obs.add(new Obstacle(0,0,50,50,"meteorBrown_big3.png"));
            this.menu = new Menu();
        }
        
    }
   public void initLives()
    {
        lives.add(new BoundingBox(10, 250, 10, 10));
        lives.add(new BoundingBox(25, 250, 10, 10));
        lives.add(new BoundingBox(40, 250, 10, 10));
        
    }
     public void initPowerUps()
    {
        for (int i = 0; i < 20; i++) {
            ups.add(new PowerUp(0,0,10,10)); 
        }
        
    }

    private void showScore() {
       Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
               g.drawString(String.valueOf(score), (int)life_icon.getX(), (int)life_icon.getY());
            }
        }, 0, 10000);
    }

}

