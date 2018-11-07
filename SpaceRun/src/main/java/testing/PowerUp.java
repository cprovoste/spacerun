/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import cl.utalca.idvrv.pv2d.engine.BoundingBox;
import java.util.Random;

/**
 *
 * @author Claudia
 */
public class PowerUp extends BoundingBox{
    
    
    Random ran = new Random();
    public PowerUp(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.x = ran.nextInt(300);
        this.y = ran.nextInt(5000)+100;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getHeight() {
        return super.getHeight(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getWidth() {
        return super.getWidth(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(double y) {
        super.setY(y); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getY() {
        return super.getY(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(double x) {
        super.setX(x); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getX() {
        return super.getX(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
