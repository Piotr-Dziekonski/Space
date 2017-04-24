import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    Handler handler;
    Camera cam;

    private void init(){
        handler = new Handler();
        handler.addObject(new Ship(10,30,Color.red,new Point(400,300)));
        this.addKeyListener(new KeyInput(handler));
    }

    public Game() {
        cam = new Camera(0,0);
        setSize(800, 600);
        setVisible(true);
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(cam.getX(), cam.getY());

        handler.render(g);

        g2d.translate(-cam.getX(), -cam.getY());

        g.dispose();
        bs.show();
    }
    public static void main(String[] args) {
        new Window(800,600,"Space", new Game());

    }
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    private synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){ //gameloop
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }


        }
        stop();

    }

    private void tick() {
        handler.tick();
        for(GameObject x:handler.object){
            if(x.getId() == ObjectId.Player){
                cam.tick(x);
            }
        }
    }
}