
PID Simulator

The purpose of this program is to demonstrate how PID controllers work. You can easily change PID values in user interface and see what effect they have.

This program does not model the real world. Values are not volts, watts or degrees, they are just numbers.
The closest real world example is a house that is getting hot in the summer, RED line is temperature. GREEN line is air conditioning, which is pumping cool air and trying to keep the temperature at Set point the YELLOW line.

Output delay
Simulates delay between PID and power output. Implemented as simple queue.

System delay
Simulates heat transfer https://en.wikipedia.org/wiki/Heat_transfer
Time that takes change of output to reach sensor. Imagine heating a long piece of wire from one end and measuring the temperature from the other end.
In case of air conditioning, it takes time for cool air to mix, and the sensor to register this change.

Noise
Adds random noise to the measuring sensor.

Low pass filter
Not working yet.

How to tune
Default values are intentionally mistuned. Basic principle of tuning is:
 1. set I and D to zero
 2. increase P in steps
 3. click on +25 button to "add some heat"
 4. if power output starts oscillating (going up and down) decrease P a little bit

 5. repeat same process for I
 6. repeat same process for D