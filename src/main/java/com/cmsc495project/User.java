/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.util.*;

/**
 *
 * @author Dylan Veraart
 */
public class User {
  private HashMap<Long,Integer> heights;
  private double targetWeight;
  private HashMap<Long,Double> dailyWeights;
  private String username;
  private long birthdate;
  
  private User(){
    heights=new HashMap();
    dailyWeights=new HashMap();
  }
  
  public User(String username){
    this();
    this.username=username;
  }
  
  public void addHeight(Date date, int height){
    heights.put(date.getTime(), height);
  }
  public void setTargetWeight(double weight){
    targetWeight=weight;
  }
  public void addWeight(Date date, double weight){
    dailyWeights.put(date.getTime(), weight);
  }
  public void setUsername(String user){
    username=user;
  }
  public void setBirthday(Date date){
    birthdate=date.getTime();
  }

  
  public HashMap getHeights(){
    return heights;
  }
  public int getHeight(Date date){
    return heights.get(date.getTime());
  }
  public double getTargetWeight(){
    return targetWeight;
  }
  public HashMap getDailyWeights(){
    return dailyWeights;
  }
  public double getWeight(Date date){
    return dailyWeights.get(date.getTime());
  }
  public String getUsername(){
    return username;
  }
  public Date getBirthday(){
    return new Date(birthdate);
  }
  
  public List getSortedHeightKeys(){
    Set<Long> set = heights.keySet();
    List<Date> dates = new ArrayList<>();
    for(Long longdate : set){
      dates.add(new Date(longdate));
    }
    Collections.sort(dates);
    return dates;
  }
  public List getSortedWeightKeys(){
    Set<Long> set = dailyWeights.keySet();
    List<Date> dates = new ArrayList<>();
    for(Long longdate : set){
      dates.add(new Date(longdate));
    }
    Collections.sort(dates);
    return dates;
  }
}
