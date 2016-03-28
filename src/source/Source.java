package source;


public interface Source extends Runnable {

    public void step();

    public double getValue();

    public double getOld();


    void add(double value);

    void setIntervall(int intervall);


}
