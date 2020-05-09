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
public class ImageDataValidationException extends Exception {
    
    public ImageDataValidationException(String message) {
        super(message);
    }
    
    public ImageDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "Image data validation";
    }
}
