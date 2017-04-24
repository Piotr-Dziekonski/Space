import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Ship{
    private int height;
    private int width;
    private Color color;
    private Point2D location;

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
        double xPoly[] = {this.location.getX(), this.location.getX() + width, this.location.getX() + (width/2)};
        double yPoly[] = {this.location.getY(), this.location.getY(), this.location.getY() - height};
        double xsum = (this.location.getX() + this.location.getX() + width + this.location.getX() + (width/2))/3;
        double ysum = (this.location.getY() + this.location.getY() + this.location.getY() - height)/3;
        Path2D path = new Path2D.Double();

        path.moveTo(xPoly[0], yPoly[0]);
        for(int i = 1; i < xPoly.length; ++i) {
            path.lineTo(xPoly[i], yPoly[i]);
        }
        path.closePath();

        g2d.rotate(angle,xsum,ysum);
        g2d.setColor(color);
        g2d.draw(path);

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

        location.setLocation(location.getX() + velX, location.getY() + velY);

        //braking
        velX *= 0.9999999999999999;
        velY *= 0.9999999999999999;
        System.out.println("Velocity: " + velX + " " + velY);
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