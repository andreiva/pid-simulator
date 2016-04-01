package ui;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        System.out.println("Starting PID simulator");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Couldn't set look & feel: Nimbus");
        }

        new MainFrame();


    }

}
