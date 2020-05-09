/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.model.User;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author chris
 */
@Controller
public class AdminController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public AdminController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayAdminPage(Principal principal, Model model) {
        String username = principal.getName();
        User user = service.getUser(username);
        List<Post> myDraftPostList = service.getDraftPostsByUserId(user.getId(), 5);
        List<Post> postList = service.getPostsLimitByNum(5);   
        List<Page> pageList = service.getPagesLimitByNum(5);
        List<Image> imageList = service.getImagesLimitByNum(5);
        List<Navigation> navigationList = service.getAllNavigations();
        List<Category> categoryList = service.getCategoriesLimitByNum(5);
        List<Tag> tagList = service.getTagsLimitByNum(5);
        List<User> userList = service.getUsersLimitByNum(5);

        model.addAttribute("myDraftPostList", myDraftPostList);
        model.addAttribute("postList", postList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("imageList", imageList);
        model.addAttribute("navigationList", navigationList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);
        model.addAttribute("userList", userList);
        
        return "admin";
    }
}
