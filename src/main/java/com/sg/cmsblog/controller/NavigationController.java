/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import com.sg.cmsblog.service.NavigationDataValidationException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author chris
 */
@Controller
public class NavigationController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public NavigationController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/editNavigationForm", method = RequestMethod.GET)
    public String displayEditNavigationForm(HttpServletRequest request, Model model) {
        String navigationIdParameter = request.getParameter("navigationId");
        int navigationId = Integer.parseInt(navigationIdParameter);
        Navigation navigation = service.getNavigationById(navigationId);
        List<Page> pageList = service.getAllPages();
        
        model.addAttribute("navigation", navigation);
        model.addAttribute("pageList", pageList);
        
        return "editNavigationForm";
    }
    
    @RequestMapping(value = "/admin/editNavigation", method = RequestMethod.POST)
    public String editNavigation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Navigation navigation = new Navigation();
        String navigationIdParameter = request.getParameter("navigationId");
        int navigationId = Integer.parseInt(navigationIdParameter);
        navigation.setNavigationId(navigationId);
        // Set section to current Navigation
        Navigation currentNavigation = service.getNavigationById(navigationId);
        navigation.setSection(currentNavigation.getSection());
        // Convert list of Page IDs to list of Pages
        String[] pagesParameter = request.getParameterValues("pages");
        List<Page> pageList = new ArrayList<>();
        if (pagesParameter != null && pagesParameter.length > 0) {
            for (String currentPage : pagesParameter) {
                int pageId = Integer.parseInt(currentPage);
                pageList.add(service.getPageById(pageId));
            }
        }
        navigation.setPages(pageList);

        service.updateNavigation(navigation);

        return "redirect:../admin";
    }
}
