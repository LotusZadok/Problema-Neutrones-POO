package neutrons2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.List;

public class ChartGenerator {

    public void generateChart(List<OrderedPair> elements, double a, double b) {
        XYSeries realSeries = new XYSeries("Real Neutrons");
        XYSeries predictedSeries = new XYSeries("Predicted Neutrons");

        for (OrderedPair element : elements) {
            double predictedNeutrons = a + b * element.getX();
            realSeries.add(element.getX(), element.getY());
            predictedSeries.add(element.getX(), predictedNeutrons);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(realSeries);
        dataset.addSeries(predictedSeries);

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Real vs Predicted Neutrons",
                "Atomic Number",
                "Neutrons",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
