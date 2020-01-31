/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
      
    }
    Date now = new Date();
    user.addHeight(now, 300);
    user.addWeight(now, 2);
    user.setTargetWeight(4);
    user.setTargetWeight(7);
    try {
      
      System.out.println(gson.toJson(ReadWriteJSON.readUsers()));
    } catch (FileNotFoundException ex) {
      
    } catch (IOException ex) {
      
    }

  }
}


