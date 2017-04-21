import java.awt.*;
import java.awt.event.*;

public class Ship{
    private int height;
    private int width;
    private Color color;
    private Point location;

    private double angle;

    private int velX, velY;
    private double acceleration;
    private double movement_speed;
    private double accel_modifier;
    private double deceleration_multiplier;



    public Ship(int width, int height, Color color, Point location)
    {
        this.velX = 0;
        this.velY = 0;
        this.angle = 0;
        this.location = location;
        this.acceleration = 0;
        this.movement_speed = 10;
        this.accel_modifier = 0.5;
        this.deceleration_multiplier = 1;
        this.height = height;
        this.width= width;
        this.color= color;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        int xPoly[] = {this.location.x, this.location.x + width, this.location.x + (width/2)};
        int yPoly[] = {this.location.y, this.location.y, this.location.y - height};
        int xsum = (this.location.x + this.location.x + width + this.location.x + (width/2))/3;
        int ysum = (this.location.y + this.location.y + this.location.y - height)/3;
        Polygon triangle = new Polygon(xPoly, yPoly, xPoly.length);
        g2d.rotate(Math.toRadians(angle),xsum,ysum);
        g2d.setColor(color);
        g2d.drawPolygon(triangle);

    }
    public void tick(){
        location.x += velX;
        location.y += velY;
    }
    public void move(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            velY = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            velY = 1;
        }


    }
    public void rotate(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            angle += Math.toRadians(-180);

        }

        if (key == KeyEvent.VK_RIGHT) {
            angle += Math.toRadians(180);
        }
    }

    public void brake(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            velY = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            velY = 0;
        }


    }
}
