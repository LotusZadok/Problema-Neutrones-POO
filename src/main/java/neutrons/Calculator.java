package neutrons;

import java.util.*;

public class Calculator {
    public double calculateSumLnY(List<Element> elements) {
        return elements.stream()
                .filter(e -> e.getNeutrons() != 0)
                .mapToDouble(e -> Math.log(e.getNeutrons()))
                .sum();
    }

    public double calculateSumLnX(List<Element> elements) {
        return elements.stream()
                .filter(e -> e.getAtomicNumber() != 0)
                .mapToDouble(e -> Math.log(e.getAtomicNumber()))
                .sum();
    }

    public double calculateSumLnXSquare(List<Element> elements) {
        return elements.stream()
                .filter(e -> e.getAtomicNumber() != 0)
                .mapToDouble(e -> Math.log(e.getAtomicNumber()))
                .map(x -> x * x)
                .sum();
    }

    public double calculateSumLnXLnY(List<Element> elements) {
        return elements.stream()
                .filter(e -> e.getAtomicNumber() != 0 && e.getNeutrons() != 0)
                .mapToDouble(e -> Math.log(e.getAtomicNumber()) * Math.log(e.getNeutrons()))
                .sum();
    }

    public double[] calculateParameters(int numElements, double sumLnX, double sumLnY, double sumLnXSquare, double sumLnXLnY) {
        double n = numElements;
        double denominator = n * sumLnXSquare - sumLnX * sumLnX;
        double lnA = (sumLnY * sumLnXSquare - sumLnX * sumLnXLnY) / denominator;
        double b = (n * sumLnXLnY - sumLnX * sumLnY) / denominator;
        double a = Math.exp(lnA);
        return new double[]{a, b};
    }
}
