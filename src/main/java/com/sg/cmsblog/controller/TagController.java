/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author chris
 */
@Controller
public class TagController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public TagController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/tags", method = RequestMethod.GET)
    public String displayTagsPage(Model model) {
        List<Tag> tagList = service.getAllTags();

        model.addAttribute("tagList", tagList);
        model.addAttribute("tag", new Tag());

        return "adminTags";
    }
    
    @RequestMapping(value = "/admin/createTag", method = RequestMethod.POST)
    public String createTag(@Valid @ModelAttribute("tag") Tag tag, BindingResult result, Model model) {
  
        if (result.hasErrors()) {
            List<Tag> tagList = service.getAllTags();

            model.addAttribute("tagList", tagList);

            return "adminTags";
        }

        service.addTag(tag);

        return "redirect:tags";
    }
    
    @RequestMapping(value = "/admin/deleteTag", method = RequestMethod.GET)
    public String deleteTag(HttpServletRequest request) {
        String tagIdParameter = request.getParameter("tagId");
        int tagId = Integer.parseInt(tagIdParameter);
        
        service.deleteTag(tagId);
        
        return "redirect:tags";
    }
    
    @RequestMapping(value = "/admin/editTagForm", method = RequestMethod.GET)
    public String displayEditTagForm(HttpServletRequest request, Model model) {
        String tagIdParameter = request.getParameter("tagId");
        int tagId = Integer.parseInt(tagIdParameter);
        Tag tag = service.getTagById(tagId);
        
        model.addAttribute("tag", tag);
        
        return "editTagForm";
    }
    
    @RequestMapping(value = "/admin/editTag", method = RequestMethod.POST)
    public String editTag(@Valid @ModelAttribute("tag") Tag tag, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editTagForm";
        }

        service.updateTag(tag);

        return "redirect:tags";
    }
}
