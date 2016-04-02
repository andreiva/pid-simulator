package pid;

import controller.Controller;
import filter.Filter;
import filter.LowPassFilter;

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

    private double kp = 15;
    private double ki = 0.2;
    private double kd = 0.1;

    private int iTime = 20;
    private int step = 0;

    private List<Double> samples =  new ArrayList<Double>();
    private List<Double> samplesUnfiltered =  new ArrayList<Double>();
    private Filter filter;
    private boolean enableFilter = true;

    public MyPID() {

        for(int i = 0; i< iTime; i++) {
            samples.add(new Double(1));
            samplesUnfiltered.add(new Double(1));
        }
        filter = new LowPassFilter();

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
        samplesUnfiltered.set(step, error);

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

    @Override
    public List<Double> getSamples() {
        return samples;
    }

    public void step() {

//        measuredValue = Controller.getInstance().getSource().getValue();
        measuredValue = Controller.getInstance().getSystemDelay().peek();
        Controller.getInstance().getSystemDelay().step();

        old = currentValue;

        error = getError();
        advanceBuffer();

        if(enableFilter) {
            Double[] asd = samples.toArray(new Double[samples.size()]);
            double[] qwe = new double[samples.size()];

            for(int i = 0; i< samples.size(); i++) {
                qwe[i] = asd[i].doubleValue();
            }
            qwe = filter.step(qwe, samples.size());

            for(int i = 0; i< samples.size(); i++) {
                samples.set(i, qwe[i]);
            }
        }

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

    @Override
    public void setKd(double kd) {
        this.kd = kd;
    }

    @Override
    public void setKi(double ki) {
        this.ki = ki;
    }

    @Override
    public void setKp(double kp) {
        this.kp = kp;
    }

    @Override
    public double getKp() {
        return kp;
    }

    @Override
    public double getKi() {
        return ki;
    }

    @Override
    public double getKd() {
        return kd;
    }

    @Override
    public int getIntervall() {
        return intervall;
    }

    @Override
    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    @Override
    public int getiTime() {
        return iTime;
    }

    @Override
    public void setiTime(int iTime) {
        this.iTime = iTime;
    }

    @Override
    public List<Double> getSamplesUnfiltered() {
        return samplesUnfiltered;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public boolean isEnableFilter() {
        return enableFilter;
    }

    @Override
    public void setEnableFilter(boolean enableFilter) {
        this.enableFilter = enableFilter;
    }
}
