import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by pitu9 on 24.04.2017.
 */
public class AlienShip extends Ship{
    AlienShip(int width, int height, Color color, Point _location, double movement_speed){
        super(width, height, color, _location,movement_speed);
    }

    @Override
    void rotate(KeyEvent e) {

    }

    @Override
    void brake(KeyEvent e) {

    }

    @Override
    void move(KeyEvent e) {

    }

    @Override
    void rotate() {
        this.isRotatingLeft = true;
    }

    @Override
    void brake() {

    }

    @Override
    void move() {
        this.isThrusting = true;
    }
}
