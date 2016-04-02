package ui;

import controller.Constants;
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

    static final int MAX = Constants.POWER_OUTPUT_DELAY;
    private double que[] = new double[MAX];

    public GraphicsPanel() {
        this.controller = Controller.getInstance();
        source = controller.getSource();
        pidController = controller.getPidController();

        Dimension d = controller.getScreenSize();
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


        g.setColor(Color.white);


        g.drawLine(0, Y, w, Y); // X-axis
        g.drawLine(X, 0, X, h); // Y-axis

        // Set point
        g.setColor(Color.gray);
        g.drawLine(0, Y - (int)pidController.getSetPoint(), w, Y - (int)pidController.getSetPoint());

        // temperature
        g.setColor(Color.red);
        g.drawLine(step-1, Y - (int)source.getOld(), step, Y - (int)source.getValue());

//        g.setColor(Color.cyan);
//        g.drawLine(step-1, Y - (int)source.getOld(), step, Y - (int)controller.getSystemDelay().peek());

        // power
        g.setColor(Color.green);
        g.drawLine(step-1, Y + (int)pidController.getOld(), step, Y + (int)pidController.getValue());

//        if(step % 50 == 0) {
//
//            g.setColor(Color.green);
//            g.drawLine(step-1, Y + (int)pidController.getOld(), step + 50, Y + (int)pidController.getValue());
//
//        }

        g.setColor(Color.black);
        g.fillRect(0, 0, 500, 180);

        g.setColor(Color.cyan);
        for (int i = 1; i<pidController.getSamples().size(); i++) {
            g.drawLine(10 + i*3, 50, 10 + i*3, 50 + (int)pidController.getSamples().get(i).intValue() * 10);
        }

        g.setColor(Color.blue);
        for (int i = 1; i<pidController.getSamples().size(); i++) {
            g.drawLine(150 + i*3, 50, 150 + i*3, 50 + (int)pidController.getSamplesUnfiltered().get(i).intValue() * 10);
        }

        screengc.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void run() {


        while(running) {
            try {

                Double power = pidController.getValue() * 0.001;
                controller.getActuatorDelay().put(power);

                source.add(pidController.getValue() * 0.001);
                source.add(controller.getActuatorDelay().get());
                controller.getSystemDelay().put(source.getValue());

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
