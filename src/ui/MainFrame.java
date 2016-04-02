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

        Dimension graph = new Dimension(Controller.getInstance().getScreenSize().width,
                Controller.getInstance().getScreenSize().height / 3 * 2 - 20);
        Dimension settings = new Dimension(Controller.getInstance().getScreenSize().width,
                Controller.getInstance().getScreenSize().height / 3 - 20);

        System.out.println("Dmension "+ graph);
        System.out.println("Dmension "+ settings);

        GridLayout gridLayout = new GridLayout(2, 0);
        frame.getContentPane().setLayout(gridLayout);

        graphicsPanel = new GraphicsPanel();
        graphicsPanel.setPreferredSize(graph);

        pidControlPanel = new PIDControlPanel();
        pidControlPanel.setPreferredSize(settings);
//        heatSourceControlPanel = new HeatSourceControlPanel();
//        heatSourceControlPanel.setPreferredSize(d);
//        systemControlPanel = new SystemControlPanel();
//        systemControlPanel.setPreferredSize(d);

        JScrollPane scrollPane = new JScrollPane(pidControlPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.add(pidControlPanel);
//        scrollPane.add(graphicsPanel);

        this.add(scrollPane);
        this.add(graphicsPanel);
    }
}
