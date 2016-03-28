package pid;


public interface PIDController {

    // TODO add abstract class

    double integrate();

    double derivate();

    double calculate();

    void setIntervall(int intervall);

    double step(double value);

}
