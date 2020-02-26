/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.pixelduke.javafx.chart.*;
import java.time.LocalDate;
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
  series.setName("Daily Weight Measurements");
  while (keyIter.hasNext()){
    long key = (long) keyIter.next();
    series.getData().add(new Data(key,weights.get(key)));
  }
  Series goalSeries = new Series();
  goalSeries.setName("Projected Weight Loss");
  long maxDate = Collections.max(weights.keySet());
  goalSeries.getData().add(new Data(maxDate,weights.get(maxDate)));
  goalSeries.getData().add(new Data(Calculator.calcDaysToGoal(mainApp.user)+LocalDate.now().toEpochDay(),mainApp.user.getTargetWeight()));
  chart.getData().clear();
  chart.getData().add(series);
  chart.getData().add(goalSeries);

  dateAxis.setAutoRanging(false);
  dateAxis.setTickUnit(1);
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
