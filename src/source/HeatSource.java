package source;


public class HeatSource implements Source {

    private boolean running = true;
    private int intervall = 5;
    private int currentValue = 0;
    private double delta = 1;

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    @Override
    public void step() {
        currentValue += delta;

    }

    @Override
    public double getValue() {
        return currentValue;
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
}
