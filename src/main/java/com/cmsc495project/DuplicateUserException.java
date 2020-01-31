/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsc495project;

/**
 *
 * @author Dylan Veraart
 */
public class DuplicateUserException extends Exception {

  public DuplicateUserException(String errorMessage) {
    super(errorMessage);
  }

  public DuplicateUserException() {
    super();
  }
}
