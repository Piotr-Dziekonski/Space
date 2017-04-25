import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Random;

public class Asteroid extends GameObject{
    private double size = 30;
    private Color color = Color.ORANGE;
    private int direction = (int) Math.toRadians(new Random().nextInt(720) - 359);

    private double angle_modifier;

    public Asteroid(double size, Color color, Point2D.Double location)
    {
        super(location, ObjectId.Asteroid);
        setVelX(0);
        setVelY(0);
        setAngle(0);
        location.setLocation(location);
        setMovementSpeed(1);
        this.size = size;
        this.color= color;
        this.angle_modifier = 3;
        this.isRotatingLeft = true;
        this.isThrusting = true;
    }
    @Override
    public void render(Graphics g)
    {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (this.location.x + this.size * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (this.location.y + this.size * Math.sin(i * 2 * Math.PI / 6)));

        int centerY = (p.ypoints[0] + p.ypoints[3])/2;
        int centerX = (p.xpoints[0] + p.xpoints[3])/2;
        double rotateAnchorX = (centerX);
        double rotateAnchorY = (centerY);


        AffineTransform at = AffineTransform.getRotateInstance(this.angle, rotateAnchorX, rotateAnchorY);       ////    This line create a rotation which is then used in creating
        Shape shape = at.createTransformedShape(p);                                                             ////    an already rotated actual shape in this line
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
            setVelY(Math.cos(this.direction) * getMovementSpeed()*(-1));
            setVelX(Math.sin(this.direction) * getMovementSpeed());
        }

        location.setLocation(location.getX() + getVelX(), location.getY() + getVelY());

        //System.out.println("x: " + getX() + " y: " + getY());
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}