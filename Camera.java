
public class Camera {
    private double x, y;
    private double bounds = 200;

    public Camera(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void tick(GameObject obj){
        //x--;
    }
}
