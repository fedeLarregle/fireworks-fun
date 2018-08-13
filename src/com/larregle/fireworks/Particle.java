package com.larregle.fireworks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Particle {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    public Particle(double x, double y) {
        Random random = new Random();
        position = new Vector2D(x, y);
        velocity = new Vector2D(0, random.nextInt((15 + 1 - 8)) - 15);
        acceleration = new Vector2D(0, 0);
    }

    public void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0, 0);
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
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
}
