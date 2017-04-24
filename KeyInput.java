import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(GameObject tmp: handler.object){
            GameObject tmpObject = tmp;
            if(tmpObject.getId() == ObjectId.Player){
                if (key == KeyEvent.VK_UP) {
                    tmpObject.setThrusting(true);
                }
                if (key == KeyEvent.VK_LEFT) {
                    tmpObject.isRotatingLeft = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    tmpObject.isRotatingRight = true;
                }
            }
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(GameObject tmp: handler.object){
            GameObject tmpObject = tmp;
            if(tmpObject.getId() == ObjectId.Player){
                if (key == KeyEvent.VK_UP) {
                    tmpObject.setThrusting(false);
                }
                if (key == KeyEvent.VK_LEFT) {
                    tmpObject.isRotatingLeft = false;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    tmpObject.isRotatingRight = false;
                }
            }
        }
    }

}
