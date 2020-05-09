/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.service;

/**
 *
 * @author chris
 */
public class NavigationDataValidationException extends Exception {
    
    public NavigationDataValidationException(String message) {
        super(message);
    }
    
    public NavigationDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "Navigation data validation";
    }
}
