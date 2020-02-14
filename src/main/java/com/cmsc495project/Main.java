/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
/**
 *
 * @author Dylan Veraart
 */
public class Main {

  /*public static User getUser(String username){
    
  }*/
  public static void main(String args[]) {
    
    Gson gson = new Gson();
    Users users = new Users();
    User user = new User("Tom");
    User user2 = new User("John");
    try {
      users.add(user);
      users.add(user2);
    } catch (DuplicateUserException ex) {
      
    } catch (IOException ex) {
      
    }
    Date now = new Date();
    user.addHeight(now, 300);
    user.addWeight(now, 2);
    user.setTargetWeight(4);
    user.setTargetWeight(7);
    try {
      users.updateUser(user);
    } catch (Exception ex) {
      
    }
    try {
      
      System.out.println(gson.toJson(ReadWriteJSON.readUsers()));
      System.out.println(gson.toJson(ReadWriteJSON.readUser("Tom")));
    } catch (FileNotFoundException ex) {
      
    } catch (IOException ex) {
      
    } catch (Exception ex) {
      
    }

  }
}


