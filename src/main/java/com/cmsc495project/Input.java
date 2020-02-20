/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
  DatePicker newUserBirthDate;

  @FXML
  TextField settingsName;
  @FXML
  DatePicker settingsBirthDate;

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
    newUserBirthDate.setValue(LocalDate.now());
    mainApp.popupStage.show();
  }

  @FXML
  void submitNewUser() {
    try {
      User user = new User(newUserName.getText());
      user.setTargetWeight(Double.parseDouble(newUserTargetWeight.getText()));
      user.addWeight(LocalDate.now().toEpochDay(), Double.parseDouble(newUserWeight.getText()));
      double inches,feet;
      feet=-1;
      try {
        inches = Double.parseDouble(newUserHeightIn.getText());
      } catch (NumberFormatException ex) {
        inches=0;
        try {
        feet = Double.parseDouble(newUserHeightFt.getText()) * 12;
        } catch (NumberFormatException c) {
          throw new NumberFormatException();
        }
      }
      if (feet<0){
        feet = Double.parseDouble(newUserHeightFt.getText()) * 12;
      }
      if (feet+inches<=0) {
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
    /*
    1. Set dailyScene to popupStage;
    2. Show popupStage;
     */
  }

  @FXML
  void submitNewWeight() {
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
    /*
    1. Set settingsScene to popupStage;
    2. Show popupStage;
    7
     */
  }

  @FXML
  void submitUserSettings() {
    /*
    1. Re - assign values of settings fields;
    2. Update labels for user data   as needed;
    3. Hide user settings scene;
    4. Set main scene;
     */
  }

  @FXML
  void logout() {
    mainApp.constructLoginButtons();
    mainApp.mainStage.setScene(mainApp.loginScene);
    mainApp.mainStage.setTitle("Daily Weight Tracker");
    mainApp.user = null;

  }

  void setMain(Main main) {
    mainApp = main;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    if (graphContainer != null) {
      try {
        Parent graphRoot = FXMLLoader.load(Graph.class.getClassLoader().getResource("graphGUI.fxml"));
        graphContainer.getChildren().add(graphRoot);
      } catch (IOException ex) {

      }

    }
  }
}
