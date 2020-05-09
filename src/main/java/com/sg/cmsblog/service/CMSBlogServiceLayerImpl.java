/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.service;

import com.sg.cmsblog.dao.CMSBlogDao;
import com.sg.cmsblog.dao.UserDao;
import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author chris
 */
public class CMSBlogServiceLayerImpl implements CMSBlogServiceLayer {
    
    private CMSBlogDao dao;
    private UserDao userDao;

    @Inject
    public CMSBlogServiceLayerImpl(CMSBlogDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }
    
    @Override
    public void addCategory(Category category) {
        dao.addCategory(category);
    }

    @Override
    public void deleteCategory(int categoryId) throws PostHasCategoryException {
        // check if post has this category
        List<Post> postList = dao.getPostsByCategoryId(categoryId);
        if (postList.size() > 0) {
            throw new PostHasCategoryException("Error: Unable to delete because a post has this category.");
        }
        
        dao.deleteCategory(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        dao.updateCategory(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return dao.getCategoryById(id);
    }

    @Override
    public List<Category> getCategoriesLimitByNum(int num) {
        return dao.getCategoriesLimitByNum(num);
    }

    @Override
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }

    @Override
    public void addImage(Image image) throws ImageDataValidationException {
        
        validateImageData(image);
        
        dao.addImage(image);
    }

    @Override
    public void deleteImage(int imageId) {
        dao.deleteImage(imageId);
    }

    @Override
    public Image getImageById(int id) {
        return dao.getImageById(id);
    }

    @Override
    public List<Image> getImagesLimitByNum(int num) {
        return dao.getImagesLimitByNum(num);
    }

    @Override
    public List<Image> getAllImages() {
        return dao.getAllImages();
    }

    @Override
    public void updateNavigation(Navigation navigation) {
        dao.updateNavigation(navigation);
    }

    @Override
    public Navigation getNavigationById(int id) {
        return dao.getNavigationById(id);
    }

    @Override
    public List<Navigation> getAllNavigations() {
        return dao.getAllNavigations();
    }

    @Override
    public void addPage(Page page) throws PageDataValidationException {
        
        validatePageData(page);
        
        dao.addPage(page);
    }

    @Override
    public void deletePage(int pageId) {
        dao.deletePage(pageId);
    }

    @Override
    public void updatePage(Page page) throws PageDataValidationException {
        
        validatePageData(page);
        
        dao.updatePage(page);
    }

    @Override
    public Page getPageById(int id) {
        return dao.getPageById(id);
    }
    
    @Override
    public Page getPublishedPageById(int id) {
        return dao.getPublishedPageById(id);
    }

    @Override
    public List<Page> getPublishedPagesByNavigationId(int navigationId) {
        return dao.getPublishedPagesByNavigationId(navigationId);
    }

    @Override
    public List<Page> getPagesLimitByNum(int num) {
        return dao.getPagesLimitByNum(num);
    }

    @Override
    public List<Page> getAllPages() {
        return dao.getAllPages();
    }

    @Override
    public void addPost(Post post, User user) throws PostDataValidationException {
        
        post = updatePostStatusForUser(post, user);
        
        validatePostData(post);
        
        dao.addPost(post);
    }

    @Override
    public void deletePost(int postId, User user) throws PostUserPermissionException {
        
        checkPermission(dao.getPostById(postId), user);
        
        dao.deletePost(postId);
    }

    @Override
    public void updatePost(Post post, User user) throws PostUserPermissionException, PostDataValidationException {
        
        checkPermission(dao.getPostById(post.getPostId()), user);
        
        post = updatePostStatusForUser(post, user);
        
        validatePostData(post);
        
        dao.updatePost(post);
    }

    @Override
    public Post getPostById(int id, User user) throws PostUserPermissionException {
        
        checkPermission(dao.getPostById(id), user);
        
        return dao.getPostById(id);
    }

    @Override
    public Post getPublishedPostById(int id) {
        return dao.getPublishedPostById(id);
    }

    @Override
    public List<Post> getPublishedPostsByCategoryId(int categoryId) {
        return dao.getPublishedPostsByCategoryId(categoryId);
    }
    
    @Override
    public List<Post> getPublishedPostsByCategoryId(int categoryId, int num) {
        return dao.getPublishedPostsByCategoryId(categoryId, num);
    }

    @Override
    public List<Post> getPublishedPostsByTagId(int tagId) {
        return dao.getPublishedPostsByTagId(tagId);
    }

    @Override
    public List<Post> getPublishedPosts() {
        return dao.getPublishedPosts();
    }

    @Override
    public List<Post> getPublishedPosts(int num) {
        return dao.getPublishedPosts(num);
    }

    @Override
    public List<Post> getDraftPostsByUserId(int userId) {
        return dao.getDraftPostsByUserId(userId);
    }

    @Override
    public List<Post> getDraftPostsByUserId(int userId, int num) {
        return dao.getDraftPostsByUserId(userId, num);
    }

    @Override
    public List<Post> getPostsLimitByNum(int num) {
        return dao.getPostsLimitByNum(num);
    }

    @Override
    public List<Post> getAllPosts() {
        return dao.getAllPosts();
    }

    @Override
    public Status getStatusById(int id) {
        return dao.getStatusById(id);
    }

    @Override
    public List<Status> getAllStatuses() {
        return dao.getAllStatuses();
    }

    @Override
    public void addTag(Tag tag) {
        dao.addTag(tag);
    }

    @Override
    public void deleteTag(int tagId) {
        dao.deleteTag(tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        dao.updateTag(tag);
    }

    @Override
    public Tag getTagById(int id) {
        return dao.getTagById(id);
    }

    @Override
    public List<Tag> getTagsLimitByNum(int num) {
        return dao.getTagsLimitByNum(num);
    }

    @Override
    public List<Tag> getAllTags() {
        return dao.getAllTags();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUsersLimitByNum(int num) {
        return userDao.getUsersLimitByNum(num);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    private void validatePageData(Page page) throws PageDataValidationException {
        if (page.getTitle() == null
                || page.getTitle().trim().length() == 0
                || page.getTitle().trim().length() > 50
                || page.getContent() == null
                || page.getContent().trim().length() == 0
                || page.getStatus() == null) {
            
            throw new PageDataValidationException("Error: Page data not valid.");
        }
    }
    
    private void validateImageData(Image image) throws ImageDataValidationException {
        if (image.getName() == null
                || image.getName().trim().length() == 0
                || image.getName().trim().length() > 50) {
            
            throw new ImageDataValidationException("Error: Image filename not valid.");
        }
    }
    
    private Boolean hasAdminRole(User user) {
        return user.getAuthorities().stream()
                .anyMatch(r -> r.equals("ROLE_ADMIN"));
    }
    
    private Post updatePostStatusForUser(Post post, User user) {
        // If non-admin role then automatically set post to draft
        if (!hasAdminRole(user)) {
            Status status = dao.getStatusById(1);
            post.setStatus(status);
        }
        
        return post;
    }
    
    private void validatePostData(Post post) throws PostDataValidationException {
        if (post.getTitle() == null
                || post.getTitle().trim().length() == 0
                || post.getTitle().trim().length() > 50
                || post.getContent() == null
                || post.getContent().trim().length() == 0
                || post.getExcerpt() == null
                || post.getExcerpt().trim().length() == 0
                || post.getCreationDate() == null
                || post.getLiveDate() == null
                || post.getCategory() == null
                || post.getStatus() == null) {
            
            throw new PostDataValidationException("Error: Post data not valid.");
        }
    }
    
    private void checkPermission(Post post, User user) throws PostUserPermissionException {
        if (post != null && post.getStatus() != null) {
            String postStatusName = post.getStatus().getName();

            if (!hasAdminRole(user) && (post.getUserId() != user.getId() || postStatusName.equals("Publish"))) {
                throw new PostUserPermissionException("Error: Unable to access post because permission is denied.");
            }
        }
    }
}
