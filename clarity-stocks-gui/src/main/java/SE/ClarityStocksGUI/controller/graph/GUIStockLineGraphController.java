package SE.ClarityStocksGUI.controller.graph;

import SE.ClarityStocksGUI.controller.GUIStockViewController;
import common.data.series.DailyDataPoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.stock.StockData;

/**
 * This class handles the line graph which is displayed in the stock-view. It also handles the
 * different analysis that can be displayed on the graph.
 * <p>
 * Its parent class is the stock-view.
 * @author Douglas Almö Thorsell
 * @see GUIStockViewController
 */
public class GUIStockLineGraphController {

  @FXML
  private LineChart<String, Number> chart;
  @FXML
  private CategoryAxis xAxis;
  @FXML
  private NumberAxis yAxis;
  private StockData stockData;
  private GUIStockViewController controller;
  private static GUIStockLineGraphController stockLineGraphController;
  //private AlphaVantageStock stock;
  private XYChart.Series<String, Number> shortTermSeries;
  private XYChart.Series<String, Number> longTermSeries;
  private XYChart.Series<String, Number> rawSeries;
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

  public void loadStockData(StockData selectedStock, String startDate) {
    this.stockData = selectedStock;
    chart.getData().clear();
    goldenCrossActive = false;
    boolean adjusted = false;
    List<DailyDataPoint> data;
    if (startDate == null) {
      data = stockData.getTimeSeriesMonthly().getMonthlyData();
      adjusted = true;
      data = data.reversed();
    } else {
      data = stockData.getTimeSeriesDaily().getDailyDataInRange(startDate, LocalDate.now().toString());
    }

    List<DailyDataPoint> shortTermMovingAverage = stockData.getTimeSeriesDaily().calculateCenteredMovingAverage(data, 50);

    List<DailyDataPoint> longTermMovingAverage = stockData.getTimeSeriesDaily().calculateTrailingMovingAverage(data, 200);

    rawSeries = new XYChart.Series<>();
    rawSeries.setName("Stock Prices");
    for (DailyDataPoint dataPoint : data) {
      rawSeries.getData().add(new XYChart.Data<>(dataPoint.getDate(), getClose(dataPoint, adjusted)));
    }

    shortTermSeries = new XYChart.Series<>();
    shortTermSeries.setName("Short-Term MA");
    longTermSeries = new XYChart.Series<>();
    longTermSeries.setName("Long-Term MA");
    for (DailyDataPoint dataPoint : shortTermMovingAverage) {
      shortTermSeries.getData().add(new XYChart.Data<>(dataPoint.getDate(), getClose(dataPoint, adjusted)));
    }
    for (DailyDataPoint dataPoint : longTermMovingAverage) {
      longTermSeries.getData().add(new XYChart.Data<>(dataPoint.getDate(), getClose(dataPoint, adjusted)));
    }

    chart.getData().add(rawSeries);
    getCrossPoints();
    styleChart();
  }

  private double getClose(DailyDataPoint dataPoint, boolean adjusted) {
    return adjusted ? dataPoint.getAdjustedClose() : dataPoint.getClose();
  }

  public void changeDate(String changeTo){
    switch (changeTo){
      case "1W":
        loadStockData(stockData, LocalDate.now().minusWeeks(1).toString());
        break;

      case "1M":
        loadStockData(stockData, LocalDate.now().minusMonths(1).toString());
        break;

      case "YTD":
        loadStockData(stockData, LocalDate.ofYearDay(2024, 1).toString());
        break;
      case "1Y":
        loadStockData(stockData, LocalDate.now().minusYears(1).toString());
        break;
      case "MAX":
        loadStockData(stockData, null);
        break;


      default:
        System.out.println("Error: Can't change date");
        break;
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

  private void getCrossPoints() {
    ArrayList<Data<String, Number>> crossPoints = new ArrayList();
    List<String> goldenCrossEvents = stockData.getTimeSeriesDaily()
        .getGoldenCrossEvents("2021-01-01", LocalDate.now().toString());
    for (String s : goldenCrossEvents) {
      LocalDate crossDate = LocalDate.parse(s);
      for (Data<String, Number> data : longTermSeries.getData()) {
        LocalDate checkDate = LocalDate.parse(data.getXValue());
        if (crossDate.equals(checkDate)) {
          data.setNode(new Circle(4));
        }
      }
    }
  }

  public void setController(GUIStockViewController controller){
    this.controller = controller;
  }
}