package filter;


public interface Filter {



    double[] step(double recordedSamples[], int numSamples);

    public double getCutoff();

    public void setCutoff(double cutoff);


    }
