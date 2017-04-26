import java.awt.*;
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
        shoot();
    }

    @Override
    public void shoot() {
        new Projectile(ProjectileId.Bullet, this.getProjectile_speed());
    }
}
