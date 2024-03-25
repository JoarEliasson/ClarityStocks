package SE.ClarityStocksGUI.controller.graphControllers;

import SE.ClarityStocksGUI.controller.LoadData;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.AlphaVantageClient;
import alphaVantage.DataPoint;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.util.*;

public class GUIStockLinegraphController {
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private GUIMainApplication application;
    private AlphaVantageClient alphaVantageClient;
    private static GUIStockLinegraphController stockLinegraphController;
    private List<DataPoint> list;
    private XYChart.Series<String, Number> series;

    public void initialize(){
        stockLinegraphController = this;
        LoadData load = new LoadData();
        alphaVantageClient = load.getAlphaVantageClient();
        chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
    }
    public void setApplication(GUIMainApplication application){
        this.application = application;
    }

    public void loadStockData(){
        if(!(chart.getData().isEmpty())){
            chart.getData().clear();
        }
        xAxis.setAnimated(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                list = alphaVantageClient.getFilteredSeries();
                populateChart();
            }
        });


    }

    public static GUIStockLinegraphController getInstance(){
        return stockLinegraphController;
    }

    public void populateChart(){
       series = new XYChart.Series<>();
        for (DataPoint data : list){

            XYChart.Data<String, Number> point = new XYChart.Data<>(data.getDate(), data.getClose());
            series.getData().add(point);

            /* TODO ADD TOOLTIP

                    Tooltip tooltip = new Tooltip("Closing price: " + data.getClose());
                    Tooltip.install(point.getNode(), tooltip);

            */
        }

        chart.getData().add(series);
    }

}