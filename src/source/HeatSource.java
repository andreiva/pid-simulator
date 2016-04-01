package source;


import pid.Noise;

public class HeatSource implements Source {

    private boolean running = true;
    private int intervall =50;
    private double currentValue = 20;
    private double old = 10;
    private double delta = 0.5;
    private Noise noise;

    public HeatSource() {
        noise = new Noise();
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    @Override
    public void step() {
        old = currentValue;
        currentValue += delta;
    }

    @Override
    public double getValue() {

        if(noise.isEnabled())
            return currentValue + noise.get();
        else
            return currentValue;
    }

    @Override
    public double getOld() {
        return old;
    }

    @Override
    public void add(double value) {
        currentValue += value;
    }

    @Override
    public void setIntervall(int intervall) {
        this.intervall = intervall;
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

    @Override
    public Noise getNoise() {
        return noise;
    }
}
