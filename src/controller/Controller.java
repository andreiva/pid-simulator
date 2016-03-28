package controller;

import pid.MyPID;
import pid.PIDController;
import source.HeatSource;
import source.Source;
import ui.MainFrame;

import java.awt.*;


public class Controller {

    private MainFrame mainFrame;
    private Dimension screenSize;
    private static Controller instance = null;
    private Source source;
    private PIDController pidController;
    private Runner runner;


    protected Controller() {

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        pidController = new MyPID();
        source = new HeatSource();
        runner = new Runner("Main runner", source, pidController);


       // runner.start();

    }

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }
}
