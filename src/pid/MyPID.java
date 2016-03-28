package pid;

import java.util.ArrayList;
import java.util.List;

public class MyPID implements PIDController {

    private int intervall = 5;

    private double currentValue = 60;
    private double setValue = 50;
    private double error = 10;
    private double integratedValue = 0;
    private double derivatedValue = 0;

    private double kp = 1;
    private double kd = 1;
    private double ki = 1;
    private int iTime = 5;

    private List<Double> samples;

    // maybe later
    // private Queue<Double> fifo = new LinkedList<Double>();


    public MyPID() {

        samples = new ArrayList<Double>();

    }

    @Override
    public double integrate() {
        integratedValue = 0;

        for(int i = 0; i<iTime; i++) {
            integratedValue += samples.get(i).doubleValue();
        }

        return integratedValue;
    }

    public double derivate() {
        derivatedValue = currentValue / samples.get(iTime - 1).doubleValue();

        return derivatedValue;
    }

    public double getError() {
        double temp = (setValue - currentValue);
        return temp;
    }

    public double calculate() {

        double output = kp * (error) + ki * (integratedValue) + kd * (derivatedValue);

        return output;
    }

    public double step(double value) {

        currentValue = value;

        // samples = filter.filter();
        integratedValue = integrate();
        derivatedValue = derivate();
        error = getError();
        double output = calculate();

        return output;
    }


    @Override
    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }
}
