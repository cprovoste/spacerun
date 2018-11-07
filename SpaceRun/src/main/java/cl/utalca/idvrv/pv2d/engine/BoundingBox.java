package cl.utalca.idvrv.pv2d.engine;

public class BoundingBox
{
    public double x;
    public double y;
    private double width;
    private double height;

    public BoundingBox(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = Math.abs(width);
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = Math.abs(height);
    }

    @Override
    public String toString()
    {
        return String.format("[%f, %f, %f, %f]", this.x, this.y, this.width, this.height);
    }
    
    

    
    
    
    
    
}
