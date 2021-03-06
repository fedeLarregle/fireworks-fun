package com.larregle;

import com.larregle.fireworks.FireworksCanvas;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class App {
    public static void main(String... args) {
        FireworksCanvas fireworksCanvas = new FireworksCanvas();
        JFrame frame = new JFrame();
        frame.setTitle("Fireworks");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(fireworksCanvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fireworksCanvas.requestFocus();
        fireworksCanvas.start();
    }
}
