/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author Dylan Veraart
 */
public class Users {
  HashSet<String> users;
  
  public Users(){
    users=new HashSet();
  }
  
  public void add(User user) throws DuplicateUserException, IOException{
    if(users.contains(user.getUsername()))
      throw new DuplicateUserException();
    ReadWriteJSON.writeUser(user);
    users.add(user.getUsername());
    ReadWriteJSON.writeUsers(this);
  }
  
  public void remove(String username) throws IOException{
    ReadWriteJSON.deleteUser(username);
    users.remove(username);
    ReadWriteJSON.writeUsers(this);
  }
  public void remove(User user) throws IOException{
    this.remove(user.getUsername());
  }
  
  public boolean contains(String username){
    return users.contains(username);
  }
  
  public Iterator iterator(){
    return users.iterator();
  }
  
  public void updateUser(User user) throws IOException, Exception{
    if(users.contains(user.getUsername()))
      ReadWriteJSON.writeUser(user);
    else
      throw new Exception("No user to update+");
  }
  
}
