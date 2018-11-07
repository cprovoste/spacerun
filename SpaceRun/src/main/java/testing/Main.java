package testing;

import cl.utalca.idvrv.pv2d.engine.managers.ImageManager;
import cl.utalca.idvrv.pv2d.engine.managers.ResourceNotFoundException;
import java.awt.image.BufferedImage;

public class Main
{

    public static void main(String[] args)
    {
        if( ImageManager.register("player.png") && ImageManager.register("darkPurple.png") &&
               ImageManager.register("meteorBrown_big3.png")  && ImageManager.register("meteorBrown_med1.png") &&
                  ImageManager.register("meteorBrown_tiny1.png") && ImageManager.register("stars.png") && ImageManager.register("playerLife3_green.png")
                && ImageManager.register("shield_gold.png") && ImageManager.register("shield3.png"))
        {
            System.out.println("Carga correcta");
        }
        else
        {
            System.out.println("Error");
        }
        
        try 
        {
            BufferedImage bg = ImageManager.get("darkPurple.png");
            System.out.println("OK background");
            BufferedImage player = ImageManager.get("player.png");
            System.out.println("OK player.png");
            BufferedImage meteorBig = ImageManager.get("meteorBrown_big3.png");
            System.out.println("OK meteorBrown_big3.png");
            BufferedImage meteorMed = ImageManager.get("meteorBrown_med1.png");
            System.out.println("OKmeteorBrown_med1.png");
            BufferedImage meteorTiny = ImageManager.get("meteorBrown_tiny1.png");
            System.out.println("OK meteorBrown_tiny1.png");
            BufferedImage starsMenu = ImageManager.get("stars.png");
            System.out.println("OK STARS MENU");
            BufferedImage lifeIcon = ImageManager.get("playerLife3_green.png");
            System.out.println("OK lifeicon");
            BufferedImage shieldicon = ImageManager.get("shield_gold.png");
            System.out.println("OK Shieldicon");
            BufferedImage shield = ImageManager.get("shield3.png");
            System.out.println("OK shield");
            
            
        } 
        catch (ResourceNotFoundException ex) 
        {
            System.out.println("No existe el recurso");
        }

        MyGame game = new MyGame(true);
        game.run();
    } 
    
}
