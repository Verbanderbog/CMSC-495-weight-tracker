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
public class MandatoryFieldException extends Exception {
  public MandatoryFieldException(String errorMessage) {
    super(errorMessage);
  }

  public MandatoryFieldException() {
    super();
  }
}
