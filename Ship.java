import java.awt.*;
import java.awt.event.*;

public class Ship{
    private int height;
    private int width;
    private Color color;
    private Point location;

    private double acceleration;
    private double movement_speed;
    private double accel_modifier;
    private double deceleration_multiplier;



    public Ship(int width, int height, Color color, Point location)
    {
        this.location = location;
        this.acceleration = 0;
        this.movement_speed = 20;
        this.accel_modifier = 0.5;
        this.deceleration_multiplier = 1;
        this.height = height;
        this.width= width;
        this.color= color;
    }

    public void paint(Graphics g)
    {
        g.setColor(color);
        Point point2 = new Point(this.location.x+width,this.location.y);
        Point point3 = new Point(this.location.x+(width/2),this.location.y - height);
        g.drawLine(this.location.x,this.location.y,point2.x,point2.y);
        g.drawLine(this.location.x,this.location.y,point3.x,point3.y);
        g.drawLine(point2.x,point2.y,point3.x,point3.y);

    }
    public void move(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            location.x -= 1;

        }

        if (key == KeyEvent.VK_RIGHT) {
            location.x += 1;
        }

        if (key == KeyEvent.VK_UP) {
            location.y -= 1;
        }

        if (key == KeyEvent.VK_DOWN) {
            location.y += 1;
        }

    }

}
