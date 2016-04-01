package pid;

import controller.Controller;

import java.util.LinkedList;

/**
 * Simple Output delay
 *
 * Stores values in queue, first in last out
 *
 * Real life example...not sure
 *
 */
public class SimpleDelay implements Delay {

    private LinkedList<Double> buffer = new LinkedList<Double>();

    public SimpleDelay() {
        buffer.add(new Double(0));
    }

    @Override
    public void step() {

    }

    @Override
    public void put(double power) {
        buffer.addFirst(power);
    }

    @Override
    public double get() {
        return buffer.pollLast();
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
