package neutrons2;

import neutrons2.classes.*;
import neutrons2.interfaces.Regression;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            PairFile pairFile = new PairFile("src/main/resources/elements.txt");
            List<OrderedPair> pairs = pairFile.getPairs();

            LogCalculator logCalculator = new LogCalculator();
            MatrixFactory matrixFactory = new MatrixFactory();
            Regression regression = new ExponentialRegression(logCalculator, matrixFactory);
            regression.calculateParameters(pairs);
            regression.printParameters();

            ResultPrinter printer = new ResultPrinter();
            printer.printResults(pairs, regression);

            ChartPlotter chartPlotter = new ChartPlotter("Neutron Prediction", pairs, regression);
            chartPlotter.display();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading the elements file", e);
        }
    }
}
