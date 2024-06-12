package neutrons2.classes;

import neutrons2.interfaces.LogCalculatorInterface;

public class LogCalculator implements LogCalculatorInterface {
    public double calculateLog(double value) {
        return Math.log(value);
    }
}
