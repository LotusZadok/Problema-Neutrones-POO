package neutrons2.classes;

import neutrons2.interfaces.LogCalculatorInterface;
import neutrons2.interfaces.MatrixFactoryInterface;
import neutrons2.interfaces.Regression;

import java.util.List;

public class ExponentialRegression implements Regression {
    private final LogCalculatorInterface logCalculator;
    private final MatrixFactoryInterface matrixFactory;
    private double a;
    private double b;

    public ExponentialRegression(LogCalculatorInterface logCalculator, MatrixFactoryInterface matrixFactory) {
        this.logCalculator = logCalculator;
        this.matrixFactory = matrixFactory;
    }

    public void calculateParameters(List<OrderedPair> pairs) {
        double sumLnX = 0;
        double sumLnY = 0;
        double sumLnXLnY = 0;
        double sumLnX2 = 0;
        int n = pairs.size();

        for (OrderedPair pair : pairs) {
            double lnX = logCalculator.calculateLog(pair.getAtomicNumber());
            double lnY = logCalculator.calculateLog(pair.getNeutrons());

            if (Double.isNaN(lnX) || Double.isNaN(lnY) || Double.isInfinite(lnX) || Double.isInfinite(lnY)) {
                continue;
            }

            sumLnX += lnX;
            sumLnY += lnY;
            sumLnXLnY += lnX * lnY;
            sumLnX2 += lnX * lnX;
        }

        Matrix matrix = matrixFactory.createMatrix(n, sumLnX, sumLnX, sumLnX2);
        double determinant = matrix.determinant();

        if (determinant == 0) {
            System.err.println("Determinant is zero, system has no unique solution.");
            return;
        }

        double[] columnB = {sumLnY, sumLnXLnY};
        Matrix matrixA = matrix.replaceColumn(columnB, 0);
        double determinantA = matrixA.determinant();

        Matrix matrixB = matrix.replaceColumn(columnB, 1);
        double determinantB = matrixB.determinant();

        double lnA = determinantA / determinant;
        b = determinantB / determinant;
        a = Math.exp(lnA);
    }

    public double predict(int x) {
        return a * Math.pow(x, b);
    }

    public void printParameters() {
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
}
