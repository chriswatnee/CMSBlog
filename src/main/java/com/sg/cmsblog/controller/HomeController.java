/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
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
public class HomeController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public HomeController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        List<Post> postList = service.getPublishedPosts(5);
        List<Tag> tagList = service.getAllTags();
        List<Category> categoryList = service.getAllCategories();

        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("postList", postList);
        model.addAttribute("tagList", tagList);        
        model.addAttribute("categoryList", categoryList);
        
        return "index";
    }
}
