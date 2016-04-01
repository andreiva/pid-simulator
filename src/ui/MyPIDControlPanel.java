package ui;

import controller.Controller;
import layout.SpringUtilities;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class MyPIDControlPanel extends JPanel {

    private JPanel factors;

    private JLabel labelP = new JLabel("P");
    private JLabel labelI = new JLabel("I");
    private JLabel labelD = new JLabel("D");
    private JLabel labelTime = new JLabel("iTime");
    private JLabel labelSetPoint = new JLabel("Set point");
    private JLabel labelLoopTime = new JLabel("Loop time");

    private JTextField fieldP = new JTextField(4);
    private JTextField fieldI = new JTextField(4);
    private JTextField fieldD = new JTextField(4);
    private JTextField fieldTime = new JTextField(4);
    private JTextField fieldSetPoint = new JTextField(4);
    private JTextField fieldLoopTime = new JTextField(4);

    private JSlider slider;

    private Controller controller = Controller.getInstance();

    public MyPIDControlPanel() {

        GridLayout layout = new GridLayout(0, 2);
        this.setLayout(layout);

        fieldP.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    double d = controller.getPidController().getKp();
                    controller.getPidController().setKp(d+1);
                    fieldP.setText((d+1) + "");
                } else {
                    double d = controller.getPidController().getKp();
                    controller.getPidController().setKp(d-1);
                    fieldP.setText((d-1) + "");
                }
            }
        });


        fieldP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d = Double.parseDouble(fieldP.getText());
                controller.getPidController().setKp(d);
            }
        });
        fieldI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d = Double.parseDouble(fieldI.getText());
                controller.getPidController().setKi(d);
            }
        });
        fieldD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d = Double.parseDouble(fieldD.getText());
                controller.getPidController().setKd(d);
            }
        });
        fieldTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int d = Integer.parseInt(fieldTime.getText());
                controller.getPidController().setiTime(d);
            }
        });
        fieldLoopTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int d = Integer.parseInt(fieldLoopTime.getText());
                controller.getPidController().setIntervall(d);
            }
        });
        fieldSetPoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d = Double.parseDouble(fieldSetPoint.getText());
                controller.getPidController().setSetPoint(d);
            }
        });

        fieldP.setText(controller.getPidController().getKp() +"");
        fieldI.setText(controller.getPidController().getKi() +"");
        fieldD.setText(controller.getPidController().getKd() +"");
        fieldTime.setText(controller.getPidController().getiTime() +"");
        fieldLoopTime.setText(controller.getPidController().getIntervall() +"");
        fieldSetPoint.setText(controller.getPidController().getSetPoint() +"");


        fieldP.setHorizontalAlignment(SwingConstants.RIGHT);
        fieldI.setHorizontalAlignment(SwingConstants.RIGHT);
        fieldD.setHorizontalAlignment(SwingConstants.RIGHT);
        fieldTime.setHorizontalAlignment(SwingConstants.RIGHT);
        fieldLoopTime.setHorizontalAlignment(SwingConstants.RIGHT);
        fieldSetPoint.setHorizontalAlignment(SwingConstants.RIGHT);

        factors = new JPanel(new SpringLayout());
        factors.setBorder(BorderFactory.createTitledBorder("PID factors"));

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double d = slider.getValue();
                controller.getPidController().setSetPoint(d);
            }
        });

        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

//        this.add(slider);

        factors.add(labelP);
        factors.add(fieldP);
        factors.add(labelI);
        factors.add(fieldI);
        factors.add(labelD);
        factors.add(fieldD);

        factors.add(labelTime);
        factors.add(fieldTime);
        factors.add(labelLoopTime);
        factors.add(fieldLoopTime);
        factors.add(labelSetPoint);
        factors.add(fieldSetPoint);

        factors.add(new JLabel("Setpoint"));
        factors.add(slider);
//        factors.add(new JPanel());
//        factors.add(new JPanel());



        SpringUtilities.makeCompactGrid(factors,
                7, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad

        this.add(factors);
        this.add(new JPanel());

    }
}
