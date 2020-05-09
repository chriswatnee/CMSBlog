/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.dao;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.model.Tag;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author chris
 */
public class CMSBlogDaoStubImpl implements CMSBlogDao {
    
    private Category onlyCategory;
    private List<Category> categoryList = new ArrayList<>();
    private Image onlyImage;
    private List<Image> imageList = new ArrayList<>();
    private Navigation onlyNavigation;
    private List<Navigation> navigationList = new ArrayList<>();
    private Page draftPage;
    private Page publishPage;
    private List<Page> publishPageList = new ArrayList<>();
    private List<Page> pageList = new ArrayList<>();
    private Post draftPost;
    private Post publishPost;
    private List<Post> draftPostList = new ArrayList<>();
    private List<Post> publishPostList = new ArrayList<>();
    private List<Post> postList = new ArrayList<>();
    private Status draftStatus;
    private Status publishStatus;
    private List<Status> statusList = new ArrayList<>();
    private Tag onlyTag;
    private List<Tag> tagList = new ArrayList<>();
    
    public CMSBlogDaoStubImpl() {
        onlyCategory = new Category();
        onlyCategory.setCategoryId(1);
        onlyCategory.setName("Tutorial");
        
        categoryList.add(onlyCategory);
        
        onlyImage = new Image();
        onlyImage.setImageId(1);
        onlyImage.setName("image.jpg");
        
        imageList.add(onlyImage);
                
        draftStatus = new Status();
        draftStatus.setStatusId(1);
        draftStatus.setName("Draft");
        
        publishStatus = new Status();
        publishStatus.setStatusId(2);
        publishStatus.setName("Publish");
        
        statusList.add(draftStatus);
        statusList.add(publishStatus);
        
        draftPage = new Page();
        draftPage.setPageId(1);
        draftPage.setTitle(PLACEHOLDER_TITLE);
        draftPage.setContent(PLACEHOLDER_CONTENT);
        draftPage.setStatus(draftStatus);
        
        publishPage = new Page();
        publishPage.setPageId(2);
        publishPage.setTitle(PLACEHOLDER_TITLE);
        publishPage.setContent(PLACEHOLDER_CONTENT);
        publishPage.setStatus(publishStatus);
        
        publishPageList.add(publishPage);
        
        pageList.add(draftPage);
        pageList.add(publishPage);
        
        onlyNavigation = new Navigation();
        onlyNavigation.setNavigationId(1);
        onlyNavigation.setSection("Navbar");
        onlyNavigation.setPages(pageList);
        
        navigationList.add(onlyNavigation);
        
        onlyTag = new Tag();
        onlyTag.setTagId(1);
        onlyTag.setName("Java");
        
        tagList.add(onlyTag);
        
        draftPost = new Post();
        draftPost.setPostId(1);
        draftPost.setTitle(PLACEHOLDER_TITLE);
        draftPost.setContent(PLACEHOLDER_CONTENT);
        draftPost.setExcerpt(PLACEHOLDER_EXCERPT);
        draftPost.setCreationDate(LocalDate.parse("2020-04-04"));
        draftPost.setLiveDate(LocalDate.parse("2020-04-04"));
        draftPost.setExpirationDate(LocalDate.parse("2030-04-04"));
        draftPost.setUserId(1);
        draftPost.setCategory(onlyCategory);
        draftPost.setStatus(draftStatus);
        draftPost.setTags(tagList);
        
        publishPost = new Post();
        publishPost.setPostId(2);
        publishPost.setTitle(PLACEHOLDER_TITLE);
        publishPost.setContent(PLACEHOLDER_CONTENT);
        publishPost.setExcerpt(PLACEHOLDER_EXCERPT);
        publishPost.setCreationDate(LocalDate.parse("2020-04-04"));
        publishPost.setLiveDate(LocalDate.parse("2020-04-04"));
        publishPost.setExpirationDate(LocalDate.parse("2030-04-04"));
        publishPost.setUserId(1);
        publishPost.setCategory(onlyCategory);
        publishPost.setStatus(publishStatus);
        publishPost.setTags(tagList);
        
        draftPostList.add(draftPost);
        
        publishPostList.add(publishPost);
        
        postList.add(draftPost);
        postList.add(publishPost);
    }
    
    private static final String PLACEHOLDER_TITLE = "Lorem Ipsum";
    private static final String PLACEHOLDER_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing "
            + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
            + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
            + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
            + "consequat. Duis aute irure dolor in reprehenderit in "
            + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in "
            + "culpa qui officia deserunt mollit anim id est laborum.";
    private static final String PLACEHOLDER_EXCERPT = "Lorem ipsum dolor sit amet, consectetur adipiscing "
            + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
            + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
            + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
            + "consequat.";

    @Override
    public Category addCategory(Category category) {
        return onlyCategory;
    }

    @Override
    public void deleteCategory(int categoryId) {
        // do nothing...
    }

    @Override
    public void updateCategory(Category category) {
        // do nothing...
    }

    @Override
    public Category getCategoryById(int id) {
        if (id == onlyCategory.getCategoryId()) {
            return onlyCategory;
        } else {
            return null;
        }
    }

