/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import com.sg.cmsblog.service.ImageDataValidationException;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author chris
 */
@Controller
public class ImageController {
    
    public static final String IMAGE_FOLDER = "images/";
    private CMSBlogServiceLayer service;
    
    @Inject
    public ImageController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/images", method = RequestMethod.GET)
    public String displayImagesPage(Model model) {
        List<Image> imageList = service.getAllImages();

        model.addAttribute("imageList", imageList);
        model.addAttribute("image", new Image());

        return "adminImages";
    }
    
    @RequestMapping(value = "/admin/createImage", method = RequestMethod.POST)
    public String createImage(HttpServletRequest request, 
            @RequestParam("name") MultipartFile imageFile, Model model) {
        Image image = new Image();
        
        if (!imageFile.isEmpty()) {
            try {
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + IMAGE_FOLDER;
                File dir = new File(savePath);
                // if IMAGE_FOLDER directory is not there create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filename = imageFile.getOriginalFilename();

                imageFile.transferTo(new File(savePath + filename));

                image.setName(filename);
            } catch (Exception e) {
                model.addAttribute("message", "File upload failed: " + e.getMessage());
                return "adminImages";
            }
        }
        
        try {
            service.addImage(image);
        } catch (ImageDataValidationException e) {
            model.addAttribute("message", e.getMessage());
            return "adminImages";
        }

        return "redirect:images";
    }
    
    @RequestMapping(value = "/admin/deleteImage", method = RequestMethod.GET)
    public String deleteImage(HttpServletRequest request, Model model) {
        String imageIdParameter = request.getParameter("imageId");
        int imageId = Integer.parseInt(imageIdParameter);
        
        try {
            String savePath = request
                    .getSession()
                    .getServletContext()
                    .getRealPath("/") + IMAGE_FOLDER;
            String filename = service.getImageById(imageId).getName();
            File imageFile = new File(savePath + filename);
            imageFile.delete();
        } catch (Exception e) {
            model.addAttribute("message", "File delete failed: " + e.getMessage());
            return "adminImages";
        }
        
        service.deleteImage(imageId);
        
        return "redirect:images";
    }
}
