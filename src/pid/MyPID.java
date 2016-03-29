package pid;

import controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class MyPID implements PIDController {

    private boolean running = true;
    private int intervall = 50;

    private double currentValue = 60;
    private double measuredValue = 60;
    private double old = 60;
    private double setPoint = 10;
    private double error = 10;
    private double integratedValue = 0;
    private double derivatedValue = 0;

    private double kp = 25;
    private double ki = 0.25;
    private double kd = 0.1;

    private int iTime = 20;
    private int step = 0;

    private List<Double> samples =  new ArrayList<Double>();

    // maybe later
    // private Queue<Double> fifo = new LinkedList<Double>();


    public MyPID() {

        for(int i = 0; i< iTime; i++) {
            samples.add(new Double(1));
        }

    }

    @Override
    public double integrate() {
        integratedValue = 0;

        for(int i = 0; i < iTime-1; i++) {
            integratedValue += samples.get(i).doubleValue();
        }

        return integratedValue;
    }

    private void advanceBuffer() {

        samples.set(step, error);

        step++;
        if(step >= iTime)
            step = 0;
    }

    public double derivate() {
        if(step > 1)
            derivatedValue = currentValue / samples.get(step - 1).doubleValue();
        else
            derivatedValue = currentValue / samples.get(iTime-1).doubleValue();

        return derivatedValue;
    }

    public double getError() {
        double temp = (setPoint - measuredValue);
        return temp;
    }

    public double calculate() {

        double output = kp * (error) + ki * (integratedValue) - kd * (derivatedValue);
        return output;
    }

    public void step() {

        measuredValue = Controller.getInstance().getSource().getValue();
        old = currentValue;

        // samples = filter.filter();
        error = getError();
        advanceBuffer();
        integratedValue = integrate();
        derivatedValue = derivate();
        currentValue = calculate();

        if(currentValue > 100)
            currentValue = 100;
        if(currentValue < -100)
            currentValue = -100;
    }


    @Override
    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }

    @Override
    public double getValue() {
        return currentValue;
    }

    @Override
    public double getOld() {
        return old;
    }

    @Override
    public void run() {
        while(running) {
            try {

                Thread.sleep(intervall);
                step();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getSetPoint() {
        return setPoint;
    }
}
