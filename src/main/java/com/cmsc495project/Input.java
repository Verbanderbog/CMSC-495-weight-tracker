/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 *
 * @author Dylan Veraart
 */
public class Input implements Initializable {

  Main mainApp;

  @FXML
  TextField newUserName;
  @FXML
  TextField newUserWeight;
  @FXML
  TextField newUserTargetWeight;
  @FXML
  TextField newUserHeightFt;
  @FXML
  TextField newUserHeightIn;

  @FXML
  TextField settingsName;
  @FXML
  TextField settingsTargetWeight;

  @FXML
  TextField dailyWeight;
  @FXML
  TextField dailyTargetWeight;
  @FXML
  TextField dailyHeightFt;
  @FXML
  TextField dailyHeightIn;
  @FXML
  DatePicker dailyDate;
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
    mainApp.popupStage.show();
  }

  @FXML
  void submitNewUser() {
    try {
      User user = new User(newUserName.getText());
      user.setTargetWeight(Double.parseDouble(newUserTargetWeight.getText()));
      user.addWeight(LocalDate.now().toEpochDay(), Double.parseDouble(newUserWeight.getText()));
      double inches, feet;
      feet = -1;
      try {
        inches = Double.parseDouble(newUserHeightIn.getText());
      } catch (NumberFormatException ex) {
        inches = 0;
        try {
          feet = Double.parseDouble(newUserHeightFt.getText()) * 12;
        } catch (NumberFormatException c) {
          throw new NumberFormatException();
        }
      }
      if (feet < 0) {
        feet = Double.parseDouble(newUserHeightFt.getText()) * 12;
      }
      if (feet + inches <= 0) {
        throw new NumberFormatException();
      }
      user.addHeight(LocalDate.now().toEpochDay(), (int) (feet + inches));

      mainApp.users.add(user);

      newUserName.clear();
      newUserWeight.clear();
      newUserTargetWeight.clear();
      newUserHeightFt.clear();
      newUserHeightIn.clear();
      mainApp.user = user;
      mainApp.mainStage.setScene(mainApp.mainScene);
      mainApp.graph.startDatePicker.setValue(Instant.ofEpochMilli(Collections.min(mainApp.user.getDailyWeights().keySet())).atZone(ZoneId.systemDefault()).toLocalDate());
      mainApp.setMainLabels();
      mainApp.popupStage.hide();

    } catch (DuplicateUserException ex) {
      //add warning dialog
    } catch (NumberFormatException ex) {
      //add warning dialog
    } catch (IOException ex) {

    }

  }

  @FXML
  void addNewWeight() {
    mainApp.popupStage.setScene(mainApp.dailyScene);
    mainApp.inputDaily.dailyDate.setValue(LocalDate.now());
    mainApp.popupStage.show();
    /*
    1. Set dailyScene to popupStage;
    2. Show popupStage;
     */
  }

  @FXML
  void submitNewWeight() {
    try {
    if (!dailyWeight.getText().equals("")){
      mainApp.user.addWeight(dailyDate.getValue().toEpochDay(), Double.parseDouble(dailyWeight.getText()));
    }
    if (!(dailyHeightFt.getText().equals("")&&dailyHeightIn.getText().equals(""))){
      mainApp.user.addHeight(dailyDate.getValue().toEpochDay(), (int) (Double.parseDouble(dailyHeightFt.getText())*12+Double.parseDouble(dailyHeightIn.getText())));
    }
    if (!dailyTargetWeight.getText().equals("")){
      mainApp.user.setTargetWeight(Double.parseDouble(dailyTargetWeight.getText()));
    }
    mainApp.setMainLabels();
    mainApp.popupStage.close();
    } catch (NumberFormatException ex) {
      //add warning dialog
    }
    
    /*
    1. Assign value of newUserWeight field to a daily weight variable;
    2. Store new weight 
    
    in HashMap of collective weights;
    3. Hide daily weight input scene;
    4. Set main scene;
     */
  }

  @FXML
  void changeUserSettings() {
    mainApp.popupStage.setScene(mainApp.settingsScene);
    mainApp.inputSettings.settingsDefault();
    mainApp.popupStage.show();
  }

  void settingsDefault(){
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
      } else if(!settingsName.getText().equals(mainApp.user.getUsername())) {
        throw new DuplicateUserException();
      }

      mainApp.user.setTargetWeight(Double.parseDouble(settingsTargetWeight.getText()));
      mainApp.popupStage.hide();
      mainApp.setMainLabels();
    } catch (DuplicateUserException ex) {
      
    } catch (IOException ex) {

    } catch (NumberFormatException ex) {
      
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
