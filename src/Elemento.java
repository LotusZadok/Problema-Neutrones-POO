public class Elemento {
    private int numeroAtomico;
    private String nombre;
    private int neutrones;

    public Elemento(int numeroAtomico, String nombre, int neutrones) {
        this.numeroAtomico = numeroAtomico;
        this.nombre = nombre;
        this.neutrones = neutrones;
    }

    public int getNumeroAtomico() {
        return numeroAtomico;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNeutrones() {
        return neutrones;
    }
}
