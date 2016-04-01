package pid;

import java.util.Random;

public class Noise {

    private Random r;
    private int magnitude = 50;
    private int probability = 10;
    private boolean enabled = false;

    public Noise() {

        this.r = new Random();

    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public double get() {

         if(r.nextInt(100) < probability) {

             return r.nextDouble() * magnitude - (magnitude / 2);
         }
        else
             return 0;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
