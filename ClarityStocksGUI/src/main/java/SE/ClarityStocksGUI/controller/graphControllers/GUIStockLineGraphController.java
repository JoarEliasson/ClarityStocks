package SE.ClarityStocksGUI.controller.graphControllers;

import alphaVantage.DataPoint;
import alphaVantage.Stock;
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
    private XYChart.Series<String, Number> series;

    public void initialize(){
        System.out.println("StockLineGraphController initialized");
        String css = this.getClass().getResource("/se/ClarityStocksGUI/styles.css").toExternalForm();
        stockLineGraphController = this;
        //chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
        chart.getStylesheets().add(css);
        xAxis.setAnimated(false);
        yAxis.setForceZeroInRange(false);
    }

    public static GUIStockLineGraphController getInstance(){
        return stockLineGraphController;
    }

    public static GUIStockLinegraphController getInstance(){
        return stockLinegraphController;
    }
}