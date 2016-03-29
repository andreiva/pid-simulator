package ui;


import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HeatSourceControlPanel extends JPanel {

    private JButton buttonImpulse = new JButton("Impulse");

    public HeatSourceControlPanel() {

        this.setBackground(Color.BLUE);

        Random r = new Random();

        buttonImpulse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().getSource().add(r.nextDouble() * 10);

            }
        });

        BoxLayout f = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(f);
        this.add(buttonImpulse);

        this.setVisible(true);

    }
}
