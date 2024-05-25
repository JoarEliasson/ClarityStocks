package com.claritystocks.controller;

import com.claritystocks.model.Effects;
import common.data.global.GlobalMarketInfo;
import common.data.global.TopListDataPoint;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.TextSize;
import eu.hansolo.tilesfx.TileBuilder;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.stock.StockDataFetcher;

/**
 * Controller class for the top lists view.
 * <p>
 * This class is responsible for handling the top lists view and updating the top gainers, top losers
 * and most traded stocks.
 * </p>
 *
 * @author Douglas Almö Thorsell
 * @author Ibrahim Tafankaji
 * @author Joar Eliasson
 */
public class TopListsController {

  @FXML
  private GridPane topGainersPane, topLosersPane, mostTradedPane;

  @FXML
  public Rectangle topGainersRectangle, topLosersRectangle, mostTradedRectangle;

  private final List<Tile> topGainersTiles = new ArrayList<>();
  private final List<Tile> topLosersTiles = new ArrayList<>();
  private final List<Tile> mostTradedTiles = new ArrayList<>();
  private List<TopListDataPoint> topGainers;
  private List<TopListDataPoint> topLosers;
  private List<TopListDataPoint> mostTraded;
  private StockDataFetcher dataFetcher;
  private GlobalMarketInfo marketInfo;

  @FXML
  public void initialize() {
    topGainersRectangle.setEffect(Effects.getDropShadow());
    topLosersRectangle.setEffect(Effects.getDropShadow());
    mostTradedRectangle.setEffect(Effects.getDropShadow());

    dataFetcher = new StockDataFetcher();
    marketInfo = dataFetcher.fetchGlobalMarketInfo();
    topGainers = marketInfo.getDailyTopLists().getTopGainers();
    topLosers = marketInfo.getDailyTopLists().getTopLosers();
    mostTraded = marketInfo.getDailyTopLists().getMostTraded();
    if (topGainers != null && !topGainers.isEmpty() && topLosers != null && !topLosers.isEmpty()
        && mostTraded != null && !mostTraded.isEmpty()) {
      setupTiles();
      updateTopLists();
    }
  }

  private void setupTiles() {
    for (int i = 0; i < 3; i++) {
      Tile tile = TileBuilder.create()
          .skinType(SkinType.HIGH_LOW)
          .prefSize(160, 140)
          .backgroundColor(Color.rgb(245, 245, 245))
          .title(topGainers.get(i).getSymbol())
          .unit("$")
          .description("+" + (topGainers.get(i).getChangeAmount()) + "$")
          .value(topGainers.get(i).getCurrentPrice() - topGainers.get(i).getChangeAmount())
          .referenceValue(topGainers.get(i).getCurrentPrice() - topGainers.get(i).getChangeAmount())
          .text(String.format("Top Gainer n. %d", i + 1))
          .textSize(TextSize.BIGGER)
          .titleColor(Color.BLACK)
          .valueColor(Color.BLACK)
          .unitColor(Color.BLACK)
          .descriptionColor(Color.BLACK)
          .textColor(Color.BLACK)
          .build();
      topGainersTiles.add(tile);
      topGainersPane.add(tile, i, 0);
    }

    for (int i = 0; i < 3; i++) {
      Tile tile = TileBuilder.create()
          .skinType(SkinType.HIGH_LOW)
          .prefSize(160, 140)
          .backgroundColor(Color.rgb(245, 245, 245))
          .title(topLosers.get(i).getSymbol())
          .unit("$")
          .description((topLosers.get(i).getChangeAmount()) + "$")
          .value((topLosers.get(i).getCurrentPrice() - topLosers.get(i).getChangeAmount()))
          .referenceValue((topLosers.get(i).getCurrentPrice() - topLosers.get(i).getChangeAmount()))
          .text(String.format("Top Loser n. %d", i + 1))
          .textSize(TextSize.BIGGER)
          .titleColor(Color.BLACK)
          .valueColor(Color.BLACK)
          .unitColor(Color.BLACK)
          .descriptionColor(Color.BLACK)
          .textColor(Color.BLACK)
          .build();
      topLosersTiles.add(tile);
      topLosersPane.add(tile, i, 0);
    }

    for (int i = 0; i < 3; i++) {
      Tile tile = TileBuilder.create()
          .skinType(SkinType.HIGH_LOW)
          .prefSize(160, 140)
          .backgroundColor(Color.rgb(245, 245, 245))
          .title(mostTraded.get(i).getSymbol())
          .unit("$")
          .description((mostTraded.get(i).getChangeAmount()) + "$")
          .value((mostTraded.get(i).getCurrentPrice() - mostTraded.get(i).getChangeAmount()))
          .referenceValue((mostTraded.get(i).getCurrentPrice() - mostTraded.get(i).getChangeAmount()))
          .text(String.format("n. %d Volume %d", i + 1, mostTraded.get(i).getTradingVolume()))
          .textSize(TextSize.BIGGER)
          .titleColor(Color.BLACK)
          .valueColor(Color.BLACK)
          .unitColor(Color.BLACK)
          .descriptionColor(Color.BLACK)
          .textColor(Color.BLACK)
          .build();
      mostTradedTiles.add(tile);
      mostTradedPane.add(tile, i, 0);
    }
  }

  private void updateTopLists() {
    Platform.runLater(() -> {
      for (int i = 0; i < 3 && i < topGainers.size(); i++) {
        TopListDataPoint topGainer = topGainers.get(i);
        Tile tile = topGainersTiles.get(i);
        tile.setValue(topGainer.getCurrentPrice());
      }
      for (int i = 0; i < 3 && i < topLosers.size(); i++) {
        TopListDataPoint topLoser = topLosers.get(i);
        Tile tile = topLosersTiles.get(i);
        tile.setValue(topLoser.getCurrentPrice());
      }
      for (int i = 0; i < 3 && i < mostTraded.size(); i++) {
        TopListDataPoint mostTradedStock = mostTraded.get(i);
        Tile tile = mostTradedTiles.get(i);
        tile.setValue(mostTradedStock.getCurrentPrice());
      }
    });
  }
}
