package com.larregle.fireworks;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fireworks {
    private final List<Particle> particles;
    private final Random random;
    private final Vector2D gravity;

    public Fireworks() {
        random = new Random();
        gravity = new Vector2D(0, 0.2);
        particles = new ArrayList<>();
        particles.add(new Particle(Integer.valueOf(random.nextInt((FireworksCanvas.WIDTH) + 1)).doubleValue(), FireworksCanvas.HEIGHT - Particle.HEIGHT));
    }

    public void update() {
        if (random.nextDouble() < 0.1) {
            particles.add(new Particle(Integer.valueOf(random.nextInt((FireworksCanvas.WIDTH) + 1)).doubleValue(), FireworksCanvas.HEIGHT - Particle.HEIGHT));
        }
        particles.forEach(p -> p.applyForce(gravity));
        particles.forEach(Particle::update);
    }

    public void draw(Graphics2D graphics) {
        particles.forEach(p -> p.draw(graphics));
    }
}
