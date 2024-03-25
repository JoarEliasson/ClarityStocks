package SE.ClarityStocksGUI.controller.graphControllers;

import SE.ClarityStocksGUI.controller.LoadData;
import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.AlphaVantageClient;
import alphaVantage.DataPoint;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
    private LineChart<Number, Number> chart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private GUIMainApplication application;
    private AlphaVantageClient alphaVantageClient;
    private static GUIStockLinegraphController stockLinegraphController;
    private List<DataPoint> list;

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
        xAxis.setForceZeroInRange(false);



        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                list = alphaVantageClient.getFilteredSeries();
                populateChart();

                    /* TODO ADD TOOLTIP
                    Tooltip tooltip = new Tooltip("Closing price: " + data.getClose());
                    Tooltip.install(point.getNode(), tooltip);

                     */

            }
        });


    }

    public static GUIStockLinegraphController getInstance(){
        return stockLinegraphController;
    }

    public void populateChart(){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();


        for (DataPoint data : list) {
            String[] date = data.getDate().split("-");
            StringBuilder dateString = new StringBuilder();
            for(String s : date){
                dateString.append(s);
            }


            System.out.println(dateString);


            XYChart.Data<Number, Number> point = new XYChart.Data<>(Integer.parseInt(dateString.toString()), data.getClose());
            series.getData().add(point);
        }

        chart.getData().add(series);
    }

}