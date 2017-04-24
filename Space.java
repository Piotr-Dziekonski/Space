import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Space extends JPanel implements KeyListener{
    PlayerShip player;
    AlienShip alien;

    public Space(){
        player = new PlayerShip(10,30,Color.red,new Point(640,512));
        alien = new AlienShip(20,60, Color.black,new Point(200,200),0);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.paint(g);
        alien.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.move(e);
        player.rotate(e); // sets player.isRotatingLeft or player.isRotatingRight to true depending on which button is pressed and then player.tick() makes it change its angle (player.angle)
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.brake(e);
        this.repaint();
    }
    public void tick(){ //TODO: we need to clean up those method calls because when there will be more entities there will be mess (already is). We can throw every aliens into an array or smth
        player.tick();
        alien.tick();
        alien.move();
        alien.rotate();
        this.repaint();
    }
}