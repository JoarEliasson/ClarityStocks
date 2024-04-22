package alphaVantage;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartTest extends ApplicationFrame {

  public ChartTest(String title, XYSeriesCollection dataset, SimpleRegression priceRegression) {
    super(title);
    JFreeChart chart = ChartFactory.createScatterPlot("Price vs Time", "Days", "Price",
        dataset);
    XYPlot plot = (XYPlot) chart.getPlot();

    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    renderer.setSeriesLinesVisible(0, true);
    renderer.setSeriesShapesVisible(0, true);
    plot.setRenderer(renderer);

    double slope = priceRegression.getSlope();
    double intercept = priceRegression.getIntercept();

    XYSeries trend = new XYSeries("Trend");
    for (int i = 0; i < dataset.getSeries(0).getItemCount(); i++) {
      trend.add(i, slope * i + intercept);
    }
    dataset.addSeries(trend);

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
    setContentPane(chartPanel);
  }
}