    @Override
    public List<Category> getCategoriesLimitByNum(int num) {
        if (num == categoryList.size()) {
            return categoryList;
        } else {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryList;
    }

    @Override
    public Image addImage(Image image) {
        return onlyImage;
    }

    @Override
    public void deleteImage(int imageId) {
        // do nothing...
    }

    @Override
    public void updateImage(Image image) {
        // do nothing...
    }

    @Override
    public Image getImageById(int id) {
        if (id == onlyImage.getImageId()) {
            return onlyImage;
        } else {
            return null;
        }
    }

    @Override
    public List<Image> getImagesLimitByNum(int num) {
        if (num == imageList.size()) {
            return imageList;
        } else {
            return null;
        }
    }

    @Override
    public List<Image> getAllImages() {
        return imageList;
    }

    @Override
    public Navigation addNavigation(Navigation navigation) {
        return onlyNavigation;
    }

    @Override
    public void deleteNavigation(int navigationId) {
        // do nothing...
    }

    @Override
    public void updateNavigation(Navigation navigation) {
        // do nothing...
    }

    @Override
    public Navigation getNavigationById(int id) {
        if (id == onlyNavigation.getNavigationId()) {
            return onlyNavigation;
        } else {
            return null;
        }
    }

    @Override
    public List<Navigation> getNavigationsLimitByNum(int num) {
        if (num == navigationList.size()) {
            return navigationList;
        } else {
            return null;
        }
    }

    @Override
    public List<Navigation> getAllNavigations() {
        return navigationList;
    }

    @Override
    public Page addPage(Page page) {
        return draftPage;
    }

    @Override
    public void deletePage(int pageId) {
        // do nothing...
    }

    @Override
    public void updatePage(Page page) {
        // do nothing...
    }

    @Override
    public Page getPageById(int id) {
        if (id == draftPage.getPageId()) {
            return draftPage;
        } else {
            return null;
        }
    }

    @Override
    public Page getPublishedPageById(int id) {
        if (id == publishPage.getPageId()) {
            return publishPage;
        } else {
            return null;
        }
    }

    @Override
    public List<Page> getPublishedPagesByNavigationId(int navigationId) {
        return publishPageList;
    }

    @Override
    public List<Page> getPagesLimitByNum(int num) {
        if (num == pageList.size()) {
            return pageList;
        } else {
            return null;
        }
    }

    @Override
    public List<Page> getAllPages() {
        return pageList;
    }

    @Override
    public Post addPost(Post post) {
        return draftPost;
    }

    @Override
    public void deletePost(int postId) {
        // do nothing...
    }

    @Override
    public void updatePost(Post post) {
        // do nothing...
    }

    @Override
    public Post getPostById(int id) {
        if (id == draftPost.getPostId()) {
            return draftPost;
        } else if (id == publishPost.getPostId()) {
            return publishPost;
        } else {
            return null;
        }
    }

    @Override
    public Post getPublishedPostById(int id) {
        if (id == publishPost.getPostId()) {
            return publishPost;
        } else {
            return null;
        }
    }

    @Override
    public List<Post> getPublishedPostsByCategoryId(int categoryId) {
        return publishPostList;
    }

    @Override
    public List<Post> getPublishedPostsByCategoryId(int categoryId, int num) {
        return publishPostList;
    }

    @Override
    public List<Post> getPublishedPostsByTagId(int tagId) {
        return publishPostList;
    }

    @Override
    public List<Post> getPublishedPosts() {
        return publishPostList;
    }

    @Override
    public List<Post> getPublishedPosts(int num) {
        return publishPostList;
    }

    @Override
    public List<Post> getDraftPostsByUserId(int userId) {
        return draftPostList;
    }

    @Override
    public List<Post> getDraftPostsByUserId(int userId, int num) {
        return draftPostList;
    }

    @Override
    public List<Post> getPostsByCategoryId(int categoryId) {
        if (categoryId == onlyCategory.getCategoryId()) {
            return postList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Post> getPostsLimitByNum(int num) {
        return postList;
    }

    @Override
    public List<Post> getAllPosts() {
        return postList;
    }

    @Override
    public Status addStatus(Status status) {
        return draftStatus;
    }

    @Override
    public void deleteStatus(int statusId) {
        // do nothing...
    }

    @Override
    public void updateStatus(Status status) {
        // do nothing...
    }

    @Override
    public Status getStatusById(int id) {
        if (id == draftStatus.getStatusId()) {
            return draftStatus;
        } else if (id == publishStatus.getStatusId()) {
            return publishStatus;
        } else {
            return null;
        }
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusList;
    }

    @Override
    public Tag addTag(Tag tag) {
        return onlyTag;
    }

    @Override
    public void deleteTag(int tagId) {
        // do nothing...
    }

    @Override
    public void updateTag(Tag tag) {
        // do nothing...
    }

    @Override
    public Tag getTagById(int id) {
        if (id == onlyTag.getTagId()) {
            return onlyTag;
        } else {
            return null;
        }
    }

    @Override
    public List<Tag> getTagsLimitByNum(int num) {
        if (num == tagList.size()) {
            return tagList;
        } else {
            return null;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return tagList;
    }
}
