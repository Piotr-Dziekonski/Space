import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Projectile {
    private ProjectileId projectileId;
    private double projectile_speed;
    private double angle;
    private Point2D.Double location;
    private double velX, velY;


    public Projectile(ProjectileId projectileId, double projectile_speed, double angle, Point2D.Double location) {
        this.angle = angle;
        this.projectileId = projectileId;
        this.projectile_speed = projectile_speed;
        this.location = location;

    }
    public void render(Graphics g){
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;

        Path2D path = new Path2D.Double();

        path.moveTo(location.getX(), location.getY());
        for(int i = 1; i < 2; ++i) {
            path.lineTo(location.getX() + 10, location.getY() + 10);
        }
        path.closePath();

        double rotateAnchorX = (this.location.getX() + this.location.getX() + 10) /2;
        double rotateAnchorY = (this.location.getY() + this.location.getY() + 10) /2;
        AffineTransform at = AffineTransform.getRotateInstance(this.angle + Math.toRadians(45), rotateAnchorX, rotateAnchorY);       ////    This line creates a rotation which is then used in creating
        Shape shape = path.createTransformedShape(at);                                                          ////    an already rotated actual shape in this line
        g2d.draw(shape);

    }
    public void tick(){
        this.velY = this.velY + Math.cos(angle) * this.projectile_speed * (-1);
        this.velX = this.velX + Math.sin(angle) * this.projectile_speed;
        location.setLocation(location.getX() + velX, location.getY() + velY);
    }

    public Point2D.Double getLocation() {
        return location;
    }
}
