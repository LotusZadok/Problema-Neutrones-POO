package neutrons2.classes;

import neutrons2.interfaces.MatrixFactoryInterface;

public class MatrixFactory implements MatrixFactoryInterface {
    public Matrix createMatrix(double a, double b, double c, double d) {
        return new Matrix(a, b, c, d);
    }
}
