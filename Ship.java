import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Ship extends GameObject{
    private double height = 30;
    private double width = 10;
    private Color color = Color.red;

    private double angle_modifier;

    public Ship(int width, int height, Color color, Point2D.Double _location)
    {
        super(_location, ObjectId.Player);
        setVelX(0);
        setVelY(0);
        setAngle(0);
        this.location.setLocation(_location);
        setMovementSpeed(10);
        this.height = height;
        this.width= width;
        this.color= color;
        this.angle_modifier = 5;
    }
    @Override
    public void render(Graphics g)
    {
        g.setColor(color);
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
        g2d.draw(shape);                                                                                        ////    and then drawn here.

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(isRotatingLeft)
        {
            setAngle(getAngle()-Math.toRadians(angle_modifier));
            if(getAngle()<=Math.toRadians(-360)){
                setAngle(getAngle()+Math.toRadians(360));
            }
        }
        else if (isRotatingRight){
            setAngle(getAngle()+Math.toRadians(angle_modifier));
            if(getAngle()>Math.toRadians(-360)){
                setAngle(getAngle()-Math.toRadians(360));
            }
        }
        if(isThrusting) {
            setVelY(Math.cos(getAngle()) * getMovementSpeed()*(-1));
            setVelX(Math.sin(getAngle()) * getMovementSpeed());
        }
        else {//braking
            setVelX(getVelX()*0.97);
            setVelY(getVelY()*0.97);
        }

        location.setLocation(location.getX() + getVelX(), location.getY() + getVelY());

        //System.out.println("x: " + getX() + " y: " + getY());
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}