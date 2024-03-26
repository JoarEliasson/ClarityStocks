package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import model.NasdaqStockholmCompanyData;

public class GUIHomeController {
    private GUIMainApplication application;
    @FXML
    private BorderPane layout;
    @FXML
    private Rectangle menuBar;
    @FXML
    private Rectangle menuBarLine;
    @FXML
    private VBox mainVBox;
    @FXML
    private MFXButton stockButton;
    @FXML
    private MFXButton homeButton;
    @FXML
    private TextField searchField;

    //For testing purposes, will be removed later
    @FXML
    private Label welcomeText;
    @FXML
    private Label testDataInfo;
    @FXML
    private Label testData;
    @FXML
    private MFXButton updateTestData;

    private NasdaqStockholmCompanyData companyData;

    public void initialize(){
        VBox.setVgrow(layout,javafx.scene.layout.Priority.ALWAYS);
        homeButton.setText("Home");
        stockButton.setText("Stock");
        //homeButton.getStyleClass().setAll("mfx-button");
        menuBar.widthProperty().bind(mainVBox.widthProperty());
        menuBarLine.widthProperty().bind(mainVBox.widthProperty());

        welcomeText.setText("WORK IN PROGRESS!!!\nThis is the home view.");
        testDataInfo.setText("To set testdata, make a method that calls the setTestData() method in the Test class.");
        testData.setText("This is the test data.");
        updateTestData.setText("Update test data");
        /*
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            performSearch(newValue); // Method to perform search
        });

         */
    }

    public void setApplication(GUIMainApplication application){
        this.application = application;
    }
    public void setCompanyData(NasdaqStockholmCompanyData companyData) {
        this.companyData = companyData;
        //setupSearchBar();
    }
    /*
    private void setupSearchBar() {
        // Assuming searchField is your TextField for search
        TextFields.bindAutoCompletion(searchField, t -> {
            return companyData.searchCompaniesByName(t.getUserText()).stream()
                    .map(ListedCompanyData::getNAME)
                    .collect(Collectors.toList());
        });
    }
    private void performSearch(String query) {
        List<ListedCompanyData> searchResults = companyData.searchCompaniesByName(query);
        // Do something with the search results
    }

     */
    @FXML
    public void goToStockView(){
        application.goToStockView();
    }
}