package SE.ClarityStocksGUI.controller.graphControllers;

import SE.ClarityStocksGUI.controller.LoadData;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.AlphaVantageClient;
import alphaVantage.DataPoint;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class GUIStockLinegraphController {
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private GUIMainApplication application;
    private AlphaVantageClient alphaVantageClient;
    private static GUIStockLinegraphController stockLineGraphController;

    public void initialize(){
        stockLineGraphController = this;
        LoadData load = new LoadData();
        alphaVantageClient = load.getAlphaVantageClient();
        chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
    }
    public void setApplication(GUIMainApplication application){
        this.application = application;
    }

    public void loadStockData(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        List<DataPoint> list = alphaVantageClient.getFilteredSeries(-1,2024);
        for(DataPoint data : list){
            XYChart.Data<String, Number> point = new XYChart.Data<>(data.getDate(), data.getClose());
            series.getData().add(point);


            /* TODO ADD TOOLTIP
            Tooltip tooltip = new Tooltip("Closing price: " + data.getClose());
            Tooltip.install(point.getNode(), tooltip);

             */
        }

        chart.getData().add(series);

    }

    public static GUIStockLinegraphController getInstance(){
        return stockLineGraphController;
    }

}