package neutrons2.classes;

public class Matrix {
    private final double[][] matrix;

    public Matrix(double a, double b, double c, double d) {
        this.matrix = new double[][]{{a, b}, {c, d}};
    }

    public double determinant() {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    public Matrix replaceColumn(double[] column, int index) {
        double[][] newMatrix = {
                {matrix[0][0], matrix[0][1]},
                {matrix[1][0], matrix[1][1]}
        };
        newMatrix[0][index] = column[0];
        newMatrix[1][index] = column[1];
        return new Matrix(newMatrix[0][0], newMatrix[0][1], newMatrix[1][0], newMatrix[1][1]);
    }
}
