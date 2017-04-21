import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    Space space = new Space();




    public Window() {
        super("Space");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(space);
        setVisible(true);




    }

    public static void main(String[] args) {
        new Window();

    }

}
