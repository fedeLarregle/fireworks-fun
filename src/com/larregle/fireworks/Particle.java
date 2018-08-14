package com.larregle.fireworks;

import java.awt.*;
import java.util.Random;

public class Particle {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    private boolean root;
    private volatile float lifespan;
    private final Color color;

    public Particle(double x, double y, boolean root, Color color) {
        Random rand = new Random();
        this.root = root;
        this.color = color;
        position = new Vector2D(x, y);
        if (root) {
            velocity = new Vector2D(0, rand.nextInt((15 - 8) + 1) - 15);
        } else {
            velocity = new Vector2D(rand.nextDouble(), rand.nextDouble());
            int vrX = rand.nextInt((40 + 1 - 2)) - 20;
            int vrY = rand.nextInt((40 + 1 - 2)) - 20;
            velocity.mult(vrX, vrY);
        }
        acceleration = new Vector2D(0, 0);
        lifespan = 1F;
    }

    public void update() {
        if (!root) {
            velocity.mult(0.9, 0.9);
            lifespan -= 0.02;
        }
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0, 0);
    }

    public boolean isDone() { return lifespan <= 0;}

    public void draw(Graphics2D graphics) {
        graphics.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, lifespan >= 0 ? lifespan : 0));
        graphics.setColor(color);
        graphics.fillOval(
                Double.valueOf(position.getX()).intValue(),
                Double.valueOf(position.getY()).intValue(),
                WIDTH,
                HEIGHT
        );
    }

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public Vector2D getVelocity() { return velocity; }

    public Vector2D getPosition() { return position; }

    public Color getColor() { return color; }
}
