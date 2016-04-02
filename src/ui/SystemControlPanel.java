package ui;

import controller.Constants;
import controller.Controller;
import layout.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class SystemControlPanel extends JPanel {

    private JPanel factors;

    private JLabel labelPower = new JLabel("Power output");
    private JLabel labelSystem = new JLabel("System");

    private JTextField fieldPower = new JTextField(4);
    private JTextField fieldSystem = new JTextField(4);

    private Controller controller = Controller.getInstance();

    public SystemControlPanel() {

//        GridLayout layout = new GridLayout(0, 2);
//        this.setLayout(layout);
        this.setLayout(new FlowLayout(0, 0, FlowLayout.LEADING));

        fieldPower.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    int i = controller.getActuatorDelay().size();
                    controller.getActuatorDelay().resize(++i);
                    fieldPower.setText(i + "");
                } else {
                    int i = controller.getActuatorDelay().size();
                    controller.getActuatorDelay().resize(--i);
                    fieldPower.setText(i + "");
                }
            }
        });

        fieldSystem.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    int i = controller.getSystemDelay().size();
                    controller.getSystemDelay().resize(++i);
                    fieldSystem.setText(i + "");
                } else {
                    int i = controller.getSystemDelay().size();
                    controller.getSystemDelay().resize(--i);
                    fieldSystem.setText(i + "");
                }
            }
        });

        fieldPower.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = Integer.parseInt(fieldPower.getText());
                controller.getActuatorDelay().resize(i);
            }
        });
        fieldSystem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                int i = Integer.parseInt(fieldPower.getText());
//                controller.getActuatorDelay().resize(i);
            }
        });

        fieldPower.setText(Constants.POWER_OUTPUT_DELAY +"");
        fieldSystem.setText(Constants.SYSTEM_OUTPUT_DELAY +"");


        fieldPower.setHorizontalAlignment(SwingConstants.RIGHT);
        fieldSystem.setHorizontalAlignment(SwingConstants.RIGHT);


        factors = new JPanel(new SpringLayout());
        factors.setBorder(BorderFactory.createTitledBorder("Delays"));


        factors.add(labelPower);
        factors.add(fieldPower);
        factors.add(labelSystem);
        factors.add(fieldSystem);

        SpringUtilities.makeCompactGrid(factors,
                2, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad

        this.add(factors);
        this.add(new JPanel());

    }
}
