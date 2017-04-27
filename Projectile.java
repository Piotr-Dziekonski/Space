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
    private double velX=2, velY=2;


    public Projectile(ProjectileId projectileId, double projectile_speed) {
        this.projectileId = projectileId;
        this.projectile_speed = projectile_speed;
        this.location = new Point2D.Double(50,50);

    }
    public void render(Graphics g){
        if(this.location.getX() < 0) {this.location.x = 0; velX = 0;}                                                   ///
        if(this.location.getY() < 15) {this.location.y = 15; velY = 0;}                                                 /// All this is temporary. It's here just to prevent ship
        if(this.location.getX() > Game.width) {this.location.x = Game.width; velX = 0;}                                 /// flying off the screen.
        if(this.location.getY() > Game.height + 15) {this.location.y = Game.height + 15; velY = 0;}                     ///

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
        AffineTransform at = AffineTransform.getRotateInstance(this.angle, rotateAnchorX, rotateAnchorY);       ////    This line creates a rotation which is then used in creating
        Shape shape = path.createTransformedShape(at);                                                          ////    an already rotated actual shape in this line
        g2d.draw(shape);

    }
    public void tick(){
        location.setLocation(location.getX() + velX, location.getY() + velY);
    }
}
