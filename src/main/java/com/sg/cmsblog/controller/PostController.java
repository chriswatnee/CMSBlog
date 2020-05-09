/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.controller;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.model.User;
import com.sg.cmsblog.service.CMSBlogServiceLayer;
import com.sg.cmsblog.service.PostDataValidationException;
import com.sg.cmsblog.service.PostUserPermissionException;
import java.security.Principal;
import java.time.LocalDate;
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
public class PostController {
    
    private CMSBlogServiceLayer service;
    
    @Inject
    public PostController(CMSBlogServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String displayPostsPage(Model model) {
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        List<Post> postList = service.getPublishedPosts();

        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("postList", postList);

        return "posts";
    }
    
    @RequestMapping(value = "/postsByCategory", method = RequestMethod.GET)
    public String displayPostsByCategoryPage(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        Category category = service.getCategoryById(categoryId);
        List<Post> postList = service.getPublishedPostsByCategoryId(categoryId);

        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("category", category);
        model.addAttribute("postList", postList);

        return "postsByCategory";
    }
    
    @RequestMapping(value = "/postsByTag", method = RequestMethod.GET)
    public String displayPostsByTagPage(HttpServletRequest request, Model model) {
        String tagIdParameter = request.getParameter("tagId");
        int tagId = Integer.parseInt(tagIdParameter);
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        Tag tag = service.getTagById(tagId);
        List<Post> postList = service.getPublishedPostsByTagId(tagId);

        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("tag", tag);
        model.addAttribute("postList", postList);

        return "postsByTag";
    }
    
    @RequestMapping(value = "/admin/posts", method = RequestMethod.GET)
    public String displayAdminPostsPage(Model model) {
        String referer = "posts";
        List<Post> postList = service.getAllPosts();

        model.addAttribute("referer", referer);
        model.addAttribute("postList", postList);

        return "adminPosts";
    }
    
    @RequestMapping(value = "/admin/myDraftPosts", method = RequestMethod.GET)
    public String displayAdminMyDraftPostsPage(Principal principal, Model model) {
        String referer = "myDraftPosts";
        String username = principal.getName();
        User user = service.getUser(username);
        List<Post> postList = service.getDraftPostsByUserId(user.getId());

        model.addAttribute("referer", referer);
        model.addAttribute("postList", postList);

        return "adminPosts";
    }
    
    @RequestMapping(value = "/admin/createPostForm", method = RequestMethod.GET)
    public String displayCreatePostForm(HttpServletRequest request, Model model) {
        List<Image> imageList = service.getAllImages();
        LocalDate currentDate = LocalDate.now();
        List<Category> categoryList = service.getAllCategories();
        List<Status> statusList = service.getAllStatuses();
        List<Tag> tagList = service.getAllTags();
        String refererParameter = request.getParameter("referer");

        model.addAttribute("imageList", imageList);
        model.addAttribute("currentDate", currentDate);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("statusList", statusList);
        model.addAttribute("tagList", tagList);
        model.addAttribute("referer", refererParameter);
        
        return "createPostForm";
    }
    
    @RequestMapping(value = "/admin/createPost", method = RequestMethod.POST)
    public String createPost(HttpServletRequest request, Principal principal, RedirectAttributes redirectAttributes) {
        Post post = new Post();
        post.setTitle(request.getParameter("title"));
        post.setContent(request.getParameter("content"));
        post.setExcerpt(request.getParameter("excerpt"));
        // Get and set dates
        post.setCreationDate(LocalDate.now());
        String liveDateParameter = request.getParameter("liveDate");
        if (!liveDateParameter.isEmpty()) {
            LocalDate liveDate = LocalDate.parse(liveDateParameter);
            post.setLiveDate(liveDate);
        }
        String expirationDateParameter = request.getParameter("expirationDate");
        if (!expirationDateParameter.isEmpty()) {
            LocalDate expirationDate = LocalDate.parse(expirationDateParameter);
            post.setExpirationDate(expirationDate);
        }
        // Get and set userId
        String username = principal.getName();
        User user = service.getUser(username);
        post.setUserId(user.getId());
        // Get and set category
        String categoryIdParameter = request.getParameter("category");
        if (!categoryIdParameter.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdParameter);
            Category category = service.getCategoryById(categoryId);
            post.setCategory(category);
        }
        // Get and set status
        String statusIdParameter = request.getParameter("status");
        if (statusIdParameter != null) {
            if (!statusIdParameter.isEmpty()) {
                int statusId = Integer.parseInt(statusIdParameter);
                Status status = service.getStatusById(statusId);
                post.setStatus(status);
            }
        }
        // Convert list of tag IDs to list of tags
        String[] tagsParameter = request.getParameterValues("tags");
        List<Tag> tagList = new ArrayList<>();
        if (tagsParameter != null && tagsParameter.length > 0) {
            for (String currentTag : tagsParameter) {
                int tagId = Integer.parseInt(currentTag);
                tagList.add(service.getTagById(tagId));
            }
        }
        post.setTags(tagList);
        String refererParameter = request.getParameter("referer");
        
        try {
            service.addPost(post, user);
        } catch (PostDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:createPostForm?referer=" + refererParameter;
        }
        
        return "redirect:" + refererParameter;
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String displayPostDetails(HttpServletRequest request, Model model) {
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        Post post = service.getPublishedPostById(postId);
        List<Category> categoryList = service.getAllCategories();
        if (post != null) {
            List<Post> postList = service.getPublishedPostsByCategoryId(post.getCategory().getCategoryId(), 5);
            model.addAttribute("postList", postList);
        }
        User author = service.getUserById(post.getUserId());
        
        model.addAttribute("navbarPageList", navbarPageList);
        model.addAttribute("footerPageList", footerPageList);
        model.addAttribute("post", post);
        model.addAttribute("author", author);
        model.addAttribute("categoryList", categoryList);
        
        return "post";
    }
    
    @RequestMapping(value = "/admin/post", method = RequestMethod.GET)
    public String displayAdminPostDetails(HttpServletRequest request, Principal principal, Model model, RedirectAttributes redirectAttributes) {
        List<Page> navbarPageList = service.getPublishedPagesByNavigationId(1);
        List<Page> footerPageList = service.getPublishedPagesByNavigationId(2);
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        String username = principal.getName();
        User user = service.getUser(username);
        String refererParameter = request.getParameter("referer");
        
        try {
            Post post = service.getPostById(postId, user);
            List<Category> categoryList = service.getAllCategories();
            List<Post> postList = service.getPublishedPostsByCategoryId(post.getCategory().getCategoryId(), 5);
            model.addAttribute("postList", postList);
            User author = service.getUserById(post.getUserId());

            model.addAttribute("navbarPageList", navbarPageList);
            model.addAttribute("footerPageList", footerPageList);
            model.addAttribute("post", post);
            model.addAttribute("author", author);
            model.addAttribute("categoryList", categoryList);
        } catch (PostUserPermissionException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:" + refererParameter;
        }
        
        return "post";
    }
    
    @RequestMapping(value = "/admin/deletePost", method = RequestMethod.GET)
    public String deletePost(HttpServletRequest request, Principal principal, RedirectAttributes redirectAttributes) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        String username = principal.getName();
        User user = service.getUser(username);
        String refererParameter = request.getParameter("referer");
        
        try {
            service.deletePost(postId, user);
        } catch (PostUserPermissionException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:" + refererParameter;
        }
        
        return "redirect:" + refererParameter;
    }
    
