package ui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GraphicsPanel extends JPanel implements Runnable {

    private Controller controller;
    private BufferedImage buffer;
    private boolean running = true;

    int intervall = 1;

    private int w, h = 0;
    private   int gap = 3;



    public GraphicsPanel() {
        this.controller = Controller.getInstance();

        Dimension d = controller.getScreenSize();
        w = d.width;
        h = d.height;

        this.setSize(new Dimension(w, h / 2));
        buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }

    public void paint(Graphics g) {
        Graphics screengc = null;
        screengc = g;

        g = buffer.getGraphics();

        g.setColor(Color.blue);
        g.fillRect(0, 0, w, h);

        g.setColor(Color.red);
        for (int i = 0; i < w; i += gap)
            g.drawLine(i, 0, w - i, h);
        for (int i = 0; i < h; i += gap)
            g.drawLine(0, i, w, h - i);
        screengc.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void run() {

        while(running) {
            try {

                this.repaint();
                Thread.sleep(intervall);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }
}
