import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public abstract class Weapon {

    private WeaponId weaponId;
    private int damage;
    private double projectile_speed;
    private int fire_interval;

    public Weapon(WeaponId weaponId, int damage, double projectile_speed, int fire_interval) {
        this.weaponId = weaponId;
        this.damage = damage;
        this.projectile_speed = projectile_speed;
        this.fire_interval = fire_interval;
    }

    public abstract void tick(LinkedList<Weapon> weapon);
    public abstract void render(Graphics g);
    public abstract Projectile shoot(double angle, Point2D.Double location);
    public WeaponId getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(WeaponId weaponId) {
        this.weaponId = weaponId;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getProjectile_speed() {
        return projectile_speed;
    }

    public void setProjectile_speed(double projectile_speed) {
        this.projectile_speed = projectile_speed;
    }

    public int getFire_interval() {
        return fire_interval;
    }

    public void setFire_interval(int fire_interval) {
        this.fire_interval = fire_interval;
    }
}
