package ui;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class MainFrame extends JFrame {

    private Controller controller;
    private JPanel controlPanel = new JPanel();
    private GraphicsPanel graphicsPanel;
    private MyPIDControlPanel pidControlPanel;
    private HeatSourceControlPanel heatSourceControlPanel;
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
       // this.setLocationRelativeTo(null);
        this.setVisible(true);
        controller.getRunner().startStuff();
         new Thread(graphicsPanel).start();
    }

    private void init() {

        Dimension d = new Dimension(Controller.getInstance().getScreenSize().width,
                Controller.getInstance().getScreenSize().height / 2);

        GridLayout gridLayout = new GridLayout(2, 0);
        frame.getContentPane().setLayout(gridLayout);

        //BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        graphicsPanel = new GraphicsPanel();
        graphicsPanel.setSize(d);

//        GridLayout gridLayout = new GridLayout(1, 2);
//        controlPanel.setLayout(gridLayout);
//        this.add(controlPanel);

        JPanel dummy = new JPanel();
        dummy.setSize(d);

        pidControlPanel = new MyPIDControlPanel();
        pidControlPanel.setPreferredSize(d);
        heatSourceControlPanel = new HeatSourceControlPanel();
        heatSourceControlPanel.setPreferredSize(d);


        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
       //tabbedPane.setPreferredSize(d);


        tabbedPane.add("PID", pidControlPanel);
        tabbedPane.add("Heat source", heatSourceControlPanel);

        pidControlPanel.setPreferredSize(d);
        tabbedPane.setPreferredSize(d);

        dummy.add(tabbedPane);
        this.add(dummy);
        this.add(graphicsPanel);

//
//        int maxW = 0;
//        int maxH = 0;
//
//        final Dimension originalTabsDim = tabbedPane.getPreferredSize();
//
//        tabbedPane.addChangeListener(new ChangeListener() {
//
//            @Override
//            public void stateChanged(ChangeEvent e) {
//
//                Component p = ((JTabbedPane) e.getSource()).getSelectedComponent();
//                Dimension panelDim = p.getPreferredSize();
//
//                Dimension nd = new Dimension(
//                        originalTabsDim.width - (maxW - panelDim.width),
//                        originalTabsDim.height - (maxH - panelDim.height));
//
//                tabbedPane.setPreferredSize(nd);
//
//                frame.pack();
//            }
//
//        });


    }
}
