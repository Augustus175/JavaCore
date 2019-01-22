package com.zzb.javacore.chap14.bounce;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HIGHT = 350;

    private List<Ball> balls = new ArrayList<>();

    public void add(Ball ball) {
        balls.add(ball);
    }

    public void painComponent(Graphics graphics) {
        super.printComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        for (Ball b :
                balls) {
            g2.fill(b.getShap());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HIGHT);
    }
}
