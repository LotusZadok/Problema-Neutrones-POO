package neutrons2;

import java.util.List;

public class Calculator {
    private static final double EPSILON = 1e-10; // Small value to replace zero

    public double calculateSumX(List<OrderedPair> elements) {
        return elements.stream()
                .mapToDouble(e -> e.getX())
                .sum();
    }

    public double calculateSumY(List<OrderedPair> elements) {
        return elements.stream()
                .mapToDouble(e -> e.getY())
                .sum();
    }

    public double calculateSumXSquare(List<OrderedPair> elements) {
        return elements.stream()
                .mapToDouble(e -> e.getX() * e.getX())
                .sum();
    }

    public double calculateSumXY(List<OrderedPair> elements) {
        return elements.stream()
                .mapToDouble(e -> e.getX() * e.getY())
                .sum();
    }

    public double[] calculateParameters(int numElements, double sumX, double sumY, double sumXSquare, double sumXY) {
        double n = numElements;
        double denominator = n * sumXSquare - sumX * sumX;

        // Print intermediate values for debugging
        System.out.println("Number of elements (n): " + n);
        System.out.println("Sum of X: " + sumX);
        System.out.println("Sum of Y: " + sumY);
        System.out.println("Sum of (X^2): " + sumXSquare);
        System.out.println("Sum of (X * Y): " + sumXY);
        System.out.println("Denominator: " + denominator);

        if (denominator == 0) {
            throw new ArithmeticException("Denominator is zero. Check your input data for issues.");
        }

        double a = (sumY * sumXSquare - sumX * sumXY) / denominator;
        double b = (n * sumXY - sumX * sumY) / denominator;
        return new double[]{a, b};
    }
}
