package neutrons2.classes;

import neutrons2.interfaces.Regression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.List;

public class ChartPlotter extends ApplicationFrame {

    public ChartPlotter(String title, List<OrderedPair> pairs, Regression regression) {
        super(title);
        JFreeChart lineChart = createChart(createDataset(pairs, regression));
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset(List<OrderedPair> pairs, Regression regression) {
        XYSeries realNeutrons = new XYSeries("Real Neutrons");
        XYSeries predictedNeutrons = new XYSeries("Predicted Neutrons");

        for (OrderedPair pair : pairs) {
            int atomicNumber = pair.getAtomicNumber();
            int realNeutronCount = pair.getNeutrons();
            double predictedNeutronCount = regression.predict(atomicNumber);

            realNeutrons.add(atomicNumber, realNeutronCount);
            predictedNeutrons.add(atomicNumber, predictedNeutronCount);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(realNeutrons);
        dataset.addSeries(predictedNeutrons);

        return dataset;
    }

    private JFreeChart createChart(XYSeriesCollection dataset) {
        JFreeChart xylineChart = ChartFactory.createScatterPlot(
                "Real vs Predicted Neutrons",
                "Atomic Number",
                "Neutron Count",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = xylineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, Color.RED);

        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesPaint(1, Color.BLUE);

        plot.setRenderer(renderer);

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

        xylineChart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 16));
        xylineChart.getLegend().setItemFont(new Font("Tahoma", Font.PLAIN, 12));

        return xylineChart;
    }

    public void display() {
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}
