package com.claritystocks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.model.UserProfile;
import user.model.UserProfileManager;

/**
 * This class handles the dialog for changing the user name.
 * <p>
 * It's parent class is the {@code GUIMainController} which is the controller for the main view.
 * </p>
 *
 * @see GUIMainController
 * @see UserProfile
 * @see UserProfileManager
 *
 * @author Ibrahinm Tafankaji
 */
public class ChangeUserNameDialogController {

  @FXML
  private TextField userNameField;

  private UserProfile userProfile;
  private GUIMainController mainController;
  private Stage stage;

  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
    userNameField.setText(userProfile.getUserName());
  }

  public void setMainController(GUIMainController mainController) {
    this.mainController = mainController;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @FXML
  private void handleSave() {
    String newUserName = userNameField.getText();
    userProfile.setUserName(newUserName);
    UserProfileManager.saveUserInformation(userProfile, "clarity-stocks-user/userInfo.json");
    mainController.updateView();

    Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
    welcomeAlert.setTitle("Welcome");
    welcomeAlert.setHeaderText(null);
    welcomeAlert.setContentText("Welcome " + newUserName);
    welcomeAlert.showAndWait();

    stage.close();
  }
}
