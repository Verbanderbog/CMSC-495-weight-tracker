/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Dylan Veraart
 */
public class Users {
  HashMap<String,User> users;
  
  public Users(){
    users=new HashMap();
  }
  
  public void add(User user) throws DuplicateUserException{
    if(users.containsKey(user.getUsername()))
      throw new DuplicateUserException();
    users.put(user.getUsername(), user);
  }
  
  public void remove(String username){
    users.remove(username);
  }
  public void remove(User user){
    users.remove(user.getUsername());
  }
  
  public boolean contains(String username){
    return users.containsKey(username);
  }
  
  public User get(String username){
    return users.get(username);
  }
  
  public Iterator keyIterator(){
    return users.keySet().iterator();
  }
  
  
}
