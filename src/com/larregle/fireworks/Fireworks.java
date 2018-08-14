package com.larregle.fireworks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fireworks {
    private final Particle firework;
    private final List<Particle> particles;
    private final Random random;
    private final Vector2D gravity;
    private volatile boolean exploded;

    public Fireworks() {
        random = new Random();
        gravity = new Vector2D(0, 0.2);
        particles = new ArrayList<>();
        firework = new Particle(
                Integer.valueOf(random.nextInt((FireworksCanvas.WIDTH) + 1)).doubleValue(),
                FireworksCanvas.HEIGHT - Particle.HEIGHT,
                true,
                new Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
        );
    }

    public void update() {
        if (!exploded) {
            firework.update();
            firework.applyForce(gravity);
            if (firework.getVelocity().getY() >= 0) {
                exploded = true;
                explode();
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isDone())
                particles.remove(i);
        }
        particles.forEach(p -> p.applyForce(gravity));
        particles.forEach(Particle::update);
    }

    public boolean isDone() {
        return exploded && particles.size() == 0;
    }

    public void explode() {
        int len = random.nextInt((350 - 20) + 1) + 20;
        for (int i = 0; i < len; i++)
            particles.add(new Particle(firework.getPosition().getX(), firework.getPosition().getY(), false, firework.getColor()));
    }

    public void draw(Graphics2D graphics) {
        if (!exploded) {
            firework.draw(graphics);
        }
        particles.forEach(p -> p.draw(graphics));
    }
}
