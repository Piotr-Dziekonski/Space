import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Projectile {
    private ProjectileId projectileId;
    private double projectile_speed;
    private double angle;
    private Point2D.Double location;
    private double velX, velY;
    private double rotateAnchorX;
    private double rotateAnchorY;
    private AffineTransform at;
    private Shape shape;


    public Projectile(ProjectileId projectileId, double rotateAnchorX, double rotateAnchorY, double projectile_speed, double angle, Point2D.Double location) {
        this.angle = angle;
        this.projectileId = projectileId;
        this.projectile_speed = projectile_speed;
        this.location = location;
        this.rotateAnchorX = rotateAnchorX;
        this.rotateAnchorY = rotateAnchorY;

    }
    public void render(Graphics g){
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;

        Path2D path = new Path2D.Double();

        path.moveTo(location.getX(), location.getY());
        for(int i = 1; i < 2; ++i) {
            path.lineTo(location.getX(), location.getY() - 10);
        }
        path.closePath();


        at = AffineTransform.getRotateInstance(this.angle, this.rotateAnchorX, this.rotateAnchorY);             ////    This line creates a rotation which is then used in creating
        shape = path.createTransformedShape(at);                                                                ////    an already rotated actual shape in this line

        g2d.draw(shape);
    }
    public void tick(){
        try {
            this.velY = Math.cos(Math.toRadians(Math.atan2(at.getShearY(), at.getScaleY()))) * this.projectile_speed * (-1);
            this.velX = Math.sin(Math.toRadians(Math.atan2(at.getShearY(), at.getScaleY()))) * this.projectile_speed;
            location.setLocation(location.getX() + velX, location.getY() + velY);
        }
        catch (NullPointerException e){
            return;
        }

    }

    public Point2D.Double getLocation() {
        return location;
    }
    public Rectangle2D getProjectileBounds2D(){
        return shape.getBounds2D();
    }
}
