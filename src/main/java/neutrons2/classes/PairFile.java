package neutrons2.classes;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PairFile {
    private static final Logger logger = Logger.getLogger(PairFile.class.getName());
    private final List<OrderedPair> pairs;

    public PairFile(String filename) throws IOException {
        this.pairs = new ArrayList<>();
        loadPairs(filename);
    }

    private void loadPairs(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            try {
                int atomicNumber = Integer.parseInt(parts[0].trim());
                int neutrons = Integer.parseInt(parts[2].trim());
                pairs.add(new OrderedPair(atomicNumber, neutrons));
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Error parsing the line: " + line, e);
            }
        }
        reader.close();
    }

    public List<OrderedPair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }
}
