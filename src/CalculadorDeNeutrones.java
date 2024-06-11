import java.util.ArrayList;

public class CalculadorDeNeutrones {
    public double sumatoriaLnY(ArrayList<Elemento> elementos) {
        return elementos.stream().filter(e -> e.getNeutrones() != 0).mapToDouble(e -> Math.log(e.getNeutrones())).sum();
    }

    public double sumatoriaLnX(ArrayList<Elemento> elementos) {
        return elementos.stream().filter(e -> e.getNumeroAtomico() != 0).mapToDouble(e -> Math.log(e.getNumeroAtomico())).sum();
    }

    public double sumatoriaLnXCuadrado(ArrayList<Elemento> elementos) {
        return elementos.stream().filter(e -> e.getNumeroAtomico() != 0).mapToDouble(e -> Math.log(e.getNumeroAtomico()) * Math.log(e.getNumeroAtomico())).sum();
    }

    public double sumatoriaLnXLnY(ArrayList<Elemento> elementos) {
        return elementos.stream().filter(e -> e.getNumeroAtomico() != 0 && e.getNeutrones() != 0).mapToDouble(e -> Math.log(e.getNumeroAtomico()) * Math.log(e.getNeutrones())).sum();
    }

    public double[] calcularAyB(ArrayList<Elemento> elementos) {
        int n = elementos.size();
        double sumLnY = sumatoriaLnY(elementos);
        double sumLnX = sumatoriaLnX(elementos);
        double sumLnXCuadrado = sumatoriaLnXCuadrado(elementos);
        double sumLnXLnY = sumatoriaLnXLnY(elementos);

        double[][] matrizPrincipal = { {n, sumLnX}, {sumLnX, sumLnXCuadrado} };
        double[][] matrizA = { {sumLnY, sumLnX}, {sumLnXLnY, sumLnXCuadrado} };
        double[][] matrizB = { {n, sumLnY}, {sumLnX, sumLnXLnY} };

        double detPrincipal = calcularDeterminante(matrizPrincipal);
        double detA = calcularDeterminante(matrizA);
        double detB = calcularDeterminante(matrizB);

        double a = Math.exp(detA / detPrincipal);
        double b = detB / detPrincipal;

        return new double[]{a, b};
    }

    private double calcularDeterminante(double[][] matriz) {
        return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
    }
}
