package neutrons2.classes;

import neutrons2.interfaces.Regression;

import java.util.List;

public class ResultPrinter {
    public void printResults(List<OrderedPair> pairs, Regression regression) {
        System.out.println("Atomic Number\tReal Neutrons\tPredicted Neutrons\tRounded Closest\tRounded Down\tDifference");
        for (OrderedPair pair : pairs) {
            int atomicNumber = pair.getAtomicNumber();
            int realNeutrons = pair.getNeutrons();
            double predictedNeutrons = regression.predict(atomicNumber);
            int roundedClosest = (int) Math.round(predictedNeutrons);
            int roundedDown = (int) Math.floor(predictedNeutrons);
            int differenceClosest = Math.abs(realNeutrons - roundedClosest);
            int differenceDown = Math.abs(realNeutrons - roundedDown);

            System.out.printf("%d\t\t\t%d\t\t\t%.2f\t\t\t%d\t\t\t%d\t\t\t%d\t\t\t%d%n",
                    atomicNumber, realNeutrons, predictedNeutrons, roundedClosest, roundedDown, differenceClosest, differenceDown);
        }
    }
}
