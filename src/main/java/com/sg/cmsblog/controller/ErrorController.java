/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import java.text.MessageFormat;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author chris
 */
@Controller
class ErrorController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public ErrorController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/error")
    public String customError(HttpServletRequest request, HttpServletResponse response, Model model) {
        
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = null;
        exceptionMessage = httpStatus.getReasonPhrase();
        
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        String message = MessageFormat.format("{0} returned for {1}: {2}",
                statusCode, requestUri, exceptionMessage);
        
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);

        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("errorMessage", message);
        
        return "customError";
    }
}
