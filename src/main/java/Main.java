import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        NeutronCalculator neutronCalculator = new NeutronCalculator();
        ResultsPrinter resultsPrinter = new ResultsPrinter();

        try {
            ArrayList<Element> elements = dataManager.readData("src/elements.txt");

            double[] parameters = neutronCalculator.calculateAandB(elements);
            double a = parameters[0];
            double b = parameters[1];

            resultsPrinter.printResults(a, b, elements);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
