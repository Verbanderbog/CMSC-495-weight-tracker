/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.pixelduke.javafx.chart.DateAxis;
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
@FXML private DatePicker startDatePicker;
@FXML private DatePicker endDatePicker;
@FXML LineChart chart;
@FXML private DateAxis dateAxis;
private Main mainApp;
void constructGraph(){
  startDatePicker.setValue(LocalDate.ofEpochDay(Collections.min(mainApp.user.getDailyWeights().keySet())));
  endDatePicker.setValue(LocalDate.ofEpochDay(LocalDate.now().toEpochDay()+Calculator.calcDaysToGoal(mainApp.user)));
  HashMap<Long,Double> weights = mainApp.user.getDailyWeights();
  Iterator keyIter = weights.keySet().iterator();
  Series series = new Series();
  series.setName("Daily Weight Measurements");
  //series.getNode().lookup(".chart-series-area-line").setStyle("-fx-stroke: rgba(25, 17, 196, 1.0);");
  while (keyIter.hasNext()){
    long key = (long) keyIter.next();
    series.getData().add(new Data(key,weights.get(key)));
  }
  Series goalSeries = new Series();
  goalSeries.setName("Projected Weight Loss");
  long maxDate = Collections.max(weights.keySet());
  goalSeries.getData().add(new Data(maxDate,weights.get(maxDate)));
  goalSeries.getData().add(new Data(Calculator.calcDaysToGoal(mainApp.user)+LocalDate.now().toEpochDay(),mainApp.user.getTargetWeight()));
  //goalSeries.getNode().lookup(".chart-series-area-line").setStyle("-fx-stroke: rgba(66, 100, 195, 1.0);");
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

void setMain(Main main){
  mainApp=main;
}

}
