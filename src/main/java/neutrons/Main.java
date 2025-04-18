package neutrons;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataReader dataReader = new DataReader();
            List<Element> elements = dataReader.readElements("src/main/resources/elements.txt");

            Calculator calculator = new Calculator();
            double sumLnX = calculator.calculateSumLnX(elements);
            double sumLnY = calculator.calculateSumLnY(elements);
            double sumLnXSquare = calculator.calculateSumLnXSquare(elements);
            double sumLnXLnY = calculator.calculateSumLnXLnY(elements);

            double[] parameters = calculator.calculateParameters(elements.size(), sumLnX, sumLnY, sumLnXSquare, sumLnXLnY);
            double a = parameters[0];
            double b = parameters[1];

            System.out.printf("Fitting parameters: a = %f, b = %f\n\n", a, b);

            System.out.printf("%-4s %-15s %-8s %-20s %-20s %-20s %-20s\n", "No.", "Name", "Atomic", "Real Neutrons", "Pred. Neutrons", "Round Closest", "Round Down");

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                double predictedNeutrons = a * Math.pow(element.getAtomicNumber(), b);
                int roundedNearest = (int) Math.round(predictedNeutrons);
                int roundedDown = (int) Math.floor(predictedNeutrons);

                System.out.printf("%-4d %-15s %-8d %-20d %-20.2f %-20d %-20d\n",
                        i + 1, element.getName(), element.getAtomicNumber(), element.getNeutrons(), predictedNeutrons, roundedNearest, roundedDown);
            }

            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeCSV("src/main/resources/datos.csv", elements, a, b);

            ChartGenerator chartGenerator = new ChartGenerator();
            chartGenerator.generateChart(elements, a, b);

        } catch (IOException e) {
            System.err.println("File read/write error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Calculation error: " + e.getMessage());
        }
    }
}
