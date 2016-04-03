package ui;


import controller.Controller;
import layout.SpringUtilities;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HeatSourceControlPanel extends JPanel {

    private Controller controller = Controller.getInstance();

    public HeatSourceControlPanel() {

        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);

        JButton buttonAdd25 = new JButton("+25");
        buttonAdd25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getSource().add(25);

            }
        });

       JButton buttonAdd10 = new JButton("+10");
        buttonAdd10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getSource().add(10);

            }
        });

       JButton buttonMinus10 = new JButton("-10");
        buttonMinus10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getSource().add(-10);

            }
        });

       JButton buttonMinus25 = new JButton("-25");
        buttonMinus25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getSource().add(-25);

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(buttonAdd25);
        buttonPanel.add(buttonAdd10);
        buttonPanel.add(buttonMinus10);
        buttonPanel.add(buttonMinus25);

        this.add(getFilterPanel());
        this.add(buttonPanel);

    }

    private JPanel getFilterPanel() {

        JPanel panel = new JPanel(new SpringLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Noise"));

        JLabel labelEnable = new JLabel("Noise");
        final JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getSource().getNoise().setEnabled(checkBox.getModel().isSelected());
            }
        });

        JLabel labelMagnitude = new JLabel("Magnitude");
        final JSlider sliderMagnitude = new JSlider(JSlider.HORIZONTAL, 0, 100, controller.getSource().getNoise().getMagnitude());
        sliderMagnitude.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int i = sliderMagnitude.getValue();
                controller.getSource().getNoise().setMagnitude(i);
            }
        });

        sliderMagnitude.setMajorTickSpacing(10);
//        sliderMagnitude.setMinorTickSpacing(1);
        sliderMagnitude.setPaintTicks(true);
        sliderMagnitude.setPaintLabels(true);

        JLabel labelProbability = new JLabel("Probability");
        final JSlider sliderProbability = new JSlider(JSlider.HORIZONTAL, 0, 100, controller.getSource().getNoise().getProbability());
        sliderProbability.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int i = sliderProbability.getValue();
                controller.getSource().getNoise().setProbability(i);
            }
        });

        sliderProbability.setMajorTickSpacing(10);
//        sliderProbability.setMinorTickSpacing(1);
        sliderProbability.setPaintTicks(true);
        sliderProbability.setPaintLabels(true);


        panel.add(labelEnable);
        panel.add(checkBox);
        panel.add(labelMagnitude);
        panel.add(sliderMagnitude);
        panel.add(labelProbability);
        panel.add(sliderProbability);

        SpringUtilities.makeCompactGrid(panel,
                3, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad

        return panel;
    }
}
