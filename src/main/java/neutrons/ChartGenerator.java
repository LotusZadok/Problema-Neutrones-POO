package neutrons;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartGenerator {

    public void generateChart(List<Element> elements, double a, double b) {
        XYSeries realSeries = new XYSeries("Real Neutrons");
        XYSeries predictedSeries = new XYSeries("Predicted Neutrons");

        for (Element element : elements) {
            double predictedNeutrons = a * Math.pow(element.getAtomicNumber(), b);
            realSeries.add(element.getAtomicNumber(), element.getNeutrons());
            predictedSeries.add(element.getAtomicNumber(), predictedNeutrons);
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
        renderer.setSeriesPaint(0, Color.RED); // Real Neutrons as red dots

        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesPaint(1, Color.BLUE); // Predicted Neutrons as blue line

        plot.setRenderer(renderer);

        // Improve the appearance of the plot
        plot.setOutlinePaint(Color.BLACK);
        plot.setOutlineStroke(new BasicStroke(1.0f));
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));
        domainAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 14));
        domainAxis.setTickLabelPaint(Color.BLACK);
        domainAxis.setLabelPaint(Color.BLACK);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));
        rangeAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 14));
        rangeAxis.setTickLabelPaint(Color.BLACK);
        rangeAxis.setLabelPaint(Color.BLACK);

        chart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 16));
        chart.getLegend().setItemFont(new Font("Tahoma", Font.PLAIN, 12));

        // Create and display the chart
        ApplicationFrame frame = new ApplicationFrame("Neutron Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}
