/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.pixelduke.javafx.chart.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.*;
import javafx.scene.control.*;

/**
 *
 * @author Dylan Veraart
 */
public class Graph {
@FXML DatePicker startDatePicker;
@FXML DatePicker endDatePicker;
@FXML LineChart chart;
@FXML DateAxis dateAxis;
@FXML NumberAxis weightAxis;
Main mainApp;
void constructGraph(){
  
  HashMap<Long,Double> weights = mainApp.user.getDailyWeights();
  Iterator keyIter = weights.keySet().iterator();
  Series series = new Series();
  while (keyIter.hasNext()){
    long key = (long) keyIter.next();
    series.getData().add(new Data(key,weights.get(key)));
  }
  chart.getData().clear();
  chart.getData().add(series);
  dateAxis.setAutoRanging(false);
  dateAxis.setLowerBound(startDatePicker.getValue().toEpochDay());
  dateAxis.setUpperBound(endDatePicker.getValue().toEpochDay());
}
@FXML
void startDateChange(){
	dateAxis.setLowerBound(startDatePicker.getValue().toEpochDay());
}
@FXML
void endDateChange(){
	dateAxis.setUpperBound(endDatePicker.getValue().toEpochDay());
}

}
