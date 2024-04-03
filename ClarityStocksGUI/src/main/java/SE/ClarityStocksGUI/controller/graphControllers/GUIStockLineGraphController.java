package SE.ClarityStocksGUI.controller.graphControllers;

import alphaVantage.DataPoint;
import alphaVantage.Stock;
import java.util.ArrayList;
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
    private Stock stock;
    private XYChart.Series<String, Number> rawSeries;
    private XYChart.Series<String, Number> shortTermSeries;
    private XYChart.Series<String, Number> longTermSeries;



    public void initialize(){
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

    public static GUIStockLineGraphController getInstance(){
        return stockLineGraphController;
    }

    public void loadStockData(Stock stock){
        this.stock = stock;
        chart.getData().clear();

        rawSeries = new XYChart.Series<>();
        rawSeries.setName("Stock Prices");

        for (DataPoint data : stock.getTimeSeries().reversed()){
            XYChart.Data<String, Number> point = new XYChart.Data<>(data.getDate(), data.getClose());
            rawSeries.getData().add(point);
        }

        List<DataPoint> shortTermMovingAverage = calculateMovingAverage(stock.getTimeSeries(), 50);
        List<DataPoint> longTermMovingAverage = calculateMovingAverage(stock.getTimeSeries(), 200);
        shortTermSeries = new XYChart.Series<>();
        shortTermSeries.setName("Short-Term MA");
        longTermSeries = new XYChart.Series<>();
        longTermSeries.setName("Long-Term MA");

        for (DataPoint data : shortTermMovingAverage.reversed()) {
            shortTermSeries.getData().add(new XYChart.Data<>(data.getDate(), data.getClose()));
        }

        for (DataPoint data : longTermMovingAverage.reversed()) {
            longTermSeries.getData().add(new XYChart.Data<>(data.getDate(), data.getClose()));
        }
        chart.getData().addAll(rawSeries, shortTermSeries, longTermSeries);
        styleChart();
    }


    private List<DataPoint> calculateMovingAverage(List<DataPoint> dataPoints, int period) {
        List<DataPoint> movingAverage = new ArrayList<>();
        for (int i = 0; i < dataPoints.size(); i++) {
          int end = Math.min(i + period, dataPoints.size());
            double sum = 0;
            for (int j = i; j < end; j++) {
                sum += dataPoints.get(j).getClose();
            }
            double average = sum / (end - i);
            movingAverage.add(new DataPoint(dataPoints.get(i).getDate(), average));
        }
        return movingAverage;
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