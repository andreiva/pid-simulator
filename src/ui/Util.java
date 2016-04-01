package ui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;


public class Util {

    private static DecimalFormat df = new DecimalFormat("####0.00");

    public static void addMouseWheelListener(JTextField field, String name, Class type, double step) {

        try {
            Class<?> c = Class.forName("pid.MyPID");
            Method setter = c.getDeclaredMethod(name, type);

            field.addMouseWheelListener(new MouseWheelListener() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {

                    double d = 0;
                    int i = 0;
                    if(type == double.class) {
                        d = Double.parseDouble(field.getText());
                    }
                    else if(type == int.class) {
                        i = Integer.parseInt(field.getText());
                    }

                    if (e.getWheelRotation() < 0) {
                        try {
                            if(type == double.class) {
                                setter.invoke (Controller.getInstance().getInstance().getPidController(), d + step);
                                field.setText(df.format(d + step));
                            }
                            else if(type == int.class) {
                                setter.invoke (Controller.getInstance().getInstance().getPidController(), i + step);
                                field.setText(df.format(i + step));
                            }
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {
                            if(type == double.class) {
                                setter.invoke (Controller.getInstance().getInstance().getPidController(), d - step);
                                field.setText(df.format(d - step));
                            }
                            else if(type == int.class) {
                                setter.invoke (Controller.getInstance().getInstance().getPidController(), i - step);
                                field.setText(df.format(i - step));
                            }
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
