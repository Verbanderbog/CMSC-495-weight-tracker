/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 *
 * @author Dylan Veraart
 */
public class Main extends Application {
  Input inputDaily;
  private Input inputMain;
  private Input inputLogin;
  private Input inputNewUser;
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
    graph.constructGraph();
    inputMain.weightLabel.setText(Double.toString(user.getCurrentWeight()));
    inputMain.BMILabel.setText(Double.toString(Calculator.calcCurrentBMI(user)));
    inputMain.BMIPercentLabel.setText(Double.toString(Calculator.calcBMIPercentChange(user)));
    inputMain.goalLabel.setText(Double.toString(user.getTargetWeight()));
    inputMain.goalDaysLabel.setText(Long.toString(Calculator.calcDaysToGoal(user)));
  }

  @Override
  public void start(Stage mainStage) throws Exception {
    this.mainStage = mainStage;
    int width = 500;
    int height = 700;
    FXMLLoader loader = new FXMLLoader(Input.class.getClassLoader().getResource("loginGUI.fxml"));
    Parent loginRoot = loader.load();
    inputLogin = loader.getController();
    inputLogin.setMain(this);

    constructLoginButtons();

    loginScene = new Scene(loginRoot, width, height);
    mainStage.setScene(loginScene);
    loader = new FXMLLoader(Input.class.getClassLoader().getResource("mainGUI.fxml"));
    Parent mainRoot = loader.load();
    inputMain = loader.getController();
    inputMain.setMain(this);
    loader = new FXMLLoader(Graph.class.getClassLoader().getResource("graphGUI.fxml"));
    Parent graphRoot = loader.load();
    graph = loader.getController();
    graph.setMain(this);
    inputMain.graphContainer.setCenter(graphRoot);
    
    mainScene = new Scene(mainRoot, width + 50, height);
    loader = new FXMLLoader(Input.class.getClassLoader().getResource("newUserGUI.fxml"));
    Parent newUserRoot = loader.load();
    inputNewUser = loader.getController();
    inputNewUser.setMain(this);
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
    inputSettings.setMain(this);
    settingsScene=new Scene(settingsRoot, width, height);
    loader = new FXMLLoader(Input.class.getClassLoader().getResource("inputGUI.fxml"));
    Parent dailyRoot = loader.load();
    inputDaily = loader.getController();
    inputDaily.setMain(this);
    dailyScene = new Scene(dailyRoot, width, height);
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
