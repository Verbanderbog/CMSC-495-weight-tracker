/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 *
 * @author Dylan Veraart
 */
public class Input implements Initializable {

  private Main mainApp;

  @FXML
  private TextField newUserName;
  @FXML
  private TextField newUserWeight;
  @FXML
  private TextField newUserTargetWeight;
  @FXML
  private TextField newUserHeightFt;
  @FXML
  private TextField newUserHeightIn;

  @FXML
  private TextField settingsName;
  @FXML
  private TextField settingsTargetWeight;

  @FXML
  private TextField dailyWeight;
  @FXML
  private TextField dailyTargetWeight;
  @FXML
  private TextField dailyHeightFt;
  @FXML
  private TextField dailyHeightIn;
  @FXML
  private DatePicker dailyDate;
  @FXML
  VBox loginButtons;
  @FXML
  BorderPane graphContainer;

  @FXML
  Label weightLabel;
  @FXML
  Label BMILabel;
  @FXML
  Label BMIPercentLabel;
  @FXML
  Label goalLabel;
  @FXML
  Label goalDaysLabel;

  Button userButton(String username) {
    Button button = new Button(username);
    button.setFont(new Font(30));
    button.setMaxWidth(Double.MAX_VALUE);
    button.setOnAction(new EventHandler() {

      @Override
      public void handle(Event t) {
        try {
          mainApp.user = ReadWriteJSON.readUser(username);

          mainApp.mainStage.setScene(mainApp.mainScene);

          mainApp.setMainLabels();

        } catch (Exception ex) {

        }

      }
    });
    return button;
  }

  Button newUserButton() {
    Button button = new Button("+ New User");
    button.setFont(new Font(30));
    button.setMaxWidth(Double.MAX_VALUE);
    button.setOnAction(new EventHandler() {

      @Override
      public void handle(Event t) {
        addNewUser();
      }
    });
    return button;
  }

  void addNewUser() {
    mainApp.popupStage.setScene(mainApp.newUserScene);
    mainApp.popupStage.setTitle("Daily Weight Tracker");
    mainApp.popupStage.setMinWidth(280);
    mainApp.popupStage.setMinHeight(380);
    mainApp.popupStage.setWidth(280);
    mainApp.popupStage.setHeight(380);
    mainApp.popupStage.show();
  }

  @FXML
  void submitNewUser() {
    try {
      if (newUserName.getText().equals("") || (newUserHeightFt.getText().equals("") && newUserHeightIn.getText().equals("")) || newUserTargetWeight.getText().equals("") || newUserWeight.getText().equals("")) {
        throw new MandatoryFieldException();
      }
      User user = new User(newUserName.getText());
      user.setTargetWeight(Double.parseDouble(newUserTargetWeight.getText()));
      user.addWeight(LocalDate.now().toEpochDay(), Double.parseDouble(newUserWeight.getText()));
      double feet = (!newUserHeightFt.getText().equals("")) ? Double.parseDouble(newUserHeightFt.getText()) : 0;
      double inches = (!newUserHeightIn.getText().equals("")) ? Double.parseDouble(newUserHeightIn.getText()) : 0;
      if (feet + inches <= 0) {
        throw new NumberFormatException();
      }
      user.addHeight(LocalDate.now().toEpochDay(), (int) (feet * 12 + inches));

      mainApp.users.add(user);

      newUserName.clear();
      newUserWeight.clear();
      newUserTargetWeight.clear();
      newUserHeightFt.clear();
      newUserHeightIn.clear();
      mainApp.user = user;
      mainApp.mainStage.setScene(mainApp.mainScene);

      mainApp.setMainLabels();
      mainApp.popupStage.hide();

    } catch (DuplicateUserException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("Duplicate User Warning");
      alert.setContentText("This username is already in use. Choose a unique name.");

      alert.showAndWait();
    } catch (NumberFormatException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("Non-numeric Input");
      alert.setContentText("A field that requires numeric input has an invalid character.");

      alert.showAndWait();
    } catch (IOException ex) {

    } catch (MandatoryFieldException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("Mandatory Input");
      alert.setContentText("A mandatory field is blank. A fields must be filled to create a user.");

      alert.showAndWait();
    }

  }

  @FXML
  void addNewWeight() {
    mainApp.popupStage.setScene(mainApp.dailyScene);
    mainApp.popupStage.setTitle("Daily Weight Tracker - New Measurement");
    mainApp.popupStage.setMinWidth(280);
    mainApp.popupStage.setMinHeight(380);
    mainApp.popupStage.setWidth(280);
    mainApp.popupStage.setHeight(380);
    mainApp.inputDaily.dailyDate.setValue(LocalDate.now());
    mainApp.popupStage.show();
  }

