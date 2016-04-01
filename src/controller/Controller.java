package controller;

import pid.*;
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
    private Delay actuatorDelay;
    private Delay systemDelay;
    private Runner runner;


    protected Controller() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        DisplayMode dm;
        if(gs.length > 0)
            dm = gs[1].getDisplayMode();
        else
            dm = gs[0].getDisplayMode();
        screenSize = new Dimension(dm.getWidth(), dm.getHeight());

        pidController = new MyPID();
        source = new HeatSource();
        actuatorDelay = new SimpleDelay();
        actuatorDelay.resize(Constants.POWER_OUTPUT_DELAY);
        systemDelay = new ConductionDelay();
        systemDelay.resize(Constants.SYSTEM_OUTPUT_DELAY);

        runner = new Runner("Main runner", source, pidController);

    }

    public Delay getActuatorDelay() {
        return actuatorDelay;
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

    public Source getSource() {
        return source;
    }

    public PIDController getPidController() {
        return pidController;
    }

    public Runner getRunner() {
        return runner;
    }

    public Delay getSystemDelay() {
        return systemDelay;
    }
}
