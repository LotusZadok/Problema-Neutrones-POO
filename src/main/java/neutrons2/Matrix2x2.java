package neutrons2;

public class Matrix2x2 {
    private int A, B, C, D;

    public Matrix2x2(int A, int B, int C, int D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public int getA() {
        return A;
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getD() {
        return D;
    }

    public void setD(int D) {
        this.D = D;
    }

    public int determinant() {
        return A * D - B * C;
    }

    public int determinantX(int x1, int x2) {
        return x1 * D - B * x2;
    }

    public int determinantY(int y1, int y2) {
        return A * y1 - C * y2;
    }
}
