package inpact.ch1;

import java.awt.*;

/**
 * Created by LuJian on 2017/9/2.
 */
public class ParticleCanvas extends Canvas {

    private Particle[] particles;

    public ParticleCanvas(int size) {
        setSize(new Dimension(size, size));
    }

    public synchronized void setParticles(Particle[] particles) {
        if(particles == null) {
            return;
        }
        this.particles = particles;
    }

    public synchronized Particle[] getParticles() {
        return this.particles;
    }

    @Override
    public void paint(Graphics g) {
        Particle[] particles = getParticles();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw(g);
        }
    }
}
