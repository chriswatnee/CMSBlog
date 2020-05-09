/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.User;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author chris
 */
@Controller
public class UserController {

    private CMSBlogServiceLayer service;
    private PasswordEncoder encoder;

    @Inject
    public UserController(CMSBlogServiceLayer service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String displayUserList(Model model) {
        List userList = service.getAllUsers();
        
        model.addAttribute("userList", userList);
        
        return "adminUsers";
    }

    @RequestMapping(value = "/admin/createUserForm", method = RequestMethod.GET)
    public String displayUserForm(Model model) {
        return "createUserForm";
    }

    @RequestMapping(value = "/admin/createUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, @Valid @ModelAttribute("user") User user, BindingResult result) {
        user = new User();
        user.setUsername(request.getParameter("username"));
        String clearPassword = request.getParameter("password");
        String hashPassword = encoder.encode(clearPassword);
        user.setPassword(hashPassword);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.addAuthority("ROLE_USER");
        if (null != request.getParameter("isAdmin")) {
            user.addAuthority("ROLE_ADMIN");
        }
        
        if (result.hasErrors()) {
            return "createUserForm";
        }
        
        service.addUser(user);

        return "redirect:users";
    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("username") String username) {
        
        service.deleteUser(username);
        
        return "redirect:users";
    }
}
