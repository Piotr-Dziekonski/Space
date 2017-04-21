import javax.swing.*;

public class Game extends JFrame implements Runnable {

    Space space;
    private boolean running = false;
    private Thread thread;

    public Game() {
        super("Space");
        space = new Space();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(space);
        setVisible(true);
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.start();

    }
    private synchronized void start(){
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
        space.tick();
    }
}