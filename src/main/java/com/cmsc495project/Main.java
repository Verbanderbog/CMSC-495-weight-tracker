/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Dylan Veraart
 */
public class Main extends Application {

  Input inputMain;
  Input inputLogin;
  Input inputNewUser;
  Input inputSettings;
  Graph graph;
  Stage mainStage;
  Stage popupStage;
  Scene newUserScene;
  Scene loginScene;

  Scene mainScene;
  Scene settingsScene;
  Scene dailyScene;
  Users users;
  User user;

  public static void main(String args[]) throws MalformedURLException, IOException {
    Main.launch(Main.class);
  }

  void constructLoginButtons() {
    try {
      users = ReadWriteJSON.readUsers();
    } catch (FileNotFoundException ex) {
      users = new Users();
    }
    inputLogin.loginButtons.getChildren().clear();
    for (String username : users.users) {
      inputLogin.loginButtons.getChildren().add(inputLogin.userButton(username));
    }
    inputLogin.loginButtons.getChildren().add(inputLogin.newUserButton());
  }

  void setMainLabels() {
    mainStage.setMinWidth(550);
    mainStage.setTitle("Daily Weight Tracker - " + user.getUsername());
    inputMain.weightLabel.setText(Double.toString(user.getCurrentWeight()));
    inputMain.BMILabel.setText(Double.toString(Calculator.calcCurrentBMI(user)));
    inputMain.BMIPercentLabel.setText(Double.toString(Calculator.calcBMIPercentChange(user)));
    inputMain.goalLabel.setText(Double.toString(user.getTargetWeight()));

  }

  @Override
  public void start(Stage mainStage) throws Exception {
    this.mainStage = mainStage;
    int width = 500;
    int height = 700;
    FXMLLoader loader = new FXMLLoader(Input.class.getClassLoader().getResource("loginGUI.fxml"));
    Parent loginRoot = loader.load();
    inputLogin = loader.getController();
    inputLogin.mainApp = this;

    constructLoginButtons();

    loginScene = new Scene(loginRoot, width, height);
    mainStage.setScene(loginScene);
    loader = new FXMLLoader(Input.class.getClassLoader().getResource("mainGUI.fxml"));
    Parent mainRoot = loader.load();
    inputMain = loader.getController();
    inputMain.mainApp = this;
    loader = new FXMLLoader(Graph.class.getClassLoader().getResource("graphGUI.fxml"));
    Parent graphRoot = loader.load();
    graph = loader.getController();
    graph.mainApp = this;
    inputMain.graphContainer.getChildren().add(graphRoot);
    mainScene = new Scene(mainRoot, width + 50, height);
    loader = new FXMLLoader(Input.class.getClassLoader().getResource("newUserGUI.fxml"));
    Parent newUserRoot = loader.load();
    inputNewUser = loader.getController();
    inputNewUser.mainApp = this;
    newUserScene = new Scene(newUserRoot, width, height);
    mainStage.setMinWidth(width);
    mainStage.setMinHeight(height);
    mainStage.setTitle("Daily Weight Tracker");
    popupStage = new Stage();
    popupStage.initModality(Modality.WINDOW_MODAL);
    popupStage.initOwner(mainStage);
    loader = new FXMLLoader(Input.class.getClassLoader().getResource("settingsGUI.fxml"));
    Parent settingsRoot = loader.load();
    inputSettings = loader.getController();
    inputSettings.mainApp = this;
    settingsScene = new Scene(settingsRoot, width, height);
    mainStage.show();
    

  }

  @Override
  public void stop() {
    if (user != null) {
      try {
        try {
          users.add(user);
        } catch (DuplicateUserException ex) {
          ReadWriteJSON.writeUser(user);
        }
      } catch (IOException ex) {

      }
    }
  }

}
