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
public class Obstacle extends BoundingBox{

    String filename;
    Random ran = new Random();
    
    public Obstacle(double x, double y, double width, double height, String filename) {
        super(x, y, width, height);
        this.x = ran.nextInt(300);
        this.y = ran.nextInt(3000)+10;
        this.filename = filename;
    }

    public String getFilename(){
        
        return this.filename;
    }
    
    public void setFilename(String filename){
        this.filename = filename;
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
        
    }

    @Override
    public double getY() {
        return super.getY(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(double x) {
       
    }

    @Override
    public double getX() {
        
        return super.getX(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
