import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Space extends JPanel implements KeyListener {
    Ship player;
    Point pt = new Point(300,300);

    public Space(){
        player = new Ship(100,200,100,200,Color.red);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        player.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                player.move(-1,0, 20);
                this.repaint();

                System.err.println(player.location.getX());
                break;
            case KeyEvent.VK_RIGHT:
                player.move(1,0, 20);
                this.repaint();

                System.err.println(player.location.getX());
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
