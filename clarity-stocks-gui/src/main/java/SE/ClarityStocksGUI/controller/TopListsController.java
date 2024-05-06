package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import common.data.global.DailyTopLists;
import common.data.global.GlobalMarketInfo;
import common.data.global.TopListDataPoint;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import model.search.SearchList;
import model.stock.StockDataFetcher;
import model.stock.StockInfo;

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

  private StockDataFetcher dataFetcher;
  private GlobalMarketInfo marketInfo;

  @FXML
  public void initialize() {
    topGainRectangle.setEffect(Effects.getDropShadow());
    topLooserRectangle.setEffect(Effects.getDropShadow());
    volumeRectangle.setEffect(Effects.getDropShadow());
    dataFetcher = new StockDataFetcher();
    marketInfo = dataFetcher.fetchGlobalMarketInfo();
    updateTopLists();
  }

  private void updateTopLists() {
    DailyTopLists dailyTopLists = marketInfo.getDailyTopLists();

    updateListWithData(topGainList, dailyTopLists.getTopGainers());
    updateListWithData(topLooserList, dailyTopLists.getTopLosers());
    updateListWithData(volumeList, dailyTopLists.getMostTraded());
  }

  private void updateListWithData(ListView<String> list,
      java.util.List<TopListDataPoint> dataPoints) {
    list.getItems().clear();
    int count = 0;

    String symbol;
    String stockName;
    SearchList searchList = new SearchList();
    boolean notUnknown = true;
    for (TopListDataPoint point : dataPoints) {
      if (count >= 3 && notUnknown) {
        break;
      }
      notUnknown = true;
      try {
        symbol = dataPoints.get(count).getSymbol();
        String finalSymbol = symbol;
        stockName = searchList.getSearchList().stream()
            .filter(stock -> stock.symbol().equals(finalSymbol))
            .findFirst()
            .map(StockInfo::name)
            .orElse("Unknown");
        System.out.println("Name retrieved from searchlist " + stockName);
        if (stockName.length() > 20) {
          stockName = stockName.substring(0, 20) + "...";
        }
        if (stockName.equals("Unknown")) {
          notUnknown = false;
        } else {

          String displayText = formatStockDisplay(stockName, point);
          list.getItems().add(displayText);
        }

        count++;
      } catch (Exception e) {
        list.getItems().add("Error loading data for: " + point.getSymbol());
      }
    }
  }

  private String formatStockDisplay(String stockName, TopListDataPoint point) {
    return String.format("%s (%s) | $ %.2f | Change: %s | Volume: %d",
        stockName,
        point.getSymbol(),
        point.getCurrentPrice(),
        point.getChangePercentage(),
        point.getTradingVolume()
    );
  }

}
