import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Space extends JPanel {
    Ship player;
    Point pt = new Point(300,300);

    public Space(){
        player = new Ship(100,200,Color.red);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        player.paint(g,pt);
    }

}
