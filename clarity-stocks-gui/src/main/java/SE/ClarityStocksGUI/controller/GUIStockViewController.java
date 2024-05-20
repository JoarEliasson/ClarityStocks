package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.controller.graph.GUIStockLineGraphController;
import SE.ClarityStocksGUI.controller.tiles.InfoTile;
import SE.ClarityStocksGUI.controller.tiles.RatingsTile;
import SE.ClarityStocksGUI.model.Effects;
import analysis.graph.GoldenCrossAnalysis;
import common.data.series.DailyDataPoint;
import common.evaluations.rating.AnalystPredictionEvaluation;
import common.evaluations.rating.BusinessPerformanceEvaluation;
import common.evaluations.rating.CompanyGrowthEvaluation;
import common.evaluations.rating.CompanySizeEvaluation;
import common.evaluations.DividendEvaluation;
import common.evaluations.rating.HighAndLowEvaluation;
import common.evaluations.rating.PERatioEvaluation;
import common.evaluations.rating.PriceToPerformance;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
import model.stock.StockData;
import model.stock.StockDataFetcher;
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
  @FXML
  private VBox categoryLabels;
  private Dialog<String> errorDialog;
  private StockData stockData;
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
    categoryLabels.setVisible(false);

    ratingsTileController.setController(this);
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
          setStockPrice(button1.getText());
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
      }else {
        button1.setSelected(true);
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
    setCategoryLabelsVisibility(false);
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
          StockDataFetcher dataFetcher = new StockDataFetcher();
          stockData = dataFetcher.fetchStockData(stockSymbol);

          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              try{
                graphController.loadStockData(stockData, LocalDate.ofYearDay(2024, 1).toString());
                resetHistoryButtons();
                resetAnalysisSelector();
                setInfoTile();
                setRatingsTile();
                progress.setVisible(false);
                printAllEvaluationTexts();
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

  private void printAllEvaluationTexts(){
    HighAndLowEvaluation hle = stockData.getHighAndLowEvaluation();
    System.out.println(hle.getEvaluationTitle());
    System.out.println(hle.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + hle.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + hle.getEvaluationMethodInfo());
    System.out.println("Results: " + hle.getResultDescription());
    System.out.println();

    PERatioEvaluation pe = stockData.getPeRatioEvaluation();
    System.out.println(pe.getEvaluationTitle());
    System.out.println(pe.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + pe.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + pe.getEvaluationMethodInfo());
    System.out.println("Results: " + pe.getResultDescription());
    System.out.println();

    AnalystPredictionEvaluation ape = stockData.getAnalystPredictionEvaluation();
    System.out.println(ape.getEvaluationTitle());
    System.out.println(ape.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + ape.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + ape.getEvaluationMethodInfo());
    System.out.println("Results: " + ape.getResultDescription());
    System.out.println();

    BusinessPerformanceEvaluation bpe = stockData.getBusinessPerformanceEvaluation();
    System.out.println(bpe.getEvaluationTitle());
    System.out.println(bpe.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + bpe.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + bpe.getEvaluationMethodInfo());
    System.out.println("Results: " + bpe.getResultDescription());
    System.out.println();

    PriceToPerformance ptpe = stockData.getPriceToPerformance();
    System.out.println(ptpe.getEvaluationTitle());
    System.out.println(ptpe.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + ptpe.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + ptpe.getEvaluationMethodInfo());
    System.out.println("Results: " + ptpe.getResultDescription());
    System.out.println();

    CompanySizeEvaluation cse = stockData.getCompanySizeEvaluation();
    System.out.println(cse.getEvaluationTitle());
    System.out.println(cse.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + cse.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + cse.getEvaluationMethodInfo());
    System.out.println("Results: " + cse.getResultDescription());
    System.out.println();


    CompanyGrowthEvaluation cge = stockData.getCompanyGrowthEvaluation();
    System.out.println(cge.getEvaluationTitle());
    System.out.println(cge.getRatingDescription());
    System.out.println();
    System.out.println("General Info: " + cge.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + cge.getEvaluationMethodInfo());
    System.out.println("Results: " + cge.getResultDescription());
    System.out.println();

    DividendEvaluation getDividendEvaluation = stockData.getDividendEvaluation();
    System.out.println(getDividendEvaluation.getEvaluationTitle());
    System.out.println();
    System.out.println("General Info: " + getDividendEvaluation.getGeneralEvaluationInfo());
    System.out.println("Method Info: " + getDividendEvaluation.getEvaluationMethodInfo());
    System.out.println("Results: " + getDividendEvaluation.getResultDescription());
    System.out.println();


    GoldenCrossAnalysis getGoldenCrossAnalysis = stockData.getGoldenCrossAnalysis();
    System.out.println(getGoldenCrossAnalysis.getAnalysisTitle());
    System.out.println();
    System.out.println("General Info: " + getGoldenCrossAnalysis.getGeneralAnalysisInfo());
    System.out.println("Method Info: " + getGoldenCrossAnalysis.getAnalysisMethodInfo());
    System.out.println("Results: " + getGoldenCrossAnalysis.getResultDescription());
    System.out.println();
  }

  public void errorLoadingStock(){
    errorDialog.showAndWait();
    controller.errorLoadingStock();
  }
  private void setInfoTile() {
    infoTileController.setCompanyName(
        stockData.getCompanyOverview().getName() + " (" + stockData.getCompanyOverview().getSymbol() + ")");
    infoTileController.setExchange(stockData.getCompanyOverview().getExchange());
    System.out.println(stockData.getCompanyOverview().getExchange());

    infoTileController.setSector(
        stockData.getCompanyOverview().getSector() + " - " + stockData.getCompanyOverview().getIndustry());

    infoTileController.setDescription(stockData.getCompanyOverview().getDescription());
  }

  private void setRatingsTile(){
    setStockPrice("YTD");

    ratingsTileController.setPeEvaluationText(
        stockData.getPeRatioEvaluation().getRating(),
        stockData.getCompanyOverview().getPERatio(),
        stockData.getPeRatioEvaluation().getResultDescription());

    ratingsTileController.setBusinessPerformance(
        stockData.getBusinessPerformanceEvaluation().getRating(),
        stockData.getBusinessPerformanceEvaluation().getResultDescription());

    ratingsTileController.setCompanyGrowth(
        stockData.getCompanyGrowthEvaluation().getRating(),
        stockData.getCompanyGrowthEvaluation().getResultDescription());

    ratingsTileController.setCompanySize(
        stockData.getCompanySizeEvaluation().getRating(),
        stockData.getCompanySizeEvaluation().getResultDescription());
  }

  @FXML
  public void showGoldenCross(){
    if(!(buttonIsSelected("1Y"))){
      graphController.changeDate("1Y");
      setStockPrice("1Y");
      unselectHistoryButtons("1Y");
    }
    graphController.showGoldenCross();
  }

  public void setCategoryLabelsVisibility(boolean value){
    categoryLabels.setVisible(value);
  }

  private boolean buttonIsSelected(String text){
    for(Node button : historyButtonGroup.getChildren()){
      ToggleButton button1 = (ToggleButton) button;
      if(button1.getText().equals(text) && button1.isSelected()){
        return true;
      }
    }
    return false;
  }

  public void stockFavoritePressed(boolean stockIsFavorite){
    controller.stockFavoritePressed(stockIsFavorite, stockData.getCompanyOverview().getSymbol());
  }

  private void setStockPrice(String stockHistory){
    double comparePrice = 0;
    List<DailyDataPoint> data;
    switch (stockHistory){
      case "1W":
        data = stockData.getTimeSeriesDaily().getDailyDataInRange(
            LocalDate.now().minusWeeks(1).toString(),
            LocalDate.now().toString());
        comparePrice = data.getFirst().getClose();
        break;
      case "1M":
        data = stockData.getTimeSeriesDaily().getDailyDataInRange(
            LocalDate.now().minusMonths(1).toString(),
            LocalDate.now().toString());
        comparePrice = data.getFirst().getClose();
        break;
      case "YTD":
        data = stockData.getTimeSeriesDaily().getDailyDataInRange(
            LocalDate.ofYearDay(2024, 1).toString(),
            LocalDate.now().toString());
        comparePrice = data.getFirst().getClose();
        break;
      case "1Y":
        data = stockData.getTimeSeriesDaily().getDailyDataInRange(
            LocalDate.now().minusYears(1).toString(),
            LocalDate.now().toString());
        comparePrice = data.getFirst().getClose();
        break;
      case "MAX":
        data = stockData.getTimeSeriesMonthly().getMonthlyData();
        comparePrice = data.getLast().getAdjustedClose();
        break;
      default:
        break;
    }
    ratingsTileController.setCurrentPrice(
        stockData.getTimeSeriesDaily().getDailyData().getLast().getClose(),
        comparePrice);
  }

  @FXML
  public void showExplanationPage(String element){
    String mainTitle = "";
    String generalText = "";
    String companyTitle = stockData.getCompanyOverview().getName();
    String companyText = "";
    switch (element){
      case "PE":
        mainTitle   = stockData.getPeRatioEvaluation().getEvaluationTitle();
        generalText = stockData.getPeRatioEvaluation().getGeneralEvaluationInfo()
                    + "\n\n"
                    + stockData.getPeRatioEvaluation().getEvaluationMethodInfo();
        companyText = stockData.getPeRatioEvaluation().getResultDescription();
        break;

      case "busPer":
        mainTitle   = stockData.getBusinessPerformanceEvaluation().getEvaluationTitle();
        generalText = stockData.getBusinessPerformanceEvaluation().getGeneralEvaluationInfo()
                    + "\n\n"
                    + stockData.getBusinessPerformanceEvaluation().getEvaluationMethodInfo();
        companyText = stockData.getBusinessPerformanceEvaluation().getResultDescription();
        break;

      case "compGrowth":
        mainTitle   = stockData.getCompanyGrowthEvaluation().getEvaluationTitle();
        generalText = stockData.getCompanyGrowthEvaluation().getGeneralEvaluationInfo()
                    + "\n\n"
                    + stockData.getCompanyGrowthEvaluation().getEvaluationMethodInfo();
        companyText = stockData.getCompanyGrowthEvaluation().getResultDescription();

        break;

      case "compSize":
        mainTitle   = stockData.getCompanySizeEvaluation().getEvaluationTitle();
        generalText = stockData.getCompanySizeEvaluation().getGeneralEvaluationInfo()
                    + "\n\n"
                    + stockData.getCompanySizeEvaluation().getEvaluationMethodInfo();
        companyText = stockData.getCompanySizeEvaluation().getResultDescription();
        break;

      default:
        break;
    }
    controller.showExplanationPage(mainTitle, generalText, companyTitle, companyText);
  }
}