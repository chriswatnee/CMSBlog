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
public class PostHasCategoryException extends Exception {
    
    public PostHasCategoryException(String message) {
        super(message);
    }
    
    public PostHasCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "Post has category";
    }
}
