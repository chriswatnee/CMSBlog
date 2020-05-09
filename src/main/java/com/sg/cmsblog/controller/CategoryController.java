/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import com.sg.cmsblog.service.PostHasCategoryException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author chris
 */
@Controller
public class CategoryController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public CategoryController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String displayCategoriesPage(Model model) {
        List<Category> categoryList = service.getAllCategories();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", new Category());

        return "adminCategories";
    }
    
    @RequestMapping(value = "/admin/createCategory", method = RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
  
        if (result.hasErrors()) {
            List<Category> categoryList = service.getAllCategories();

            model.addAttribute("categoryList", categoryList);

            return "adminCategories";
        }

        service.addCategory(category);

        return "redirect:categories";
    }
    
    @RequestMapping(value = "/admin/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        
        try {
            service.deleteCategory(categoryId);
        } catch (PostHasCategoryException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        
        return "redirect:categories";
    }
    
    @RequestMapping(value = "/admin/editCategoryForm", method = RequestMethod.GET)
    public String displayEditCategoryForm(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        Category category = service.getCategoryById(categoryId);
        
        model.addAttribute("category", category);
        
        return "editCategoryForm";
    }
    
    @RequestMapping(value = "/admin/editCategory", method = RequestMethod.POST)
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editCategoryForm";
        }

        service.updateCategory(category);

        return "redirect:categories";
    }
}
