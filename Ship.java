import java.awt.*;

public class Ship {
    protected int height;
    protected int width;
    protected Color color;

    public Ship(int width, int height, Color color)
    {
        this.height = height;
        this.width= width;
        this.color= color;


    }

    public void paint(Graphics g, Point location)
    {
        g.setColor(color);
        Point point2 = new Point(location.x+width,location.y);
        Point point3 = new Point(location.x+(width/2),location.y - height);
        g.drawLine(location.x,location.y,point2.x,point2.y);
        g.drawLine(location.x,location.y,point3.x,point3.y);
        g.drawLine(point2.x,point2.y,point3.x,point3.y);

    }

}
