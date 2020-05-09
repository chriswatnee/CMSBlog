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
public class PageDataValidationException extends Exception {
    
    public PageDataValidationException(String message) {
        super(message);
    }
    
    public PageDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "Page data validation";
    }
}
