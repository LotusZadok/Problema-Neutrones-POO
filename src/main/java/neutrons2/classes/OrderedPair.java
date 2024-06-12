package neutrons2.classes;

public class OrderedPair {
    private final int atomicNumber;
    private final int neutrons;

    public OrderedPair(int atomicNumber, int neutrons) {
        this.atomicNumber = atomicNumber;
        this.neutrons = neutrons;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public int getNeutrons() {
        return neutrons;
    }
}
