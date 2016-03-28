package controller;

import pid.PIDController;
import source.Source;

public class Runner extends Thread {

    private Source source;
    private PIDController pidController;

    public Runner(String name, Source source, PIDController pidController) {
        super(name);
        this.source = source;


        this.pidController = pidController;
    }


    public void setSourceIntervall(int intervall) {

        source.setIntervall(intervall);
    }

    public void setPIDIntervall(int intervall) {

        pidController.setIntervall(intervall);
    }

    public void startStuff() {
        source.run();
    }
}
