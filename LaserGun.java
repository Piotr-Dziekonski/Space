import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class LaserGun extends Weapon {


    public LaserGun(int damage, double projectile_speed, int fire_interval) {
        super(WeaponId.LaserGun, damage, projectile_speed, fire_interval);
    }

    @Override
    public void tick(LinkedList<Weapon> object) {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Projectile shoot(double angle, double rotateAnchorX, double rotateAnchorY, Point2D.Double location) {
        return new Projectile(ProjectileId.Bullet, rotateAnchorX, rotateAnchorY, this.getProjectile_speed(), angle, location);
        //System.out.println("pew");

    }
}
