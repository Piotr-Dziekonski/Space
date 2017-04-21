import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Space extends JPanel implements KeyListener{
    Ship player;

    public Space(){
        player = new Ship(20,40,Color.red,new Point(300,300));
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.move(e);
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}