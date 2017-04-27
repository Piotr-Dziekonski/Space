import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<>();
    public LinkedList<Weapon> weapon = new LinkedList<>();
    public LinkedList<Projectile> projectile = new LinkedList<>();

    private GameObject tmpObject;
    private Weapon tmpWeapon;
    private Projectile tmpProjectile;

    public void tick(){

        for(GameObject tmp:object){
            tmpObject = tmp;
            tmpObject.tick(object);

        }
        for(Weapon tmp:weapon){
            tmpWeapon = tmp;
            tmpWeapon.tick(weapon);

        }
        for(Projectile tmp:projectile){
            tmpProjectile = tmp;
            tmpProjectile.tick();

        }
    }
    public void render(Graphics g){
        for(GameObject tmp:object){
            tmpObject = tmp;
            tmpObject.render(g);
        }
        for(Weapon tmp:weapon){
            tmpWeapon = tmp;
            tmpWeapon.render(g);
        }
        for(Projectile tmp:projectile){
            tmpProjectile = tmp;
            tmpProjectile.render(g);
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    public void addWeapon(Weapon weapon){
        this.weapon.add(weapon);
    }
    public void removeWeapon(Weapon weapon){
        this.weapon.remove(weapon);
    }
    public void addProjectile(Projectile projectile){
        this.projectile.add(projectile);
    }
    public void removeProjectile(Projectile projectile){
        this.projectile.remove(projectile);
    }

}
