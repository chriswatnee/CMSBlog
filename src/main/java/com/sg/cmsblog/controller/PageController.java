/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import com.sg.cmsblog.service.PageDataValidationException;
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
public class PageController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public PageController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/pages", method = RequestMethod.GET)
    public String displayPagesPage(Model model) {
        List<Page> pageList = service.getAllPages();

        model.addAttribute("pageList", pageList);

        return "adminPages";
    }
    
    @RequestMapping(value = "/admin/createPageForm", method = RequestMethod.GET)
    public String displayCreatePageForm(HttpServletRequest request, Model model) {
        List<Image> imageList = service.getAllImages();
        List<Status> statusList = service.getAllStatuses();

        model.addAttribute("imageList", imageList);
        model.addAttribute("statusList", statusList);
        
        return "createPageForm";
    }
    
    @RequestMapping(value = "/admin/createPage", method = RequestMethod.POST)
    public String createPage(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Page page = new Page();
        page.setTitle(request.getParameter("title"));
        page.setContent(request.getParameter("content"));
        // Get and set status
        String statusIdParameter = request.getParameter("status");
        if (!statusIdParameter.isEmpty()) {
            int statusId = Integer.parseInt(statusIdParameter);
            Status status = service.getStatusById(statusId);
            page.setStatus(status);
        }

        try {
            service.addPage(page);
        } catch (PageDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:createPageForm";
        }

        return "redirect:pages";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String displayPageDetails(HttpServletRequest request, Model model) {
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        String pageIdParameter = request.getParameter("pageId");
        int pageId = Integer.parseInt(pageIdParameter);
        
        Page page = service.getPublishedPageById(pageId);
        
        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("page", page);
        
        return "page";
    }
    
    @RequestMapping(value = "/admin/page", method = RequestMethod.GET)
    public String displayAdminPageDetails(HttpServletRequest request, Model model) {
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        String pageIdParameter = request.getParameter("pageId");
        int pageId = Integer.parseInt(pageIdParameter);
        
        Page page = service.getPageById(pageId);
        
        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("page", page);
        
        return "page";
    }
    
    @RequestMapping(value = "/admin/deletePage", method = RequestMethod.GET)
    public String deletePage(HttpServletRequest request) {
        String pageIdParameter = request.getParameter("pageId");
        int pageId = Integer.parseInt(pageIdParameter);
        
        service.deletePage(pageId);
        
        return "redirect:pages";
    }
    
    @RequestMapping(value = "/admin/editPageForm", method = RequestMethod.GET)
    public String displayEditPageForm(HttpServletRequest request, Model model) {
        String pageIdParameter = request.getParameter("pageId");
        int pageId = Integer.parseInt(pageIdParameter);
        Page page = service.getPageById(pageId);
        List<Image> imageList = service.getAllImages();
        List<Status> statusList = service.getAllStatuses();
        
        model.addAttribute("page", page);
        model.addAttribute("imageList", imageList);
        model.addAttribute("statusList", statusList);
        
        return "editPageForm";
    }
    
    @RequestMapping(value = "/admin/editPage", method = RequestMethod.POST)
    public String editPage(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Page page = new Page();
        String pageIdParameter = request.getParameter("pageId");
        int pageId = Integer.parseInt(pageIdParameter);
        page.setPageId(pageId);
        page.setTitle(request.getParameter("title"));
        page.setContent(request.getParameter("content"));
        // Get and set status
        String statusIdParameter = request.getParameter("status");
        if (!statusIdParameter.isEmpty()) {
            int statusId = Integer.parseInt(statusIdParameter);
            Status status = service.getStatusById(statusId);
            page.setStatus(status);
        }

        try {
            service.updatePage(page);
        } catch (PageDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:editPageForm?pageId=" + pageIdParameter;
        }

        return "redirect:pages";
    }
}
