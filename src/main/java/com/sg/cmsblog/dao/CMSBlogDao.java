/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.dao;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.model.Tag;
import java.util.List;

/**
 *
 * @author chris
 */
public interface CMSBlogDao {
    
    public Category addCategory(Category category);
    
    public void deleteCategory(int categoryId);
    
    public void updateCategory(Category category);
    
    public Category getCategoryById(int id);
    
    public List<Category> getCategoriesLimitByNum(int num);
    
    public List<Category> getAllCategories();
    
    public Image addImage(Image image);
    
    public void deleteImage(int imageId);
    
    public void updateImage(Image image);
    
    public Image getImageById(int id);
    
    public List<Image> getImagesLimitByNum(int num);
    
    public List<Image> getAllImages();
    
    public Navigation addNavigation(Navigation navigation);
    
    public void deleteNavigation(int navigationId);
    
    public void updateNavigation(Navigation navigation);
    
    public Navigation getNavigationById(int id);
    
    public List<Navigation> getNavigationsLimitByNum(int num);
    
    public List<Navigation> getAllNavigations();
    
    public Page addPage(Page page);
    
    public void deletePage(int pageId);
    
    public void updatePage(Page page);
    
    public Page getPageById(int id);
    
    public Page getPublishedPageById(int id);
    
    public List<Page> getPublishedPagesByNavigationId(int navigationId);
    
    public List<Page> getPagesLimitByNum(int num);
    
    public List<Page> getAllPages();
    
    public Post addPost(Post post);
    
    public void deletePost(int postId);
    
    public void updatePost(Post post);
    
    public Post getPostById(int id);
    
    public Post getPublishedPostById(int id);
    
    public List<Post> getPublishedPostsByCategoryId(int categoryId);
    
    public List<Post> getPublishedPostsByCategoryId(int categoryId, int num);
    
    public List<Post> getPublishedPostsByTagId(int tagId);
    
    public List<Post> getPublishedPosts();
    
    public List<Post> getPublishedPosts(int num);
    
    public List<Post> getDraftPostsByUserId(int userId);
    
    public List<Post> getDraftPostsByUserId(int userId, int num);
    
    public List<Post> getPostsByCategoryId(int categoryId);
    
    public List<Post> getPostsLimitByNum(int num);
    
    public List<Post> getAllPosts();
    
    public Status addStatus(Status status);
    
    public void deleteStatus(int statusId);
    
    public void updateStatus(Status status);
    
    public Status getStatusById(int id);
    
    public List<Status> getAllStatuses();
    
    public Tag addTag(Tag tag);
    
    public void deleteTag(int tagId);
    
    public void updateTag(Tag tag);
    
    public Tag getTagById(int id);
    
    public List<Tag> getTagsLimitByNum(int num);
    
    public List<Tag> getAllTags();
}
