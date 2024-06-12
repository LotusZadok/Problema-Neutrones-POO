package neutrons2.interfaces;

import neutrons2.classes.OrderedPair;

import java.util.List;

public interface Regression {
    void calculateParameters(List<OrderedPair> pairs);
    double predict(int x);
    void printParameters();
}
