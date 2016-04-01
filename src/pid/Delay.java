package pid;

public interface Delay {
    void step();

    void put(double power);

    double get();

    double peek();

    int size();

    void resize(int size);
}
