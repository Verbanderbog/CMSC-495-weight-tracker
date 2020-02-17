/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Dylan Veraart
 */
public class Input {

  static TextField newUserName;
  static TextField newUserWeight;
  static TextField newUserTargetWeight;
  static TextField newUserHeight;
  static DatePicker newUserBirthDate;
  static DatePicker newUserDate;

  static TextField settingsName;
  static DatePicker settingsBirthDate;

  static TextField dailyWeight;
  static TextField dailyTargetWeight;
  static TextField dailyHeight;
  static DatePicker dailyDate;

  static VBox loginButtons;
  static BorderPane graphContainer;

  static Label weightLabel;
  static Label BMILabel;
  static Label BMIPercentLabel;
  static Label goalLabel;

  static Button userButton(String username) {
    Create Button button;
    Create actionEvent
    
      (){
			Main.user = ReadWriteJSON.readUser(username, User.class);
      Main.mainStage.setScene(Main.mainScene);
    };
    set button action to actionEvent;
    return button;
  }

  static Button newUserButton() {
    Create Button button;
    Create actionEvent
    
      (){
			addNewUser();
    };
    set button action to actionEvent;
    return button;
  }

  static void addNewUser() {
    Main.popupStage.setScene(Main.newUserScene);
    Main.popupStage.show();
  }

  static void submitNewUser() {
    Value of newUser fields to new User(), user;
    Main.user = user;
    Main.setMainLabels();
    Main.users.add(user);
    Main.popupStage.hide();
    Main.mainStage.setScene(Main.mainScene);
    Clear all newUser fields;

  }

  static void addNewWeight() {
    1. Set dailyScene to popupStage;
    2. Show popupStage;
  }

  static void submitNewWeight() {
    1. Assign value of newUserWeight field to a daily weight variable;
    2. Store new weight 
    in HashMap of collective weights;
    3. Hide daily weight input scene;
    4. Set main scene;
  }

  static void changeUserSettings() {
    1. Set settingsScene to popupStage;
    2. Show popupStage;
    7
  }

  static void submitUserSettings() {
    1. Re - assign values of settings fields;
    2. Update labels for user data  as needed;
    3. Hide user settings scene;
    4. Set main scene;
  }

}

static void logout(){
		Main.constructLoginButtons();
		Main.mainStage.setScene(Main.loginScene);
		Main.user=null;
	}
}
