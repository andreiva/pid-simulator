package ui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private Controller controller;
    private JPanel controlPanel = new JPanel();
    private GraphicsPanel graphicsPanel;
    private MyPIDControlPanel pidControlPanel;
    private HeatSourceControlPanel heatSourceControlPanel;


    public MainFrame() throws HeadlessException {
        super("PID Simulator");

        controller = Controller.getInstance();

        this.setSize(controller.getScreenSize());
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();

        this.setVisible(true);
        controller.getRunner().startStuff();
        new Thread(graphicsPanel).start();
    }

    private void init() {

        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS);
        graphicsPanel = new GraphicsPanel();
        this.add(graphicsPanel);

        GridLayout gridLayout = new GridLayout(1, 2);
        controlPanel.setLayout(gridLayout);
        this.add(controlPanel);

        pidControlPanel = new MyPIDControlPanel();
        heatSourceControlPanel = new HeatSourceControlPanel();
        controlPanel.add(pidControlPanel);
        controlPanel.add(heatSourceControlPanel);


    }
}
