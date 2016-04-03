package ui;

import controller.Constants;
import layout.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public class Help extends JPanel {

    public Help() {

        this.setLayout(new FlowLayout(0, 0, FlowLayout.LEADING));

        JPanel panel = new JPanel(new SpringLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Explanation"));

        JPanel power = new JPanel();
        power.setSize(new Dimension(20, 20));
        power.setBackground(Constants.POWER_COLOR);

        panel.add(power);
        panel.add(new JLabel("Power output"));

        JPanel temp = new JPanel();
        temp.setSize(new Dimension(20, 20));
        temp.setBackground(Constants.TEMPERATURE_COLOR);

        panel.add(temp);
        panel.add(new JLabel("Temperature"));

        JPanel setpoint = new JPanel();
        setpoint.setSize(new Dimension(20, 20));
        setpoint.setBackground(Constants.SETPOINT_COLOR);

        panel.add(setpoint);
        panel.add(new JLabel("Set point"));

        this.add(panel);

        SpringUtilities.makeCompactGrid(panel,
                3, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad
    }
}
