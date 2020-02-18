/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import javafx.stage.Stage;
/**
 *
 * @author Dylan Veraart
 */
public class Main {

 static Stage mainStage;
	static Stage popupStage;
	static Scene newUserScene;
	static Scene loginScene;
	
	static Scene mainScene;
	static Scene settingsScene;
	static Scene dailyScene;
	static Users users;
	static User user;

	
	public static void main() throws MalformedURLException, IOException{
		mainStage = new Stage();
        int width =300;
        int height=300;
        Parent loginRoot = FXMLLoader.load(Main.class.getResource("login.fxml"));
   
		constructLoginButtons();
		loginScene = new Scene(loginRoot, width, height);
        mainStage.setScene(loginScene);
		Parent mainRoot = FXMLLoader.load(*fxml mainGUI file*);
		Parent graphRoot = FXMLLoader.load(*fxml graphGUI file*);
		Input.graphContainer.getChildren().add(graphRoot);
		Graph.constructGraph(user);
		mainScene = new Scene(mainRoot, width, height);
		Parent mainRoot = FXMLLoader.load(*fxml mainScene file*);
		mainScene = new Scene(mainRoot, width, height);
        mainStage.show();
		
		
	}
	
	static void constructLoginButtons(){
		users = ReadWriteJSON.readUsers();
		for (String username : users.users){
			Input.loginButtons.getChildren().add(Input.userButton(username));
		}
		
		Add Input.newUserButton() to Input.loginButtons;
	}
	
	static void setMainLabels(){
		set Input.weightLabel to user.getCurrentWeight();
		set Input.BMILabel to Calculator.calcBMI(user);
		set Input.BMIPercentLabel to Calculator.calcBMIPercentChange(user, Graph.startDatePicker.getValue(), Graph.endDatePicker.getValue())
		set Input.goalLabel to user.targetWeight;
	}
}


