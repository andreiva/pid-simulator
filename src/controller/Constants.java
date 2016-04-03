package controller;

import java.awt.*;

public class Constants {

    public static final String version = "0.1";

    public static final int POWER_OUTPUT_DELAY = 2;
    public static final int SYSTEM_OUTPUT_DELAY = 2;
    public static final int PID_INTERVALL = 50;

    public static final Color POWER_COLOR = Color.green;
    public static final Color TEMPERATURE_COLOR = Color.red;
    public static final Color SETPOINT_COLOR = Color.yellow;
    public static final Color AXIS_COLOR = Color.blue;


    public static final String SYSTEM_DELAY = "Time that takes change of output to reach sensor. " +
            "Imagine heating a long piece of wire from one end and measuring the temperature from the other end.";


}
