import java.util.ArrayList;

public class NeutronCalculator {
    public double sumLnY(ArrayList<Element> elements) {
        return elements.stream().filter(e -> e.getNeutrons() != 0).mapToDouble(e -> Math.log(e.getNeutrons())).sum();
    }

    public double sumLnX(ArrayList<Element> elements) {
        return elements.stream().filter(e -> e.getAtomicNumber() != 0).mapToDouble(e -> Math.log(e.getAtomicNumber())).sum();
    }

    public double sumLnXSquare(ArrayList<Element> elements) {
        return elements.stream().filter(e -> e.getAtomicNumber() != 0).mapToDouble(e -> Math.log(e.getAtomicNumber()) * Math.log(e.getAtomicNumber())).sum();
    }

    public double sumLnXLnY(ArrayList<Element> elements) {
        return elements.stream().filter(e -> e.getAtomicNumber() != 0 && e.getNeutrons() != 0).mapToDouble(e -> Math.log(e.getAtomicNumber()) * Math.log(e.getNeutrons())).sum();
    }

    public double[] calculateAandB(ArrayList<Element> elements) {
        int n = elements.size();
        double sumLnY = sumLnY(elements);
        double sumLnX = sumLnX(elements);
        double sumLnXSquare = sumLnXSquare(elements);
        double sumLnXLnY = sumLnXLnY(elements);

        double[][] mainMatrix = { {n, sumLnX}, {sumLnX, sumLnXSquare} };
        double[][] matrixA = { {sumLnY, sumLnX}, {sumLnXLnY, sumLnXSquare} };
        double[][] matrixB = { {n, sumLnY}, {sumLnX, sumLnXLnY} };

        double mainDeterminant = calculateDeterminant(mainMatrix);
        double determinantA = calculateDeterminant(matrixA);
        double determinantB = calculateDeterminant(matrixB);

        double a = Math.exp(determinantA / mainDeterminant);
        double b = determinantB / mainDeterminant;

        return new double[]{a, b};
    }

    private double calculateDeterminant(double[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }
}
