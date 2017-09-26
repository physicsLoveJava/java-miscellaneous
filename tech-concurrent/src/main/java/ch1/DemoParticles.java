package ch1;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by LuJian on 2017/9/2.
 */
public class DemoParticles extends Applet {

    private Thread[] threads;
    private ParticleCanvas canvas = new ParticleCanvas(100);

    public DemoParticles() throws HeadlessException {
        super();
    }

    @Override
    public void init() {
        add(canvas);
        setSize(new Dimension(200, 200));
        setVisible(true);
        setBackground(Color.BLUE);
    }

    @Override
    public synchronized void start() {
        if(threads == null) {
            int n = 10;
            Particle[] particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                particles[i] = new Particle();
            }
            canvas.setParticles(particles);
            threads = new Thread[n];
            for (int i = 0; i < n; i++) {
                threads[i] = makeThread(particles[i]);
                threads[i].start();
            }
        }
    }

    private Thread makeThread(final Particle particle) {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        particle.move();
                        canvas.repaint();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return new Thread(runnable);
    }

//    public static void main(String[] args) {
//        DemoParticles pa = new DemoParticles();
//        JFrame frame = new JFrame();
//        frame.add(pa);
//        frame.setLocation(200, 200);
//        frame.setSize(500, 500);
//        frame.add(new sun.plugin.AppletViewer());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        pa.start();
//    }

//    <applet code=DemoParticles width=100 height=200></applet>

}
