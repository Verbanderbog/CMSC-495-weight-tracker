/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

/**
 *
 * @author Dylan Veraart
 *
 */
public class Calculator {
    
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
        double initialWeight = user.dailyWeights.get(Collections.min(user.dailyWeights.keySet()));
        int initialHeight = user.heights.get(Collections.min(user.heights.keySet()));
        double currentWeight = user.dailyWeights.get(Collections.max(user.dailyWeights.keySet()));
        int currentHeight = user.heights.get(Collections.max(user.heights.keySet()));
        double initialBMI = calcBMI(initialWeight, initialHeight);
        double currentBMI = calcBMI(currentWeight, currentHeight);
        double percentChange = (currentBMI - initialBMI)/initialBMI * 100;
        // This rounds the percent change to one decimal place
        double roundedPercentChange = Math.round(percentChange * 10) / 10.0;
        return roundedpercentChange;
    }
    
}

