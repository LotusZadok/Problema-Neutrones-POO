import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultsPrinter {
    public void printResults(double a, double b, ArrayList<Element> elements) throws IOException {
        System.out.printf("Adjustment parameters: a = %f, b = %f%n%n", a, b);
        System.out.printf("%-4s %-20s %-8s %-15s %-15s %-15s %-15s%n", "No.", "Name", "Atomic", "Actual Neutrons", "Predicted Neutrons", "Round to Nearest", "Round Down");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            double prediction = a * Math.pow(element.getAtomicNumber(), b);
            int roundNearest = (int) Math.round(prediction);
            int roundDown = (int) Math.floor(prediction);

            System.out.printf("%-4d %-20s %-8d %-15d %-15.2f %-15d %-15d%n", i + 1, element.getName(), element.getAtomicNumber(), element.getNeutrons(), prediction, roundNearest, roundDown);
        }

        createCSVFile("data.csv", elements, a, b);
    }

    public void createCSVFile(String filename, ArrayList<Element> elements, double a, double b) throws IOException {
        try (FileWriter file = new FileWriter(filename)) {
            file.write("No.,Name,Atomic,Actual Neutrons,Predicted Neutrons,Round to Nearest,Round Down\n");

            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                double prediction = a * Math.pow(element.getAtomicNumber(), b);
                int roundNearest = (int) Math.round(prediction);
                int roundDown = (int) Math.floor(prediction);

                file.write(String.format("%d,%s,%d,%d,%.2f,%d,%d\n", i + 1, element.getName(), element.getAtomicNumber(), element.getNeutrons(), prediction, roundNearest, roundDown));
            }
        }
    }
}
