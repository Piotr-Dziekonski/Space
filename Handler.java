import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tmpObject;

    public void tick(){

        for(GameObject tmp:object){
            tmpObject = tmp;
            tmpObject.tick(object);
        }
    }
    public void render(Graphics g){
        for(GameObject tmp:object){
            tmpObject = tmp;
            tmpObject.render(g);
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
