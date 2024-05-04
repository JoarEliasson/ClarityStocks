package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graph.GUIStockLineGraphController;
import SE.ClarityStocksGUI.controller.tiles.InfoTile;
import SE.ClarityStocksGUI.controller.tiles.RatingsTile;
import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * {@code GUIStockViewController}
 * <p>
 * This class is the controller for the stock-view. It's the controller for the Stock-view.fxml file.
 * <p>
 * The stock-view is split up between different tiles. Where each tile shows different information
 * about the stock. The GUIStockViewController class handles the loading of data to the different
 * tiles that exist inside the stock-view.
 * <p>
 * The class has the Main-view as the parent and the children are the following, Info-Tile,
 * Ratings-tile and Stock-lineGraph.
 *
 * @author Douglas Almö Thorsell
 * @see GUIMainController
 * @see InfoTile
 * @see RatingsTile
 * @see GUIStockLineGraphController
 */
public class GUIStockViewController {

  private GUIMainController controller;
  @FXML
  private BorderPane layout;
  @FXML
  private HBox historyButtonGroup;
  @FXML
  private ComboBox<Label> analysisSelector;
  @FXML
  private GUIStockLineGraphController graphController;
  @FXML
  private Rectangle graphBackground;
  @FXML
  private Rectangle descBackground;
  @FXML
  private Rectangle statBackground;
  @FXML
  private Rectangle dialTileBackground;
  @FXML
  private RatingsTile ratingsTileController;
  @FXML
  private InfoTile infoTileController;
  @FXML
  private ScrollPane scrollPane;
  private Dialog<String> errorDialog;
  private AlphaVantageStock stock;
  private HashMap<Integer, Boolean> selectedAnalysis;

  @FXML
  private ProgressBar progress;


  public void initialize() {
    setUpControllers();
    progress.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    setupHistoryButtons();
    setupAnalysisSelector();
    createErrorDialog();
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);

