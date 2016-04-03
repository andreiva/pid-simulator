package pid;

import java.util.LinkedList;

/**
 * Conduction Output delay
 *
 * Stores values in queue. Mimics thermal conduction
 *
 * Real life example
 * When you switch on ceiling fan, air flow doesn't reach you immediately
 * Heating an iron rod from one end, and measuring temperature from the other
 *
 */
public class ConductionDelay implements Delay {

    private LinkedList<Double> buffer = new LinkedList<Double>();
    private double minLimit = 0.01;
    private double coeff = 0.9;     //  heat transfer coefficient,

    public ConductionDelay() {
        buffer.add(new Double(20));
    }

    @Override
    public void step() {

        LinkedList<Double> transfer = buffer;

        if(buffer.size() < 2)
            return;

        for (int i = 1; i< buffer.size(); i++) {

            if(Math.abs(buffer.get(i-1) - buffer.get(i)) > minLimit) {

                if(buffer.get(i-1) > buffer.get(i)) {
                    double diff = (buffer.get(i-1) - buffer.get(i)) * coeff;
                    transfer.set(i-1, buffer.get(i-1) - diff);
                    transfer.set(i, buffer.get(i) + diff);
                }
                else {
                    double diff = (buffer.get(i) - buffer.get(i-1)) * coeff;
                    transfer.set(i-1, buffer.get(i-1) + diff);
                    transfer.set(i, buffer.get(i) - diff);
                }
            }
        }

        buffer = transfer;
    }

    @Override
    public void put(double power) {
        buffer.set(0, power);
    }

    @Override
    public double get() {
        return buffer.peekLast();
    }

    @Override
    public double peek() {
        return buffer.peekLast();
    }

    @Override
    public int size() {
        return buffer.size();
    }

    @Override
    public void resize(int size) {

        if (size < 1)
            return;

        int oldsize = buffer.size();

        if(size > oldsize) {
            Double last = buffer.getFirst();
            for (int i = 0; i< (size - oldsize); i++) {
                buffer.addFirst(last);
            }
        }
        else {
            for (int i = 0; i< (oldsize - size); i++) {
                buffer.removeFirst();
            }
        }
    }

}
