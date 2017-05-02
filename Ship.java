import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Vector;

public class Ship extends GameObject{
    private double height = 30;
    private double width = 10;
    private Color color = Color.red;
    private int firingInterval = 1000;
    private long lastFire;
    private double rotateAnchorX;
    private double rotateAnchorY;

    private double accel_modifier;
    private double angle_modifier;
    private Weapon weapon;
    private Vector<Projectile> projectiles = new Vector<>();


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
        this.accel_modifier = 0.02;
        weapon = new LaserGun(20, 20, 200);
    }
    @Override
    public void render(Graphics g)
    {
        if(this.location.getX() < 0) {this.location.x = 0; velX = 0;}                                                   ///
        if(this.location.getY() < 15) {this.location.y = 15; velY = 0;}                                                 /// All this is temporary. It's here just to prevent ship
        if(this.location.getX() > Game.width) {this.location.x = Game.width; velX = 0;}                                 /// flying off the screen.
        if(this.location.getY() > Game.height + 15) {this.location.y = Game.height + 15; velY = 0;}                     ///

        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        double xPoly[] = {this.location.getX(), this.location.getX() + width, this.location.getX() + (width/2)};
        double yPoly[] = {this.location.getY(), this.location.getY(), this.location.getY() - height};
        Path2D path = new Path2D.Double();

        path.moveTo(xPoly[0], yPoly[0]);
        for(int i = 1; i < xPoly.length; ++i) {
            path.lineTo(xPoly[i], yPoly[i]);
        }
        path.closePath();

        this.rotateAnchorX = (this.location.getX() + this.location.getX() + width + this.location.getX() + (width/2))/3;
        this.rotateAnchorY = (this.location.getY() + this.location.getY() + this.location.getY() - height)/3;

        AffineTransform at = AffineTransform.getRotateInstance(this.angle, this.rotateAnchorX, this.rotateAnchorY);       ////    This line creates a rotation which is then used in creating
        Shape shape = path.createTransformedShape(at);                                                          ////    an already rotated actual shape in this line
        g2d.draw(shape);                                                                                        ////    and then drawn here.

        for (int x = projectiles.size() - 1; x >= 0; x--)   //TODO: This needs to somehow be in the handler class I think
        {
            Projectile projectile = projectiles.elementAt(x);
            projectile.render(g);
            if((projectile.getProjectileBounds2D().getX() < 0 || projectile.getProjectileBounds2D().getY() < 0) || (projectile.getProjectileBounds2D().getX() > Game.width || projectile.getProjectileBounds2D().getY() > Game.height)){
                projectiles.remove(projectile); /// removes the projectile if it went out of screen
            }
        }


    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(isFiring){
            if (!(System.currentTimeMillis() - lastFire < weapon.getFire_interval())) {
                lastFire = System.currentTimeMillis();
                projectiles.add(weapon.shoot(getAngle(), rotateAnchorX, rotateAnchorY, new Point2D.Double(this.getX()+ (width / 2), this.getY() - height)));

            }
        }
        if(isRotatingLeft) {
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
            setVelY(getVelY() + Math.cos(getAngle()) * getMovementSpeed()*(-1) * accel_modifier);
            setVelX(getVelX() + Math.sin(getAngle()) * getMovementSpeed() * accel_modifier);
        }
        else {//braking
            setVelX(getVelX()*0.97);
            setVelY(getVelY()*0.97);
        }

        location.setLocation(location.getX() + getVelX(), location.getY() + getVelY());

        for (Projectile projectile : projectiles) //TODO: This needs to somehow be in the handler class I think
        {
            projectile.tick();
        }

        //System.out.println("x: " + getX() + " y: " + getY());
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}