package ui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private JPanel controlPanel = new JPanel();
    private GraphicsPanel graphicsPanel;
    private MyPIDControlPanel pidControlPanel;
    private HeatSourceControlPanel heatSourceControlPanel;


    public MainFrame() throws HeadlessException {
        super("PID Simulator");

        this.setSize(Controller.getInstance().getScreenSize());
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();

        this.setVisible(true);
        graphicsPanel.run();
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
