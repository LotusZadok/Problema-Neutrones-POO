package neutrons2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PairFile {
    private String filename;

    public PairFile(String filename) {
        this.filename = filename;
    }

    public List<OrderedPair> getPairs() throws IOException {
        List<OrderedPair> pairs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[2].trim());
                pairs.add(new OrderedPair(x, y));
            }
        }
        reader.close();
        return pairs;
    }
}
