import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ImpresorDeResultados {
    public void imprimirResultados(double a, double b, ArrayList<Elemento> elementos) throws IOException {
        System.out.printf("Parámetros de ajuste: a = %f, b = %f%n%n", a, b);
        System.out.printf("%-4s %-20s %-8s %-15s %-15s %-15s %-15s%n", "No.", "Nombre", "Atómico", "Neutrones Reales", "Neutrones Predichos", "Redondeo al más cercano", "Redondeo hacia cero");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < elementos.size(); i++) {
            Elemento elemento = elementos.get(i);
            double prediccion = a * Math.pow(elemento.getNumeroAtomico(), b);
            int redondeoCercano = (int) Math.round(prediccion);
            int redondeoCero = (int) Math.floor(prediccion);

            System.out.printf("%-4d %-20s %-8d %-15d %-15.2f %-15d %-15d%n", i + 1, elemento.getNombre(), elemento.getNumeroAtomico(), elemento.getNeutrones(), prediccion, redondeoCercano, redondeoCero);
        }

        crearArchivoCSV("datos.csv", elementos, a, b);
    }

    public void crearArchivoCSV(String filename, ArrayList<Elemento> elementos, double a, double b) throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write("No.,Nombre,Atómico,Neutrones Reales,Neutrones Predichos,Redondeo al más cercano,Redondeo hacia cero\n");

        for (int i = 0; i < elementos.size(); i++) {
            Elemento elemento = elementos.get(i);
            double prediccion = a * Math.pow(elemento.getNumeroAtomico(), b);
            int redondeoCercano = (int) Math.round(prediccion);
            int redondeoCero = (int) Math.floor(prediccion);

            file.write(String.format("%d,%s,%d,%d,%.2f,%d,%d\n", i + 1, elemento.getNombre(), elemento.getNumeroAtomico(), elemento.getNeutrones(), prediccion, redondeoCercano, redondeoCero));
        }

        file.close();
    }
}
