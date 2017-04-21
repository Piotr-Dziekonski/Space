import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Space extends JFrame {
    public static void main(String[] args) {
        new Window();
        Ship player = new Ship(100,200,Color.red);
    }

}
