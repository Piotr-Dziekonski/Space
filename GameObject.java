import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;


public abstract class GameObject {

    protected Point2D.Double location;
    protected double x,y;
    protected ObjectId id;
    protected double velX=0, velY=0;
    protected boolean isThrusting=false;
    protected boolean isRotatingLeft = false;
    protected boolean isRotatingRight = false;
    protected double angle;
    protected double movement_speed;

    public GameObject(Point2D.Double location, ObjectId id){
        this.location = location;
        this.x = location.x;
        this.y = location.y;
        this.id = id;
    }
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public void setThrusting(boolean thrust){
        this.isThrusting = thrust;
    }
    public void setMovementSpeed(double speed){
        this.movement_speed = speed;
    }
    public double getMovementSpeed(){
        return this.movement_speed;
    }
    public double getX(){
        return this.location.getX();
    }
    public double getY(){
        return this.location.getY();
    }
    public void setX(double x){
        this.x = x;
        this.location.setLocation(x, this.location.getY());
    }
    public void setY(double y){
        this.y = y;
        this.location.setLocation(this.location.getX(), y);
    }

    public double getVelX(){
        return this.velX;
    }
    public double getVelY(){
        return this.velY;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY(double velY){
        this.velY = velY;
    }
    public void setAngle(double angle){
        this.angle = angle;
    }
    public double getAngle(){
        return this.angle;
    }

    public ObjectId getId(){
        return id;
    }
    public double getRelativeX(){
        return 0;
    }
    public double getRelativeY(){
        return 0;
    }
}
