/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Claudia
 */
public class Menu {
    
    public Rectangle playButton = new Rectangle(325,350, 100,50);

        
    public void render(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font f = new Font("Lato", Font.BOLD, 50);
        g.setFont(f);
        g.setColor(Color.white);
        g.drawString("SPACE RUN", 240, 200);
        
        Font f2 = new Font("Lato", Font.BOLD, 30);
        g.setFont(f2);
        g.setColor(Color.white);
        g.drawString("PLAY",playButton.x+15, playButton.y+35);
        g2d.draw(playButton);
        g.drawString("Press enter...", playButton.x-25, playButton.y+90);
    }
}
