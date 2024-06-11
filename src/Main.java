import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ManejadorDeDatos manejador = new ManejadorDeDatos();
        try {
            ArrayList<Elemento> elementos = manejador.leerDatos("elements.txt");

            CalculadorDeNeutrones calculador = new CalculadorDeNeutrones();
            double[] AB = calculador.calcularAyB(elementos);
            double a = AB[0];
            double b = AB[1];

            ImpresorDeResultados impresor = new ImpresorDeResultados();
            impresor.imprimirResultados(a, b, elementos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
