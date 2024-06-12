package neutrons2;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            PairFile pairFile = new PairFile("src/main/resources/elements.txt");
            List<OrderedPair> pairs = pairFile.getPairs();

            PairVector pairVector = new PairVector(pairs);

            // Calculator for parameters a and b
            Calculator calculator = new Calculator();
            double sumX = calculator.calculateSumX(pairs);
            double sumY = calculator.calculateSumY(pairs);
            double sumXSquare = calculator.calculateSumXSquare(pairs);
            double sumXY = calculator.calculateSumXY(pairs);

            double[] parameters = calculator.calculateParameters(pairs.size(), sumX, sumY, sumXSquare, sumXY);
            double a = parameters[0];
            double b = parameters[1];

            System.out.printf("Fitting parameters: a = %f, b = %f\n\n", a, b);

            // Show results
            ResultTable resultTable = new ResultTable();
            resultTable.showResults(pairs, a, b);

            // Generate chart
            ChartGenerator chartGenerator = new ChartGenerator();
            chartGenerator.generateChart(pairs, a, b);

        } catch (IOException e) {
            System.err.println("Error reading or writing the file: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Error in calculation: " + e.getMessage());
        }
    }
}
