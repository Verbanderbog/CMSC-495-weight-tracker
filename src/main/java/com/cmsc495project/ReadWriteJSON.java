/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dylan Veraart
 */
public class ReadWriteJSON {

  public static Users readUsers() throws FileNotFoundException {
    File file = new File(System.getProperty("user.dir") + "\\userFiles\\users.json");
    Gson gson = new Gson();
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      Users users = new Users();
      try {
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        gson.toJson(users, writer);
        writer.flush();
        writer.close();
      } catch (IOException ex) {

      }
      return users;
    }
    FileReader reader = new FileReader(file);
    Users users = gson.fromJson(reader, Users.class);
    try {
      reader.close();
    } catch (IOException ex) {
      
    }
    return users;
  }

  public static void writeUsers(Users users) throws IOException {
    File file = new File(System.getProperty("user.dir") + "\\userFiles\\users.json");
    Gson gson = new Gson();
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      try {
        file.createNewFile();
      } catch (IOException ex) {

      }
    }
    FileWriter writer = new FileWriter(file);
    gson.toJson(users, writer);
    writer.flush();
    writer.close();
  }
}
