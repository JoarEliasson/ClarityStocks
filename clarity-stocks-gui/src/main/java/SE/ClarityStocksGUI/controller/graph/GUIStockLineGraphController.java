package SE.ClarityStocksGUI.controller.graph;

import common.data.series.DailyDataPoint;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import model.stock.StockData;

public class GUIStockLineGraphController {

  @FXML
  private LineChart<String, Number> chart;
  @FXML
  private CategoryAxis xAxis;
  @FXML
  private NumberAxis yAxis;
  private static GUIStockLineGraphController stockLineGraphController;
  //private StockData stock;
  private XYChart.Series<String, Number> series;
  private XYChart.Series<String, Number> shortTermSeries;
  private XYChart.Series<String, Number> longTermSeries;
  private boolean goldenCrossActive = false;

  public void initialize() {
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

  public void loadStockData(StockData stock) {
    chart.getData().clear();
    goldenCrossActive = false;

    List<DailyDataPoint> data = stock.getTimeSeriesDaily().getDailyDataInRange("2021-01-01",
        LocalDate.now().toString());

    List<DailyDataPoint> shortTermMovingAverage = stock.getTimeSeriesDaily().calculateCenteredMovingAverage(data, 50);

    List<DailyDataPoint> longTermMovingAverage = stock.getTimeSeriesDaily().calculateTrailingMovingAverage(data, 200);

    XYChart.Series<String, Number> rawSeries = new XYChart.Series<>();
    rawSeries.setName("Stock Prices");
    for (DailyDataPoint dataPoint : data) {
      rawSeries.getData().add(new XYChart.Data<>(dataPoint.getDate(), dataPoint.getClose()));
    }

    shortTermSeries = new XYChart.Series<>();
    shortTermSeries.setName("Short-Term MA");
    longTermSeries = new XYChart.Series<>();
    longTermSeries.setName("Long-Term MA");
    for (DailyDataPoint dataPoint : shortTermMovingAverage) {
      shortTermSeries.getData().add(new XYChart.Data<>(dataPoint.getDate(), dataPoint.getClose()));
    }
    for (DailyDataPoint dataPoint : longTermMovingAverage) {
      longTermSeries.getData().add(new XYChart.Data<>(dataPoint.getDate(), dataPoint.getClose()));
    }

    chart.getData().add(rawSeries);
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

  public void showGoldenCross(){
    if(!goldenCrossActive){
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          chart.getData().addAll(shortTermSeries, longTermSeries);
          goldenCrossActive = true;
        }
      });
    }else {
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          chart.getData().removeAll(shortTermSeries, longTermSeries);
          goldenCrossActive = false;
        }
      });
    }
  }
}