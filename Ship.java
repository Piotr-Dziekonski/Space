import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

abstract class Ship{
    protected int height;
    protected int width;
    protected Color color;
    protected Point2D.Double location;
    protected double movement_speed;

    protected double angle                      = 0;

    protected double velX                       = 0;
    protected double velY                       = 0;
    protected double acceleration               = 0;
    protected double accel_modifier             = 1;
    protected double deceleration_multiplier    = 1;
    protected double angle_modifier             = 5;
    protected boolean isRotatingLeft            = false;
    protected boolean isRotatingRight           = false;
    protected boolean isThrusting               = false;


    public Ship(int width, int height, Color color, Point _location) {
        this.location = new Point2D.Double(_location.x,_location.y);
        this.movement_speed = 10;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    public Ship(int width, int height, Color color, Point _location, double movement_speed) {
        this.location = new Point2D.Double(_location.x,_location.y);
        this.movement_speed = movement_speed;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public void paint(Graphics g)
    {
        if(this.location.getX() < 0) {this.location.x = 0; velX = 0;}
        if(this.location.getY() < 0) {this.location.y = 0; velY = 0;}

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

        double rotateAnchorX = (this.location.getX() + this.location.getX() + width + this.location.getX() + (width/2))/3;
        double rotateAnchorY = (this.location.getY() + this.location.getY() + this.location.getY() - height)/3;
        AffineTransform at = AffineTransform.getRotateInstance(this.angle, rotateAnchorX, rotateAnchorY);       ////    This line create a rotation which is then used in creating
        Shape shape = path.createTransformedShape(at);                                                          ////    an already rotated actual shape in this line
        g2d.setColor(this.color);
        g2d.draw(shape);                                                                                        ////    and then drawn here.

    }
    public void tick(){

        if(this.isRotatingLeft)
        {
            this.angle -= Math.toRadians(this.angle_modifier);
            if (this.angle <= Math.toRadians(-360)){
                this.angle +=Math.toRadians(360);
            }
        }
        else if (isRotatingRight){
            this.angle += Math.toRadians(angle_modifier);
            if (this.angle > Math.toRadians(360)){
                this.angle-=Math.toRadians(360);
            }
        }
        if(this.isThrusting) {
            this.velY += (Math.cos(this.angle) * this.movement_speed)*(-1) * 0.02;
            this.velX += Math.sin(this.angle) * this.movement_speed * 0.02;
        }
        else {//braking
            this.velX *= 0.97;
            this.velY *= 0.97;
        }



        this.location.setLocation(this.location.getX() + this.velX, this.location.getY() + this.velY);

        //System.out.println("Velocity: " + velX + " " + velY);
        //System.out.println("Angle: " + Math.toDegrees(angle) + " " + angle);

    }

    abstract void rotate(KeyEvent e);   //  Those three methods
    abstract void brake(KeyEvent e);    //  are for the player steered ship
    abstract void move(KeyEvent e);     //
    abstract void rotate();             //  Those three methods
    abstract void brake();              //  are for alien steered ships
    abstract void move();               //
}