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
    private double angle_modifier;
    private boolean isRotatingLeft;
    private boolean isRotatingRight;
    private boolean isThrusting;


    public Ship(int width, int height, Color color, Point location)
    {
        this.isRotatingLeft = false;
        this.isRotatingRight = false;
        this.isThrusting = false;
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
        this.angle_modifier = 5;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        int xPoly[] = {this.location.x, this.location.x + width, this.location.x + (width/2)};
        int yPoly[] = {this.location.y, this.location.y, this.location.y - height};
        int xsum = (this.location.x + this.location.x + width + this.location.x + (width/2))/3;
        int ysum = (this.location.y + this.location.y + this.location.y - height)/3;
        Polygon triangle = new Polygon(xPoly, yPoly, xPoly.length);
        g2d.rotate(angle,xsum,ysum);
        g2d.setColor(color);
        g2d.drawPolygon(triangle);

    }
    public void tick(){

        if(isRotatingLeft)
        {
            angle -= Math.toRadians(angle_modifier);
            if (angle <= Math.toRadians(-360)){
                angle +=Math.toRadians(360);
            }
        }
        else if (isRotatingRight){
            angle += Math.toRadians(angle_modifier);
            if (angle > Math.toRadians(360)){
                angle-=Math.toRadians(360);
            }
        }
        if(isThrusting) {
            velY = (int) (Math.cos(angle) * movement_speed)*(-1);
            velX = (int) (Math.sin(angle) * movement_speed);
        }

        location.x += velX;
        location.y += velY;

        //braking
        velX *= 0.9999999;
        velY *= 0.9999999;
        System.out.println("Angle: " + Math.toDegrees(angle));

    }
    public void move(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isThrusting = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            velY = 1;   //TODO Braking or whatever not sure how's that supposed to work, up to you
        }


    }
    public void rotate(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isRotatingLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isRotatingRight = true;
        }

    }

    public void brake(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isThrusting = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            velY = 0;   //TODO
        }
        if (key == KeyEvent.VK_LEFT) {
            isRotatingLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isRotatingRight = false;
        }
    }
}
