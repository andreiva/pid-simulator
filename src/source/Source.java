package source;


public interface Source extends Runnable {

    public void step();

    public double getValue();

    void add(double value);

    void setIntervall(int intervall);


}
