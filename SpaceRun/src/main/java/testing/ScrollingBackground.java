/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import cl.utalca.idvrv.pv2d.engine.BoundingBox;
import java.awt.Graphics2D;

/**
 *
 * @author Claudia
 */
public class ScrollingBackground extends BoundingBox {


    
    public ScrollingBackground(double xMin, double yMin, double width, double height, Graphics2D g) {
        super(xMin, yMin, width, height);
        
    }

    double xMax = this.getX()+this.getWidth();
    double yMax = this.getY()+this.getHeight();
    
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

    
    public void setYMax(double y) {
       this.yMax = y;
    }

    public double getYMax() {
        return yMax; 
    }


    public void setXMax(double x) {
       this.xMax = x;
    }


    public double getXMax() {
       return xMax;
    }
    
}
