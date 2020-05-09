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
public class PostDataValidationException extends Exception {
    
    public PostDataValidationException(String message) {
        super(message);
    }
    
    public PostDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "Post data validation";
    }
}
