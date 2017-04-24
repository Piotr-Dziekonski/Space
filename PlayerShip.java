import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by pitu9 on 24.04.2017.
 */
public class PlayerShip extends Ship {
    public PlayerShip(int width, int height, Color color, Point _location){
        super(width, height, color, _location);
    }

    public void brake(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            this.isThrusting = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            this.velY = 0;   //TODO
        }
        if (key == KeyEvent.VK_LEFT) {
            this.isRotatingLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            this.isRotatingRight = false;
        }
    }

    public void move(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            this.isThrusting = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            this.velY = 1;   //TODO Braking or whatever not sure how's that supposed to work, up to you
        }
    }

    public void rotate(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            this.isRotatingLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            this.isRotatingRight = true;
        }

    }

    @Override
    void rotate() {

    }

    @Override
    void brake() {

    }

    @Override
    void move() {

    }


}
