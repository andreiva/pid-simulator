package ui;

import controller.Controller;
import layout.SpringUtilities;
import pid.MyPID;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Arc2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;


public class PIDControlPanel extends JPanel {

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

    private Controller controller = Controller.getInstance();
    DecimalFormat df = new DecimalFormat("####0.00");

    public PIDControlPanel() {

        fieldTime.setEnabled(false);
        fieldLoopTime.setEnabled(false);

        this.setLayout(new FlowLayout(0, 0, FlowLayout.LEADING));

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

        SpringUtilities.makeCompactGrid(factors,
                6, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(getFilterPanel());
        panel.add(new SystemControlPanel());

        this.add(factors);
        this.add(panel);
        this.add(new HeatSourceControlPanel());

        Util.addMouseWheelListener(fieldP, "setKp", double.class, 1);
        Util.addMouseWheelListener(fieldI, "setKi", double.class, 0.01);
        Util.addMouseWheelListener(fieldD, "setKd", double.class, 0.01);

        Util.addMouseWheelListener(fieldSetPoint, "setSetPoint", double.class, 1);
        Util.addMouseWheelListener(fieldTime, "setiTime", int.class, 1);
        Util.addMouseWheelListener(fieldLoopTime, "setIntervall", int.class, 1);

    }

    private JPanel getFilterPanel() {

        JPanel panel = new JPanel(new SpringLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Filter"));

        JLabel labelEnable = new JLabel("Low pass filter");
        JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getPidController().setEnableFilter(checkBox.getModel().isSelected());
            }
        });

        JLabel labelCutoff = new JLabel("Cutoff Hz");
        JTextField fieldCutoff = new JTextField(controller.getPidController().getFilter().getCutoff() +"", 4);
        fieldCutoff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d = Double.parseDouble(fieldCutoff.getText());
                controller.getPidController().getFilter().setCutoff(d);
            }
        });
        panel.add(labelEnable);
        panel.add(checkBox);
        panel.add(labelCutoff);
        panel.add(fieldCutoff);

        SpringUtilities.makeCompactGrid(panel,
                2, 2,   // rows, cols
                3, 3,  //initX, initY
                3, 3); //xPad, yPad

        return panel;
    }


}
