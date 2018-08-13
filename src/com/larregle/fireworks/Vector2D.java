package com.larregle.fireworks;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void add(Vector2D v2) {
        this.x = this.x + v2.x;
        this.y = this.y + v2.y;
    }

    public void mult(double x, double y) {
        this.x = this.x * x;
        this.y = this.y * y;
    }

    public void mult(Vector2D v2) {
        this.x = this.x * v2.x;
        this.y = this.y * v2.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
