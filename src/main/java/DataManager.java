import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {
    public ArrayList<Element> readData(String filePath) throws IOException {
        ArrayList<Element> elements = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 3) continue;
                try {
                    int atomicNumber = Integer.parseInt(data[0]);
                    String name = data[1];
                    int neutrons = Integer.parseInt(data[2]);
                    elements.add(new Element(atomicNumber, name, neutrons));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        return elements;
    }
}