  @FXML
  void submitNewWeight() {
    try {
      if ((dailyHeightFt.getText().equals("") && dailyHeightIn.getText().equals("")) && dailyTargetWeight.getText().equals("") && dailyWeight.getText().equals("")) {
        throw new MandatoryFieldException();
      }
      if (!dailyWeight.getText().equals("")) {
        mainApp.user.addWeight(dailyDate.getValue().toEpochDay(), Double.parseDouble(dailyWeight.getText()));
      }
      if (!(dailyHeightFt.getText().equals("") && dailyHeightIn.getText().equals(""))) {
        double feet = (!dailyHeightFt.getText().equals("")) ? Double.parseDouble(dailyHeightFt.getText()) : 0;
        double inches = (!dailyHeightIn.getText().equals("")) ? Double.parseDouble(dailyHeightIn.getText()) : 0;
        if (feet + inches <= 0) {
          throw new NumberFormatException();
        }
        mainApp.user.addHeight(dailyDate.getValue().toEpochDay(), (int) (feet * 12 + inches));
      }
      if (!dailyTargetWeight.getText().equals("")) {
        mainApp.user.setTargetWeight(Double.parseDouble(dailyTargetWeight.getText()));
      }
      mainApp.setMainLabels();
      dailyHeightFt.setText("");
      dailyHeightIn.setText("");
      dailyTargetWeight.setText("");
      dailyWeight.setText("");
      mainApp.popupStage.close();
    } catch (NumberFormatException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("Non-numeric Input");
      alert.setContentText("A field that requires numeric input has an invalid character or is outside the appropriate range.");

      alert.showAndWait();
    } catch (MandatoryFieldException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("No Input");
      alert.setContentText("You haven't added any new measurements.");
      alert.showAndWait();
    }
  }

  @FXML
  void changeUserSettings() {
    mainApp.popupStage.setScene(mainApp.settingsScene);
    mainApp.popupStage.setTitle("Daily Weight Tracker - User Settings");
    mainApp.popupStage.setMinWidth(340);
    mainApp.popupStage.setMinHeight(350);
    mainApp.popupStage.setWidth(340);
    mainApp.popupStage.setHeight(350);
    mainApp.inputSettings.settingsDefault();
    mainApp.popupStage.show();
  }

  void settingsDefault() {
    settingsName.setText(mainApp.user.getUsername());
    settingsTargetWeight.setText(Double.toString(mainApp.user.getTargetWeight()));
  }

  @FXML
  void submitUserSettings() {

    try {
      if (!mainApp.users.contains(settingsName.getText())) {
        String oldUserName = mainApp.user.getUsername();
        mainApp.user.setUsername(settingsName.getText());
        mainApp.users.add(mainApp.user);
        mainApp.users.remove(oldUserName);
      } else if (!settingsName.getText().equals(mainApp.user.getUsername())) {
        throw new DuplicateUserException();
      }

      mainApp.user.setTargetWeight(Double.parseDouble(settingsTargetWeight.getText()));
      mainApp.popupStage.hide();
      mainApp.setMainLabels();
    } catch (DuplicateUserException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("Duplicate User Warning");
      alert.setContentText("This username is already in use. Choose a unique name.");

      alert.showAndWait();
    } catch (IOException ex) {

    } catch (NumberFormatException ex) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Daily Weight Tracker");
      alert.setHeaderText("Non-numeric Input");
      alert.setContentText("A field that requires numeric input has an invalid character.");

      alert.showAndWait();
    }

  }

  @FXML
  void deleteAccount() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Daily Weight Tracker");
    alert.setHeaderText("Delete User Account");
    alert.setContentText("Are you sure you want to delete your account? This cannot be undone.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      try {
        mainApp.users.remove(mainApp.user);
        mainApp.popupStage.hide();
        mainApp.constructLoginButtons();
        mainApp.mainStage.setScene(mainApp.loginScene);
        mainApp.mainStage.setTitle("Daily Weight Tracker");
        mainApp.user = null;
      } catch (IOException ex) {

      }

    }
  }

  @FXML
  void logout() {
    mainApp.constructLoginButtons();
    mainApp.mainStage.setScene(mainApp.loginScene);
    mainApp.mainStage.setTitle("Daily Weight Tracker");
    try {
      ReadWriteJSON.writeUser(mainApp.user);
    } catch (IOException ex) {

    }
    mainApp.user = null;

  }

  void setMain(Main main) {
    mainApp = main;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }
}
