package pid;

import java.util.Random;

public class Noise {

    private Random r;
    private int magnitude = 50;

    public Noise() {

        this.r = new Random();

    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public double get() {

         if(r.nextInt(100) < 10) {

             return r.nextDouble() * magnitude - (magnitude / 2);
         }
        else
             return 0;

    }


}
