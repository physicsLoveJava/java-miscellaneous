package inpact.ch1;

import java.awt.*;
import java.util.Random;

/**
 * Created by LuJian on 2017/9/2.
 */
public class Particle {

    private int x;
    private int y;
    private final Random random = new Random();

    public Particle() {
    }

    public void draw(Graphics g) {
        int x1, y1;
        synchronized (this) {
            x1 = x;
            y1 = y;
        }
        g.drawRect(x1, y1, 10, 10);
    }

    public void move() {
        x = random.nextInt(50);
        y = random.nextInt(50);
    }
}
