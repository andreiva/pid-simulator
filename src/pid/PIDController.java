package pid;


import java.util.List;

public interface PIDController extends Runnable {

    // TODO add abstract class

    double integrate();

    double derivate();

    double calculate();

    void setIntervall(int intervall);

    List<Double> getSamples();

    void step();

    public double getValue();

    public double getOld();

    public double getSetPoint();

    void setKp(double kp);

    void setKi(double ki);

    void setKd(double kd);

    double getKp();

    double getKi();

    double getKd();

    int getIntervall();

    void setSetPoint(double setPoint);

    int getiTime();

    abstract void setiTime(int iTime);

    double getError();

    List<Double> getSamplesUnfiltered();
}