package SE.ClarityStocksGUI.controller.graphControllers;

import alphaVantage.AlphaVantageStock;
import alphaVantage.DailyDataPoint;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class GUIStockLineGraphController {

  @FXML
  private LineChart<String, Number> chart;
  @FXML
  private CategoryAxis xAxis;
  @FXML
  private NumberAxis yAxis;
  private static GUIStockLineGraphController stockLineGraphController;
  private AlphaVantageStock stock;
  private XYChart.Series<String, Number> series;

  public void initialize() {
    System.out.println("StockLineGraphController initialized");
    String css = this.getClass().getResource("/se/ClarityStocksGUI/styles.css").toExternalForm();
    stockLineGraphController = this;
    //chart.setCreateSymbols(false);
    chart.setLegendVisible(false);
    chart.getStylesheets().add(css);
    chart.setAnimated(false);
    xAxis.setAnimated(false);
    yAxis.setAnimated(false);
    yAxis.setForceZeroInRange(false);
  }

  public static GUIStockLineGraphController getInstance() {
    return stockLineGraphController;
  }

  public void loadStockData(AlphaVantageStock stock) {
    chart.getData().clear();

    XYChart.Series<String, Number> rawSeries = new XYChart.Series<>();
    rawSeries.setName("Stock Prices");

    for (DailyDataPoint data : stock.getTimeSeries().reversed()) {
      XYChart.Data<String, Number> point = new XYChart.Data<>(data.getDate(), data.getClose());
      rawSeries.getData().add(point);
    }

    List<DailyDataPoint> shortTermMovingAverage = stock.getMovingAverage(50);
    List<DailyDataPoint> longTermMovingAverage = stock.getMovingAverage(200);
    XYChart.Series<String, Number> shortTermSeries = new XYChart.Series<>();
    shortTermSeries.setName("Short-Term MA");
    XYChart.Series<String, Number> longTermSeries = new XYChart.Series<>();
    longTermSeries.setName("Long-Term MA");

    for (DailyDataPoint data : shortTermMovingAverage.reversed()) {
      shortTermSeries.getData().add(new XYChart.Data<>(data.getDate(), data.getClose()));
    }

    for (DailyDataPoint data : longTermMovingAverage.reversed()) {
      longTermSeries.getData().add(new XYChart.Data<>(data.getDate(), data.getClose()));
    }

    chart.getData().addAll(rawSeries, shortTermSeries, longTermSeries);
    styleChart();
  }

  private void styleChart() {
    for (XYChart.Series<String, Number> s : chart.getData()) {
      for (XYChart.Data<String, Number> data : s.getData()) {
        Tooltip tooltip = new Tooltip("Date: " + data.getXValue() + "\nValue: " + data.getYValue());
        tooltip.setShowDelay(Duration.seconds(0));
        tooltip.setHideDelay(Duration.seconds(0));
        Tooltip.install(data.getNode(), tooltip);

        data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
        data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
      }
    }
  }
}