package ui;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private GraphicsPanel graphicsPanel;


    public MainFrame() throws HeadlessException {

        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.getContentPane().add(this);

        graphicsPanel = new GraphicsPanel();
        this.add(graphicsPanel);
        this.setVisible(true);

        graphicsPanel.run();
    }
}
