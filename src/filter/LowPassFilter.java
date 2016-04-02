package filter;

public class LowPassFilter extends AbstractFilter {


    private double cutoff = 10;
    private double SAMPLE_RATE = 1000;
    private double recordedSamples[];


    public double[] step(double recordedSamples[], int numSamples) {

        double RC = 1.0/(cutoff*2*3.14);
        double dt = 1.0/SAMPLE_RATE;
        double alpha = RC/(RC + dt);
        double filteredArray[] = new double[numSamples];
        filteredArray[0] = recordedSamples[0];
        for (int i = 1; i<numSamples; i++){
            filteredArray[i] = filteredArray[i-1] + (alpha*(recordedSamples[i] - filteredArray[i-1]));
        }
        recordedSamples = filteredArray;
        return recordedSamples;
    }

    @Override
    public double getCutoff() {
        return cutoff;
    }

    @Override
    public void setCutoff(double cutoff) {

    }
}
