package com.larregle.fireworks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FireworksCanvas extends Canvas {

    public static final int WIDTH = 920;
    public static final int HEIGHT = 650;

    private final Random random;

    private List<Fireworks> fireworks;
    private BufferStrategy bufferStrategy;
    private boolean isRunning;

    public FireworksCanvas() {
        fireworks = new ArrayList<>();
        fireworks.add(new Fireworks());
        random = new Random();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void start() {
        if (!isRunning) {
            createBufferStrategy(3);
            bufferStrategy = getBufferStrategy();
            isRunning = true;
            new Thread(new GameLoop()).start();
        }
    }

    public class GameLoop implements Runnable {

        private static final int FPS = 90;

        public GameLoop() {
        }

        @Override
        public void run() {
            long startTime;
            long waitTime;
            long URTimeMillis;
            long targetTime = 1_000 / FPS; // Time that should take per frame

            /* GAME LOOP */
            while(isRunning) {

                startTime = System.nanoTime();
                update();
                render();
                URTimeMillis = (System.nanoTime() - startTime) / 1_000_000;
                waitTime = targetTime - URTimeMillis; // Checking if took longer than expected

                if(waitTime > 0) {
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

            }
        }

        public void update() {
            if (random.nextDouble() < 0.1) {
                fireworks.add(new Fireworks());
            }
            fireworks.forEach(Fireworks::update);
        }

        public void render() {
            Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
            graphics.setBackground(Color.BLACK);
            graphics.clearRect(0, 0, WIDTH, HEIGHT);
            fireworks.forEach(f -> f.draw(graphics));
            graphics.dispose();
            bufferStrategy.show();
        }
    }
}
