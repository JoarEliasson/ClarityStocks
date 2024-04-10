package SE.ClarityStocksGUI.controller.graphControllers;

import alphaVantage.AlphaVantageStock;
import alphaVantage.DailyDataPoint;
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
    this.stock = stock;
    if (!(chart.getData().isEmpty())) {
      chart.getData().clear();
    }

    series = new XYChart.Series<>();

    for (DailyDataPoint data : stock.getTimeSeries().reversed()) {

      XYChart.Data<String, Number> point = new XYChart.Data<>(data.getDate(), data.getClose());
      series.getData().add(point);

    }
    chart.getData().add(series);

    for (XYChart.Data<String, Number> data : series.getData()) {
      Tooltip tooltip = new Tooltip();
      tooltip.setText("Price: " + data.getYValue());
      tooltip.setShowDelay(new Duration(0.0));
      tooltip.setHideDelay(new Duration(0.0));

      Tooltip.install(data.getNode(), tooltip
      );
      System.out.println(tooltip.getText());
      data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
      data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
    }
  }
}