package neutrons2;

import java.util.List;

public class PairVector {
    private List<OrderedPair> vector;

    public PairVector(List<OrderedPair> vector) {
        this.vector = vector;
    }

    public List<OrderedPair> getVector() {
        return vector;
    }

    public OrderedPair getNextPair(int i) {
        if (i >= 0 && i < vector.size()) {
            return vector.get(i);
        }
        return null;
    }
}
