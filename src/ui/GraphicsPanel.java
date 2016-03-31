package ui;

import controller.Controller;
import pid.PIDController;
import source.Source;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GraphicsPanel extends JPanel implements Runnable {

    private Controller controller;
    private Source source;
    private PIDController pidController;
    private BufferedImage buffer;
    private boolean running = true;


    int intervall = 5;
    int step = 0;

    private int w, h = 0;
    private int X, Y;

    static final int MAX = 100;
    private double que[] = new double[MAX];

    public GraphicsPanel() {
        this.controller = Controller.getInstance();
        source = controller.getSource();
        pidController = controller.getPidController();

       Dimension d = controller.getScreenSize();
        //imension d = this.getPreferredSize();
        w = d.width;
        h = d.height;


        this.setSize(new Dimension(w, h / 2));
        X = this.getWidth() / 2;
        Y = this.getHeight() / 2;
        buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }

    public void paint(Graphics g) {
        Graphics screengc = null;
        screengc = g;
        g = buffer.getGraphics();

        step++;
        if(step > w) {
            g.setColor(Color.black);
            g.fillRect(0, 0, w, h);
            step = 0;
        }


        g.setColor(Color.blue);

        g.drawLine(0, Y, w, Y); // X-axis
        g.drawLine(X, 0, X, h); // Y-axis

        g.setColor(Color.gray);
        g.drawLine(0, Y - (int)pidController.getSetPoint(), w, Y - (int)pidController.getSetPoint());


        g.setColor(Color.red);
        g.drawLine(step-1, Y - (int)source.getOld(), step, Y - (int)source.getValue());

        g.setColor(Color.green);
        g.drawLine(step-1, Y + (int)pidController.getOld(), step, Y + (int)pidController.getValue());


        screengc.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void run() {


        while(running) {
            try {

                que[0] = pidController.getValue() * 0.001;
                for(int i = 1; i < MAX; i++) {
                    que[MAX-i] = que[MAX-i -1];
                }

                source.add(que[MAX-1]);
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
