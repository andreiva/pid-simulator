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

    private Image image;
    private Image dbImage = null;
    private Graphics dbg;
    private Dimension screenSize;

    public GraphicsPanel() {
        this.controller = Controller.getInstance();
        source = controller.getSource();
        pidController = controller.getPidController();

        screenSize = controller.getScreenSize();
        w = screenSize.width;
        h = screenSize.height;

        this.setSize(new Dimension(w, h / 3 * 2));
        X = this.getWidth() / 2;
        Y = this.getHeight() / 2;
        buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        if (dbImage != null)
            g.drawImage(dbImage, 0, 0, null);
    }

    private void paintScreen()
    {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null))
                g.drawImage(dbImage, 0, 0, null);
            // Sync the display on some systems.
            // (on Linux, this fixes event queue problems)
            Toolkit.getDefaultToolkit().sync();

            g.dispose();
        }
        catch (Exception e)
        { System.out.println("Graphics context error: " + e);  }
    }

    private void render() {

        // draw the current frame to an image buffer
        if (dbImage == null) {
            dbImage = createImage((int) screenSize.getWidth(), (int) screenSize.getHeight());
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else
                dbg = dbImage.getGraphics();
        }

        Graphics2D g2 = (Graphics2D) dbg;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


        if(step > w || step == 0) {
            step = 0;

            // clear the background
            dbg.setColor(Color.black);
            dbg.fillRect(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

            g2.setColor(Constants.AXIS_COLOR);
            g2.drawLine(0, Y, w, Y); // X-axis
            g2.drawLine(X, 0, X, h); // Y-axis

            for(int i = 1; i < 30; i++) {
                g2.drawLine(X-5, Y-i*20, X+5, Y-i*20);
                g2.drawString(i*20+ "", X+7, Y-i*20+5);

                g2.drawLine(X-5, Y+i*20, X+5, Y+i*20);
                g2.drawString("-"+ i*20, X-50, Y+i*20+5);
            }

            // Set point
            g2.setColor(Constants.SETPOINT_COLOR);
            g2.drawLine(0, Y - (int)pidController.getSetPoint(), w, Y - (int)pidController.getSetPoint());
        }

        step++;

        // temperature
        g2.setColor(Constants.TEMPERATURE_COLOR);
        g2.drawLine(step-1, Y - (int)source.getOld(), step, Y - (int)source.getValue());

        // power
        g2.setColor(Constants.POWER_COLOR);
        g2.drawLine(step-1, Y + (int)pidController.getOld(), step, Y + (int)pidController.getValue());


//        g2.setColor(Color.black);
//        g2.fillRect(0, 0, 500, 180);
//
//        g2.setColor(Color.cyan);
//        for (int i = 1; i<pidController.getSamples().size(); i++) {
//            g2.drawLine(10 + i*3, 50, 10 + i*3, 50 + (int)pidController.getSamples().get(i).intValue() * 10);
//        }
//
//        g2.setColor(Color.blue);
//        for (int i = 1; i<pidController.getSamples().size(); i++) {
//            g2.drawLine(150 + i*3, 50, 150 + i*3, 50 + (int)pidController.getSamplesUnfiltered().get(i).intValue() * 10);
//        }


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

                //this.repaint();
                render(); // render to a buffer
                paintScreen();

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
