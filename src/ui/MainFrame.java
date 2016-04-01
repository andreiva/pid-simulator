package ui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private Controller controller;
    private GraphicsPanel graphicsPanel;
    private PIDControlPanel pidControlPanel;
    private HeatSourceControlPanel heatSourceControlPanel;
    private SystemControlPanel systemControlPanel;
    private JTabbedPane tabbedPane;

    JFrame frame;

    public MainFrame() throws HeadlessException {
        super("PID Simulator");

        frame = this;
        controller = Controller.getInstance();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();

        this.pack();
        this.setVisible(true);
        controller.getRunner().startStuff();
         new Thread(graphicsPanel).start();
    }

    private void init() {

        Dimension d = new Dimension(Controller.getInstance().getScreenSize().width,
                Controller.getInstance().getScreenSize().height / 2 - 20);

        GridLayout gridLayout = new GridLayout(2, 0);
        frame.getContentPane().setLayout(gridLayout);

        //BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        graphicsPanel = new GraphicsPanel();
        graphicsPanel.setSize(d);

        JPanel dummy = new JPanel();
        dummy.setSize(d);

        pidControlPanel = new PIDControlPanel();
        pidControlPanel.setPreferredSize(d);
        heatSourceControlPanel = new HeatSourceControlPanel();
        heatSourceControlPanel.setPreferredSize(d);
        systemControlPanel = new SystemControlPanel();
        systemControlPanel.setPreferredSize(d);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
       //tabbedPane.setPreferredSize(d);

        tabbedPane.add("PID", pidControlPanel);
        tabbedPane.add("Heat source", heatSourceControlPanel);
        tabbedPane.add("System", systemControlPanel);

        pidControlPanel.setPreferredSize(d);
        tabbedPane.setPreferredSize(d);

        dummy.add(tabbedPane);
        this.add(dummy);
        this.add(graphicsPanel);

    }
}
