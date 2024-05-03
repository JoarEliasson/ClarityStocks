package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import alphaVantage.model.data.global.DailyTopLists;
import alphaVantage.model.data.global.TopListDataPoint;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;

public class TopListsController {

  @FXML
  public ListView<String> topGainList;
  @FXML
  public ListView<String> topLooserList;
  @FXML
  public ListView<String> volumeList;

  @FXML
  public Rectangle topGainRectangle;
  @FXML
  public Rectangle topLooserRectangle;
  @FXML
  public Rectangle volumeRectangle;


  private AlphaVantageClient alphaVantageClient;

  @FXML
  public void initialize() {
    topGainRectangle.setEffect(Effects.getDropShadow());
    topLooserRectangle.setEffect(Effects.getDropShadow());
    volumeRectangle.setEffect(Effects.getDropShadow());
    alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
    updateTopLists();
  }

  private void updateTopLists() {
    DailyTopLists dailyTopLists = alphaVantageClient.getGlobalMarketInfo().getDailyTopLists();

    updateListWithData(topGainList, dailyTopLists.getTopGainers());
    updateListWithData(topLooserList, dailyTopLists.getTopLosers());
    updateListWithData(volumeList, dailyTopLists.getMostTraded());
  }

  private void updateListWithData(ListView<String> list,
      java.util.List<TopListDataPoint> dataPoints) {
    list.getItems().clear();
    int count = 0;

    for (TopListDataPoint point : dataPoints) {
        if (count >= 3) {
            break;
        }

      try {
        AlphaVantageStock stock = alphaVantageClient.getStock(point.getSymbol());
        String displayText = formatStockDisplay(stock, point);
        list.getItems().add(displayText);
        count++;
      } catch (Exception e) {
        list.getItems().add("Error loading data for: " + point.getSymbol());
      }
    }
  }

  private String formatStockDisplay(AlphaVantageStock stock, TopListDataPoint point) {
    if (stock != null && !stock.getTimeSeriesDaily().getDailyData().isEmpty()) {
      String name = stock.getCompanyOverview().getName();
      double price = stock.getTimeSeriesDaily().getDailyData().get(0).getClose();
      return String.format("%s (%s) | $%.2f", name, point.getSymbol(), price);
    }
    return String.format("%s (%s) | No data available", point.getSymbol(), point.getSymbol());
  }


}
