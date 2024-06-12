package neutrons;

import java.io.*;
import java.util.*;

public class DataReader {
    public List<Element> readElements(String filename) throws IOException {
        List<Element> elements = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length != 3) {
                System.err.println("Línea con formato incorrecto: " + line);
                continue;
            }
            try {
                int atomicNumber = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int neutrons = Integer.parseInt(parts[2].trim());
                elements.add(new Element(atomicNumber, name, neutrons));
            } catch (NumberFormatException e) {
                System.err.println("Número inválido en la línea: " + line);
            }
        }
        reader.close();
        return elements;
    }
}
