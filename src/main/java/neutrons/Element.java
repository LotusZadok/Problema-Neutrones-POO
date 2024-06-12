package neutrons;

public class Element {
    private int atomicNumber;
    private String name;
    private int neutrons;

    public Element(int atomicNumber, String name, int neutrons) {
        this.atomicNumber = atomicNumber;
        this.name = name;
        this.neutrons = neutrons;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getName() {
        return name;
    }

    public int getNeutrons() {
        return neutrons;
    }
}
