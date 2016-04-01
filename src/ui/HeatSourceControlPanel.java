package ui;


import controller.Controller;
import layout.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HeatSourceControlPanel extends JPanel {

    private Controller controller = Controller.getInstance();
    private JButton buttonImpulse = new JButton("Impulse");

    public HeatSourceControlPanel() {

//        GridLayout layout = new GridLayout(1, 2);
        BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
        this.setLayout(layout);


        Random r = new Random();

        buttonImpulse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().getSource().add(r.nextDouble() * 10);

            }
        });


        this.add(buttonImpulse);
        this.add(getFilterPanel());

    }



    private JPanel getFilterPanel() {

        JPanel panel = new JPanel(new SpringLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Noise"));

        JLabel labelEnable = new JLabel("Noise");
        JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getSource().getNoise().setEnabled(checkBox.getModel().isSelected());
            }
        });

        JLabel labelMagnitude = new JLabel("Magnitude");
        JTextField fieldMagnitude = new JTextField(controller.getSource().getNoise().getMagnitude() +"", 4);
        fieldMagnitude.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = Integer.parseInt(fieldMagnitude.getText());
                controller.getSource().getNoise().setMagnitude(i);
            }
        });

        JLabel labelProbability = new JLabel("Probability");
        JTextField fieldProbability = new JTextField(controller.getSource().getNoise().getProbability() +"", 4);
        fieldProbability.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = Integer.parseInt(fieldProbability.getText());
                controller.getSource().getNoise().setProbability(i);
            }
        });

        panel.add(labelEnable);
        panel.add(checkBox);
        panel.add(labelMagnitude);
        panel.add(fieldMagnitude);
        panel.add(labelProbability);
        panel.add(fieldProbability);

        SpringUtilities.makeCompactGrid(panel,
                3, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad

        return panel;
    }
}
