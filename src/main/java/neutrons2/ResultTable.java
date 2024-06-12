package neutrons2;

import java.util.List;

public class ResultTable {
    public void showResults(List<OrderedPair> vector, double a, double b) {
        System.out.printf("%-4s %-8s %-15s %-15s\n", "No.", "X", "Y Real", "Y Predicted");
        System.out.println("------------------------------------------------------");

        for (int i = 0; i < vector.size(); i++) {
            OrderedPair pair = vector.get(i);
            double yPredicted = a + b * pair.getX(); // Using the correct linear formula
            System.out.printf("%-4d %-8d %-15d %-15.2f\n",
                    i + 1, pair.getX(), pair.getY(), yPredicted);
        }
    }
}
