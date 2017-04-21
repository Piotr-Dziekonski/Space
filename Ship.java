import java.awt.*;

public class Ship {
    protected int height;
    protected int width;
    protected Color color;
    protected int acceleration = 0;
    protected int movement_speed = 20;
    protected int accel_modifier = 2;
    protected int deceleration_multiplier = 1;
    Point location;

    public Ship(int pos_x, int pos_y, int width, int height, Color color)
    {

        this.location = new Point(pos_x, pos_y);
        this.height = height;
        this.width= width;
        this.color= color;


    }

    public void paint(Graphics g)
    {
        g.setColor(color);
        Point point2 = new Point(location.x+width,location.y);
        Point point3 = new Point(location.x+(width/2),location.y - height);
        g.drawLine(location.x,location.y,point2.x,point2.y);
        g.drawLine(location.x,location.y,point3.x,point3.y);
        g.drawLine(point2.x,point2.y,point3.x,point3.y);

    }
    public void move(int horizontal, int vertical, int speed){
        movement_speed = speed;
        if(horizontal>0){
            location.x+=speed;
        }
        else if(horizontal<0){
            location.x-=speed;
        }
        else{

        }

    }

}