    descBackground.setEffect(Effects.getDropShadow());
    statBackground.setEffect(Effects.getDropShadow());
    graphBackground.setEffect(Effects.getDropShadow());
    dialTileBackground.setEffect(Effects.getDropShadow());
  }

  private void setUpControllers(){
    graphController.setController(this);
    infoTileController.setController(this);
  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }
  public void setupScrollbar() {
    scrollPane.minWidthProperty().bind(controller.getWidthProperty());
    scrollPane.minHeightProperty().bind(controller.getHeightProperty());
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setEffect(null);
  }

  private void setupHistoryButtons(){
    historyButtonGroup.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    for(Node button : historyButtonGroup.getChildren()){
      ToggleButton button1 = (ToggleButton) button;
      if(button1.getText().equals("YTD")){
        button1.setSelected(true);
      }
      button1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          unselectHistoryButtons(button1.getText());
          resetAnalysisSelector();
          graphController.changeDate(button1.getText());
        }
      });
    }
  }

  private void resetHistoryButtons() {
    for (Node button : historyButtonGroup.getChildren()) {
      ToggleButton button1 = (ToggleButton) button;
      button1.setSelected(button1.getText().equals("YTD"));
    }
  }

  @FXML
  public void unselectHistoryButtons(String buttonText){
    for(Node button : historyButtonGroup.getChildren()){
      ToggleButton button1 = (ToggleButton) button;
      if(!(buttonText.equals(button1.getText()))){
        button1.setSelected(false);
      }
    }
  }

  private void createErrorDialog(){
    errorDialog = new Dialog<>();
    errorDialog.setTitle("Error: Couldn't load stock");
    errorDialog.setContentText("The stock couldn't be loaded.\n"
        + "You are either out of API calls or have no internet.");
    ButtonType button = new ButtonType("Ok", ButtonData.OK_DONE);
    errorDialog.getDialogPane().getButtonTypes().add(button);
  }
  private void setupAnalysisSelector(){
    selectedAnalysis = new HashMap<>();
    analysisSelector.setPromptText("Analysis");
    analysisSelector.setButtonCell(new ListCell<>() {
      @Override
      protected void updateItem(Label item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
          setText(analysisSelector.getPromptText());
        } else {
          setText(item.getText());
        }
      }
    });
    analysisSelector.getItems().add(new Label("Golden Cross"));
    selectedAnalysis.put(0, false);
  }

  @FXML
  public void analysisSelected(){
    if(!analysisSelector.getValue().getText().equals("")){
      int index = getAnalysisIndex(analysisSelector.getValue().getText());
      if(index > -1){
        setSelectedAnalysisColor(index);
        goToAnalysis(analysisSelector.getValue().getText());
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            analysisSelector.setValue(new Label());
          }
        });
      }
    }
  }

  private void resetAnalysisSelector(){
    for(int i : selectedAnalysis.keySet()){
      analysisSelector.getItems().get(i).
          setStyle("-fx-background-color: transparent; -fx-padding: 3; -fx-background-radius: 10");
      selectedAnalysis.put(i, false);
    }
  }

  private void setSelectedAnalysisColor(int index){
    if(selectedAnalysis.get(index)){
      analysisSelector.getItems().get(index).
          setStyle("-fx-background-color: transparent; -fx-padding: 3; -fx-background-radius: 10");
      selectedAnalysis.put(index, false);

    }else {
      analysisSelector.getItems().get(index).
          setStyle("-fx-background-color: #9f9f9f; -fx-padding: 3; -fx-background-radius: 10");
      selectedAnalysis.put(index, true);

    }
  }
  private int getAnalysisIndex(String text){
    for(int i = 0; i < analysisSelector.getItems().size(); i++){
      if(analysisSelector.getItems().get(i).getText().equals(text)){
        return i;
      }
    }
    return -1;
  }
  
  private void goToAnalysis(String text){
    switch(text){
      case("Golden Cross"):
        showGoldenCross();
    }
  }

  public void loadStockView(String stockSymbol) {
    progress.setVisible(true);
    new Thread(new Runnable() {

      @Override
      public void run() {
        try{
          AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
          stock = alphaVantageClient.getStock(stockSymbol);

          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              try{
                graphController.loadStockData(stock, LocalDate.ofYearDay(2024, 1).toString());
                resetHistoryButtons();
                resetAnalysisSelector();
                setInfoTile();
                setRatingsTile();
                progress.setVisible(false);
              }catch (NoSuchElementException e){
                errorLoadingStock();
                e.printStackTrace();
              }
            }
          });
        }catch (RuntimeException e){
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              errorLoadingStock();
              e.printStackTrace();

            }
          });
        }

      }
    }).start();
  }

  public void errorLoadingStock(){
    errorDialog.showAndWait();
    controller.errorLoadingStock();
  }
  private void setInfoTile() {
    infoTileController.setCompanyName(
        stock.getCompanyOverview().getName() + " (" + stock.getCompanyOverview().getSymbol() + ")");

    infoTileController.setSector(
        stock.getCompanyOverview().getSector() + " - " + stock.getCompanyOverview().getIndustry());

    infoTileController.setDescription(stock.getCompanyOverview().getDescription());
  }

  private void setRatingsTile(){
    ratingsTileController.setCurrentPrice(
        stock.getTimeSeriesDaily().getDailyData().getFirst().getClose());

    ratingsTileController.setPeEvaluationText(
        stock.getPeRatioEvaluation().getRating(),
        stock.getCompanyOverview().getPERatio(),
        stock.getPeRatioEvaluation().getDescription());

    ratingsTileController.setBusinessPerformance(
        stock.getBusinessPerformanceEvaluation().getRating(),
        stock.getBusinessPerformanceEvaluation().getDescription());

    ratingsTileController.setCompanyGrowth(
        stock.getCompanyGrowthEvaluation().getRating(),
        stock.getCompanyGrowthEvaluation().getDescription());

    ratingsTileController.setCompanySize(
        stock.getCompanySizeEvaluation().getRating(),
        stock.getCompanySizeEvaluation().getDescription());
  }

  @FXML
  public void showGoldenCross(){
    graphController.showGoldenCross();
  }

  public void stockFavoritePressed(boolean stockIsFavorite){
    controller.stockFavoritePressed(stockIsFavorite, stock.getCompanyOverview().getSymbol());
  }

}