    @RequestMapping(value = "/admin/editPostForm", method = RequestMethod.GET)
    public String displayEditPostForm(HttpServletRequest request, Principal principal, Model model, RedirectAttributes redirectAttributes) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        String username = principal.getName();
        User user = service.getUser(username);
        String refererParameter = request.getParameter("referer");
        
        try {
            Post post = service.getPostById(postId, user);
            List<Image> imageList = service.getAllImages();
            List<Category> categoryList = service.getAllCategories();
            List<Status> statusList = service.getAllStatuses();
            List<Tag> tagList = service.getAllTags();

            model.addAttribute("post", post);
            model.addAttribute("imageList", imageList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("statusList", statusList);
            model.addAttribute("tagList", tagList);
            model.addAttribute("referer", refererParameter);
        } catch (PostUserPermissionException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:" + refererParameter;
        }
        
        return "editPostForm";
    }
    
    @RequestMapping(value = "/admin/editPost", method = RequestMethod.POST)
    public String editPost(HttpServletRequest request, Principal principal, RedirectAttributes redirectAttributes) {
        Post post = new Post();
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        post.setPostId(postId);
        post.setTitle(request.getParameter("title"));
        post.setContent(request.getParameter("content"));
        post.setExcerpt(request.getParameter("excerpt"));
        String username = principal.getName();
        User user = service.getUser(username);
        String refererParameter = request.getParameter("referer");
        
        try {
            Post currentPost = service.getPostById(postId, user);
            post.setCreationDate(currentPost.getCreationDate());
            post.setUserId(currentPost.getUserId());
        } catch (PostUserPermissionException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:" + refererParameter;
        }
        
        // Get and set live and expiration dates
        String liveDateParameter = request.getParameter("liveDate");
        if (!liveDateParameter.isEmpty()) {
            LocalDate liveDate = LocalDate.parse(liveDateParameter);
            post.setLiveDate(liveDate);
        }
        String expirationDateParameter = request.getParameter("expirationDate");
        if (!expirationDateParameter.isEmpty()) {
            LocalDate expirationDate = LocalDate.parse(expirationDateParameter);
            post.setExpirationDate(expirationDate);
        }
        // Get and set category
        String categoryIdParameter = request.getParameter("category");
        if (!categoryIdParameter.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdParameter);
            Category category = service.getCategoryById(categoryId);
            post.setCategory(category);
        }
        // Get and set status
        String statusIdParameter = request.getParameter("status");
        if (statusIdParameter != null) {
            if (!statusIdParameter.isEmpty()) {
                int statusId = Integer.parseInt(statusIdParameter);
                Status status = service.getStatusById(statusId);
                post.setStatus(status);
            }
        }
        // Convert list of tag IDs to list of tags
        String[] tagsParameter = request.getParameterValues("tags");
        List<Tag> tagList = new ArrayList<>();
        if (tagsParameter != null && tagsParameter.length > 0) {
            for (String currentTag : tagsParameter) {
                int tagId = Integer.parseInt(currentTag);
                tagList.add(service.getTagById(tagId));
            }
        }
        post.setTags(tagList);

        try {
            service.updatePost(post, user);
        } catch (PostUserPermissionException | PostDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:editPostForm?postId=" + postIdParameter + "&referer=" + refererParameter;
        }
        
        return "redirect:" + refererParameter;
    }
}
