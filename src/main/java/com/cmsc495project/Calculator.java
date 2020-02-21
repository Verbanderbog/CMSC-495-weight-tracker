/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.util.Collections;

/**
 *
 * @author Dylan Veraart
 */
public class Calculator {
    
  
  static double calcCurrentBMI(User user) {
    return calcBMI(user.getWeight(Collections.max(user.getDailyWeights().keySet())),user.getHeight(Collections.max(user.getHeights().keySet())));
  }
  
    // Method to calculate a BMI value
    static double calcBMI(double weight, int height) {
        // This formula is for units of pounds & inches
        double bmi = (weight * 703)/(height * height);
        // This rounds the BMI to one decimal place
        double roundedBMI = Math.round(bmi * 10) / 10.0;
        return roundedBMI;
    }
    
    // Method to calculate BMI % change
    static double calcBMIPercentChange(User user) {
        double initialWeight = user.getWeight(Collections.min(user.getDailyWeights().keySet()));
        int initialHeight = user.getHeight(Collections.min(user.getHeights().keySet()));
        double currentWeight = user.getWeight(Collections.max(user.getDailyWeights().keySet()));
        int currentHeight = user.getHeight(Collections.max(user.getHeights().keySet()));
        double initialBMI = calcBMI(initialWeight, initialHeight);
        double currentBMI = calcBMI(currentWeight, currentHeight);
        double percentChange = (currentBMI - initialBMI)/initialBMI * 100;
        // This rounds the percent change to one decimal place
        double roundedPercentChange = Math.round(percentChange * 10) / 10.0;
        return roundedPercentChange;
    }
    

}

