import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManejadorDeDatos {
    public ArrayList<Elemento> leerDatos(String archivo) throws IOException {
        ArrayList<Elemento> elementos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length != 3) continue;
            try {
                int numeroAtomico = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                int neutrones = Integer.parseInt(datos[2]);
                elementos.add(new Elemento(numeroAtomico, nombre, neutrones));
            } catch (NumberFormatException e) {
                continue;
            }
        }
        br.close();
        return elementos;
    }
}
