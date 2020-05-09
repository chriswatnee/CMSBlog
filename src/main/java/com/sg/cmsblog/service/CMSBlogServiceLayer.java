/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.service;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.model.User;
import java.util.List;

/**
 *
 * @author chris
 */
public interface CMSBlogServiceLayer {
    
    public void addCategory(Category category);
    
    public void deleteCategory(int categoryId) throws PostHasCategoryException;
    
    public void updateCategory(Category category);
    
    public Category getCategoryById(int id);
    
    public List<Category> getCategoriesLimitByNum(int num);
    
    public List<Category> getAllCategories();
    
    public void addImage(Image image) throws ImageDataValidationException;
    
    public void deleteImage(int imageId);
    
    public Image getImageById(int id);
    
    public List<Image> getImagesLimitByNum(int num);
    
    public List<Image> getAllImages();
    
    public void updateNavigation(Navigation navigation);
    
    public Navigation getNavigationById(int id);
    
    public List<Navigation> getAllNavigations();
    
    public void addPage(Page page) throws PageDataValidationException;
    
    public void deletePage(int pageId);
    
    public void updatePage(Page page) throws PageDataValidationException;
    
    public Page getPageById(int id);
    
    public Page getPublishedPageById(int id);
    
    public List<Page> getPublishedPagesByNavigationId(int navigationId);
    
    public List<Page> getPagesLimitByNum(int num);
    
    public List<Page> getAllPages();
    
    public void addPost(Post post, User user) throws PostDataValidationException;
    
    public void deletePost(int postId, User user) throws PostUserPermissionException;
    
    public void updatePost(Post post, User user) throws PostUserPermissionException, PostDataValidationException;
    
    public Post getPostById(int id, User user) throws PostUserPermissionException;
    
    public Post getPublishedPostById(int id);
    
    public List<Post> getPublishedPostsByCategoryId(int categoryId);
    
    public List<Post> getPublishedPostsByCategoryId(int categoryId, int num);
    
    public List<Post> getPublishedPostsByTagId(int tagId);
    
    public List<Post> getPublishedPosts();
    
    public List<Post> getPublishedPosts(int num);
    
    public List<Post> getDraftPostsByUserId(int userId);
    
    public List<Post> getDraftPostsByUserId(int userId, int num);
    
    public List<Post> getPostsLimitByNum(int num);
    
    public List<Post> getAllPosts();
    
    public Status getStatusById(int id);
    
    public List<Status> getAllStatuses();
    
    public void addTag(Tag tag);
    
    public void deleteTag(int tagId);
    
    public void updateTag(Tag tag);
    
    public Tag getTagById(int id);
    
    public List<Tag> getTagsLimitByNum(int num);
    
    public List<Tag> getAllTags();
    
    public void addUser(User user);
    
    public void deleteUser(String username);
    
    public User getUser(String username);
    
    public User getUserById(int id);
    
    public List<User> getUsersLimitByNum(int num);
    
    public List<User> getAllUsers();
}
