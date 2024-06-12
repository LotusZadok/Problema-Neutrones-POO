package neutrons;

import java.io.*;
import java.util.*;

public class CSVWriter {
    public void writeCSV(String filename, List<Element> elements, double a, double b) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("Numero Atomico,Neutrones Reales,Neutrones Predichos\n");
        for (Element element : elements) {
            double predictedNeutrons = a * Math.pow(element.getAtomicNumber(), b);
            writer.write(String.format("%d,%d,%f\n", element.getAtomicNumber(), element.getNeutrons(), predictedNeutrons));
        }
        writer.close();
    }
}
