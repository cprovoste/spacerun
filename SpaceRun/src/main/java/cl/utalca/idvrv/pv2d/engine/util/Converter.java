/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.utalca.idvrv.pv2d.engine.util;


import cl.utalca.idvrv.pv2d.engine.BoundingBox;
import java.awt.Dimension;

/**
 *
 * @author Pablo Rojas
 */
public class Converter
{
    static public BoundingBox toWindow(BoundingBox bbWorld, BoundingBox viewport, Dimension window)
    {
        double xm0 = bbWorld.getX();
        double ym0 = bbWorld.getY();
        double wm0 = bbWorld.getWidth();
        double hm0 = bbWorld.getHeight();
        
        double xm = viewport.getX();
        double ym = viewport.getY();
        double wm = viewport.getWidth();
        double hm = viewport.getHeight();
        
        double wv = window.getWidth();
        double hv = window.getHeight();
        
        //traslado al origen
        xm0 = xm0 - xm;
        ym0 = ym0 - ym;
        
        //reescalo a [0, 1]
        xm0 = xm0/wm;
        ym0 = ym0/hm;
        wm0 = wm0/wm;
        hm0 = hm0/hm;
        
        //convierto a ventana
        double xv0 = xm0*wv;
        double wv0 = wm0*wv;
        //convierto ventana - corrijo el eje y
        double yv0 = -1*(ym0*hv - hv);
        double hv0 = hm0*hv;
        
        return new BoundingBox(xv0, yv0 - hv0, wv0,  hv0);
    }
}
