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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chris
 */
public class CMSBlogDaoDbImpl implements CMSBlogDao {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // Category
    private static final String SQL_INSERT_CATEGORY
            = "INSERT INTO Category (`Name`) "
            + "VALUES (?)";

    private static final String SQL_DELETE_CATEGORY
            = "DELETE FROM Category WHERE CategoryID = ?";

    private static final String SQL_UPDATE_CATEGORY
            = "UPDATE Category SET `Name` = ? "
            + "WHERE CategoryID = ?";

    private static final String SQL_SELECT_CATEGORY
            = "SELECT * FROM Category WHERE CategoryID = ?";
    
    private static final String SQL_SELECT_CATEGORY_BY_POST_ID
            = "SELECT c.* "
            + "FROM CATEGORY c "
            + "INNER JOIN Post p ON c.CategoryID = p.CategoryID "
            + "WHERE PostID = ?";
    
    private static final String SQL_SELECT_CATEGORIES_LIMIT_BY_NUM
            = "SELECT * FROM Category "
            + "ORDER BY `Name` "
            + "LIMIT ?";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "SELECT * FROM Category ORDER BY `Name`";
    
    // Image
    private static final String SQL_INSERT_IMAGE
            = "INSERT INTO Image (`Name`) "
            + "VALUES (?)";

    private static final String SQL_DELETE_IMAGE
            = "DELETE FROM Image WHERE ImageID = ?";

    private static final String SQL_UPDATE_IMAGE
            = "UPDATE Image SET `Name` = ? "
            + "WHERE ImageID = ?";

    private static final String SQL_SELECT_IMAGE
            = "SELECT * FROM Image WHERE ImageID = ?";
    
    private static final String SQL_SELECT_IMAGES_LIMIT_BY_NUM
            = "SELECT * FROM Image "
            + "ORDER BY `Name` "
            + "LIMIT ?";

    private static final String SQL_SELECT_ALL_IMAGES
            = "SELECT * FROM Image ORDER BY `Name`";
    
    // Navigation and NavigationPage
    private static final String SQL_INSERT_NAVIGATION
            = "INSERT INTO Navigation (Section) "
            + "VALUES (?)";
    
    private static final String SQL_INSERT_NAVIGATION_PAGE
            = "INSERT INTO NavigationPage "
            + "(NavigationID, PageID) VALUES (?, ?)";

    private static final String SQL_DELETE_NAVIGATION
            = "DELETE FROM Navigation WHERE NavigationID = ?";
    
    private static final String SQL_DELETE_NAVIGATION_PAGE
            = "DELETE FROM NavigationPage WHERE NavigationID = ?";
    
    private static final String SQL_DELETE_NAVIGATION_PAGE_BY_PAGE_ID
            = "DELETE FROM NavigationPage WHERE PageID = ?";

    private static final String SQL_UPDATE_NAVIGATION
            = "UPDATE Navigation SET Section = ? "
            + "WHERE NavigationID = ?";

    private static final String SQL_SELECT_NAVIGATION
            = "SELECT * FROM Navigation WHERE NavigationID = ?";
    
    private static final String SQL_SELECT_NAVIGATIONS_LIMIT_BY_NUM
            = "SELECT * FROM Navigation LIMIT ?";

    private static final String SQL_SELECT_ALL_NAVIGATIONS
            = "SELECT * FROM Navigation";
    
    // Page
    private static final String SQL_INSERT_PAGE
            = "INSERT INTO Page (Title, Content, StatusID) "
            + "VALUES (?, ?, ?)";

    private static final String SQL_DELETE_PAGE
            = "DELETE FROM Page WHERE PageID = ?";

    private static final String SQL_UPDATE_PAGE
            = "UPDATE Page SET Title = ?, Content = ?, StatusID = ? "
            + "WHERE PageID = ?";

    private static final String SQL_SELECT_PAGE
            = "SELECT * FROM Page WHERE PageID = ?";
    
    private static final String SQL_SELECT_PUBLISHED_PAGE
            = "SELECT p.* "
            + "FROM Page p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE PageID = ? AND `Name` = 'Publish'";
    
    private static final String SQL_SELECT_PUBLISHED_PAGES_BY_NAVIGATION_ID
            = "SELECT p.* "
            + "FROM Page p "
            + "INNER JOIN NavigationPage np ON p.PageID = np.PageID "
            + "INNER JOIN Navigation n ON np.NavigationID = n.NavigationID "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE n.NavigationID = ? AND `Name` = 'Publish' "
            + "ORDER BY Title";
    
    private static final String SQL_SELECT_PAGES_BY_NAVIGATION_ID
            = "SELECT p.* "
            + "FROM Page p "
            + "INNER JOIN NavigationPage np ON p.PageID = np.PageID "
            + "INNER JOIN Navigation n ON np.NavigationID = n.NavigationID "
            + "WHERE n.NavigationID = ? "
            + "ORDER BY Title";
    
    private static final String SQL_SELECT_PAGES_LIMIT_BY_NUM
            = "SELECT * FROM Page "
            + "ORDER BY Title "
            + "LIMIT ?";

    private static final String SQL_SELECT_ALL_PAGES
            = "SELECT * FROM Page ORDER BY Title";
    
    // Post and PostTag
    private static final String SQL_INSERT_POST
            = "INSERT INTO Post (Title, Content, Excerpt, CreationDate, "
            + "LiveDate, ExpirationDate, UserID, CategoryID, StatusID) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_INSERT_POST_NO_EXPIRATION_DATE
            = "INSERT INTO Post (Title, Content, Excerpt, CreationDate, "
            + "LiveDate, UserID, CategoryID, StatusID) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_INSERT_POST_TAG
            = "INSERT INTO PostTag "
            + "(PostID, TagID) VALUES (?, ?)";
    
    private static final String SQL_DELETE_POST
            = "DELETE FROM Post WHERE PostID = ?";
    
    private static final String SQL_DELETE_POST_TAG
            = "DELETE FROM PostTag WHERE PostID = ?";
     
    private static final String SQL_DELETE_POST_TAG_BY_TAG_ID
            = "DELETE FROM PostTag WHERE TagID = ?";
    
    private static final String SQL_UPDATE_POST
            = "UPDATE Post SET Title = ?, Content = ?, Excerpt = ?, "
            + "CreationDate = ?, LiveDate = ?, ExpirationDate = ?, "
            + "UserID = ?, CategoryID = ?, StatusID = ? "
            + "WHERE PostID = ?";
    
    private static final String SQL_UPDATE_POST_NO_EXPIRATION_DATE
            = "UPDATE Post SET Title = ?, Content = ?, Excerpt = ?, "
            + "CreationDate = ?, LiveDate = ?, "
            + "UserID = ?, CategoryID = ?, StatusID = ? "
            + "WHERE PostID = ?";
    
    private static final String SQL_REMOVE_POST_CATEGORY
            = "UPDATE Post SET CategoryID = NULL "
            + "WHERE CategoryID = ?";

    private static final String SQL_SELECT_PUBLISHED_POST
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE (PostID = ? AND `Name` = 'Publish' AND LiveDate <= ?) AND "
            + "(ExpirationDate is NULL OR ExpirationDate >= ?) ";
    
    private static final String SQL_SELECT_POST
            = "SELECT * FROM Post WHERE PostID = ?";
    
    private static final String SQL_SELECT_PUBLISHED_POSTS_BY_CATEGORY_ID
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE (CategoryID = ? AND `Name` = 'Publish' AND LiveDate <= ?) AND "
            + "(ExpirationDate is NULL OR ExpirationDate >= ?) "
            + "ORDER BY LiveDate DESC";
    
    private static final String SQL_SELECT_PUBLISHED_POSTS_BY_CATEGORY_ID_LIMIT_BY_NUM
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE (CategoryID = ? AND `Name` = 'Publish' AND LiveDate <= ?) AND "
            + "(ExpirationDate is NULL OR ExpirationDate >= ?) "
            + "ORDER BY LiveDate DESC "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_PUBLISHED_POSTS_BY_TAG_ID
            = "SELECT p.* FROM Post p "
            + "INNER JOIN PostTag pt ON p.PostID = pt.PostID "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE (TagID = ? AND `Name` = 'Publish' AND LiveDate <= ?) AND "
            + "(ExpirationDate is NULL OR ExpirationDate >= ?) "
            + "ORDER BY LiveDate DESC";
    
    private static final String SQL_SELECT_PUBLISHED_POSTS
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE (`Name` = 'Publish' AND LiveDate <= ?) AND "
            + "(ExpirationDate is NULL OR ExpirationDate >= ?) "
            + "ORDER BY LiveDate DESC";
    
    private static final String SQL_SELECT_PUBLISHED_POSTS_LIMIT_BY_NUM
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE (`Name` = 'Publish' AND LiveDate <= ?) AND "
            + "(ExpirationDate is NULL OR ExpirationDate >= ?) "
            + "ORDER BY LiveDate DESC "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_DRAFT_POSTS_BY_USER_ID
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE `Name` = 'Draft' AND UserID = ? "
            + "ORDER BY LiveDate DESC";
    
    private static final String SQL_SELECT_DRAFT_POSTS_BY_USER_ID_LIMIT_BY_NUM
            = "SELECT p.* FROM Post p "
            + "INNER JOIN `Status` s ON p.StatusID = s.StatusID "
            + "WHERE `Name` = 'Draft' AND UserID = ? "
            + "ORDER BY LiveDate DESC "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_POSTS_BY_CATEGORY_ID
            = "SELECT * FROM Post "
            + "WHERE CategoryID = ? "
            + "ORDER BY LiveDate DESC";
    
    private static final String SQL_SELECT_ALL_POSTS_LIMIT_BY_NUM
            = "SELECT * FROM Post "
            + "ORDER BY LiveDate DESC "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_ALL_POSTS
            = "SELECT * FROM Post ORDER BY LiveDate DESC";
    
    private static final String SQL_SELECT_USER_ID_BY_POST_ID
            = "SELECT UserID FROM Post WHERE PostID = ?";
    
    // Status
    private static final String SQL_INSERT_STATUS
            = "INSERT INTO Status (`Name`) "
            + "VALUES (?)";

    private static final String SQL_DELETE_STATUS
            = "DELETE FROM Status WHERE StatusID = ?";

    private static final String SQL_UPDATE_STATUS
            = "UPDATE Status SET `Name` = ? "
            + "WHERE StatusID = ?";

    private static final String SQL_SELECT_STATUS
            = "SELECT * FROM Status WHERE StatusID = ?";
    
    private static final String SQL_SELECT_STATUS_BY_PAGE_ID
            = "SELECT s.* "
            + "FROM Status s "
            + "INNER JOIN Page p ON s.StatusID = p.StatusID "
            + "WHERE PageID = ?";
    
    private static final String SQL_SELECT_STATUS_BY_POST_ID
            = "SELECT s.* "
            + "FROM Status s "
            + "INNER JOIN Post p ON s.StatusID = p.StatusID "
            + "WHERE PostID = ?";

    private static final String SQL_SELECT_ALL_STATUSES
            = "SELECT * FROM Status ORDER BY `Name`";
    
    // Tag
    private static final String SQL_INSERT_TAG
            = "INSERT INTO Tag (`Name`) "
            + "VALUES (?)";

    private static final String SQL_DELETE_TAG
            = "DELETE FROM Tag WHERE TagID = ?";

    private static final String SQL_UPDATE_TAG
            = "UPDATE Tag SET `Name` = ? "
            + "WHERE TagID = ?";

    private static final String SQL_SELECT_TAG
            = "SELECT * FROM Tag WHERE TagID = ?";
    
    private static final String SQL_SELECT_TAGS_BY_POST_ID
            = "SELECT t.* "
            + "FROM Tag t "
            + "INNER JOIN PostTag pt ON t.TagID = pt.TagID "
            + "WHERE pt.PostID = ? "
            + "ORDER BY `Name`";
    
    private static final String SQL_SELECT_TAGS_LIMIT_BY_NUM
            = "SELECT * FROM Tag "
            + "ORDER BY `Name` "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_ALL_TAGS
            = "SELECT * FROM Tag ORDER BY `Name`";

    // Category Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY, category.getName());

        int categoryId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        category.setCategoryId(categoryId);
        
        return category;
    }

    @Override
    public void deleteCategory(int categoryId) {
        // removes category from posts
        jdbcTemplate.update(SQL_REMOVE_POST_CATEGORY, categoryId);
        // delete category
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                            category.getName(),
                            category.getCategoryId());
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY, 
                                               new CategoryMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getCategoriesLimitByNum(int num) {
        return jdbcTemplate.query(SQL_SELECT_CATEGORIES_LIMIT_BY_NUM, 
                                  new CategoryMapper(),
                                  num);
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    // Image Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Image addImage(Image image) {
        jdbcTemplate.update(SQL_INSERT_IMAGE, image.getName());

        int imageId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        image.setImageId(imageId);
        
        return image;
    }

    @Override
    public void deleteImage(int imageId) {
        jdbcTemplate.update(SQL_DELETE_IMAGE, imageId);
    }

    @Override
    public void updateImage(Image image) {
        jdbcTemplate.update(SQL_UPDATE_IMAGE,
                            image.getName(),
                            image.getImageId());
    }

    @Override
    public Image getImageById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_IMAGE, 
                                               new ImageMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Image> getImagesLimitByNum(int num) {
        return jdbcTemplate.query(SQL_SELECT_IMAGES_LIMIT_BY_NUM, 
                                  new ImageMapper(),
                                  num);
    }

    @Override
    public List<Image> getAllImages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_IMAGES, new ImageMapper());
    }

    // Navigation Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Navigation addNavigation(Navigation navigation) {
        jdbcTemplate.update(SQL_INSERT_NAVIGATION, navigation.getSection());

        int navigationId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        navigation.setNavigationId(navigationId);
        // now update the NavigationPage table
        insertNavigationPage(navigation);
        
        return navigation;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteNavigation(int navigationId) {
        // delete NavigationPage relationships for this navigation
        jdbcTemplate.update(SQL_DELETE_NAVIGATION_PAGE, navigationId);
        // delete navgiation
        jdbcTemplate.update(SQL_DELETE_NAVIGATION, navigationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateNavigation(Navigation navigation) {
        jdbcTemplate.update(SQL_UPDATE_NAVIGATION,
                            navigation.getSection(),
                            navigation.getNavigationId());
        // delete NavigationPage relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_NAVIGATION_PAGE, navigation.getNavigationId());
        insertNavigationPage(navigation);
    }

    @Override
    public Navigation getNavigationById(int id) {
        try {
            // get the properties from the navigation table
            Navigation navigation = 
                    jdbcTemplate.queryForObject(SQL_SELECT_NAVIGATION, 
                                                new NavigationMapper(), 
                                                id);
            // get the pages for this navigation and set list on the navigation
            navigation.setPages(findPagesForNavigation(navigation));
            return navigation;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Navigation> getNavigationsLimitByNum(int num) {
        // get specific number of navigations
        List<Navigation> navigationList = 
            jdbcTemplate.query(SQL_SELECT_NAVIGATIONS_LIMIT_BY_NUM, 
                               new NavigationMapper(),
                               num);
        // set the list of pages for each navigation
        return associatePagesWithNavigations(navigationList);
    }

    @Override
    public List<Navigation> getAllNavigations() {
        // get all the navigations
        List<Navigation> navigationList = 
            jdbcTemplate.query(SQL_SELECT_ALL_NAVIGATIONS, 
                               new NavigationMapper());
        // set the list of pages for each navigation
        return associatePagesWithNavigations(navigationList);
    }

    // Page Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Page addPage(Page page) {
        jdbcTemplate.update(SQL_INSERT_PAGE,
                            page.getTitle(),
                            page.getContent(),
                            page.getStatus().getStatusId());

        int pageId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        page.setPageId(pageId);
        
        return page;
    }

    @Override
    public void deletePage(int pageId) {
        // delete NavigationPage relationships for this page
        jdbcTemplate.update(SQL_DELETE_NAVIGATION_PAGE_BY_PAGE_ID, pageId);
        // delete page
        jdbcTemplate.update(SQL_DELETE_PAGE, pageId);
    }

    @Override
    public void updatePage(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE,
                            page.getTitle(),
                            page.getContent(),
                            page.getStatus().getStatusId(),
                            page.getPageId());
    }

    @Override
    public Page getPageById(int id) {
        try {
            // get the properties from the location table
            Page page = 
                    jdbcTemplate.queryForObject(SQL_SELECT_PAGE, 
                                                new PageMapper(), 
                                                id);
            // get the state for this location
            page.setStatus(findStatusForPage(page));
            return page;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Page getPublishedPageById(int id) {
        try {
            // get the properties from the location table
            Page page = 
                    jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHED_PAGE, 
                                                new PageMapper(), 
                                                id);
            // get the state for this location
            page.setStatus(findStatusForPage(page));
            return page;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Page> getPublishedPagesByNavigationId(int navigationId) {
        // get published pages by navigationId
        List<Page> pageList = 
                jdbcTemplate.query(SQL_SELECT_PUBLISHED_PAGES_BY_NAVIGATION_ID, 
                                   new PageMapper(),
                                   navigationId);
        // set the status for each page
        return associateStatusWithPages(pageList);
    }

    @Override
    public List<Page> getPagesLimitByNum(int num) {
        // get specific number of pages
        List<Page> pageList = 
                jdbcTemplate.query(SQL_SELECT_PAGES_LIMIT_BY_NUM, 
                                  new PageMapper(),
                                  num);
        // set the status for each page
        return associateStatusWithPages(pageList);
    }

    @Override
    public List<Page> getAllPages() {
        // get all the pages
        List<Page> pageList = 
                jdbcTemplate.query(SQL_SELECT_ALL_PAGES, new PageMapper());
        // set the status for each page
        return associateStatusWithPages(pageList);
    }

    // Post Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {
        if (post.getExpirationDate() != null) {
            jdbcTemplate.update(SQL_INSERT_POST,
                                post.getTitle(),
                                post.getContent(),
                                post.getExcerpt(),
                                post.getCreationDate().toString(),
                                post.getLiveDate().toString(),
                                post.getExpirationDate().toString(),
                                post.getUserId(),
                                post.getCategory().getCategoryId(),
                                post.getStatus().getStatusId());
        } else {
            jdbcTemplate.update(SQL_INSERT_POST_NO_EXPIRATION_DATE,
                                post.getTitle(),
                                post.getContent(),
                                post.getExcerpt(),
                                post.getCreationDate().toString(),
                                post.getLiveDate().toString(),
                                post.getUserId(),
                                post.getCategory().getCategoryId(),
                                post.getStatus().getStatusId());
        }

        int postId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        post.setPostId(postId);
        // now update the PostTag table
        insertPostTag(post);
        
        return post;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePost(int postId) {
        // delete PostTag relationships for this post
        jdbcTemplate.update(SQL_DELETE_POST_TAG, postId);
        // delete post
        jdbcTemplate.update(SQL_DELETE_POST, postId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePost(Post post) {
        if (post.getExpirationDate() != null) {
            jdbcTemplate.update(SQL_UPDATE_POST,
                                post.getTitle(),
                                post.getContent(),
                                post.getExcerpt(),
                                post.getCreationDate().toString(),
                                post.getLiveDate().toString(),
                                post.getExpirationDate().toString(),
                                post.getUserId(),
                                post.getCategory().getCategoryId(),
                                post.getStatus().getStatusId(),
                                post.getPostId());
        } else {
            jdbcTemplate.update(SQL_UPDATE_POST_NO_EXPIRATION_DATE,
                                post.getTitle(),
                                post.getContent(),
                                post.getExcerpt(),
                                post.getCreationDate().toString(),
                                post.getLiveDate().toString(),
                                post.getUserId(),
                                post.getCategory().getCategoryId(),
                                post.getStatus().getStatusId(),
                                post.getPostId());
        }
        // delete PostTag relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_POST_TAG, post.getPostId());
        insertPostTag(post);
    }

    @Override
    public Post getPostById(int id) {
        try {
            // get the properties from the post table
            Post post = 
                    jdbcTemplate.queryForObject(SQL_SELECT_POST, 
                                                new PostMapper(), 
                                                id);
            // get the userId for this post
            post.setUserId(findUserIdForPost(post));
            // get the category for this post
            post.setCategory(findCategoryForPost(post));
            // get the status for this post
            post.setStatus(findStatusForPost(post));
            // get the tags for this post and set list on the post
            post.setTags(findTagsForPost(post));
            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Post getPublishedPostById(int id) {
        String todaysDate = LocalDate.now().toString();
        try {
            // get the properties from the post table
            Post post = 
                    jdbcTemplate.queryForObject(SQL_SELECT_PUBLISHED_POST, 
                                                new PostMapper(), 
                                                id,
                                                todaysDate,
                                                todaysDate);
            // get the userId for this post
            post.setUserId(findUserIdForPost(post));
            // get the category for this post
            post.setCategory(findCategoryForPost(post));
            // get the status for this post
            post.setStatus(findStatusForPost(post));
            // get the tags for this post and set list on the post
            post.setTags(findTagsForPost(post));
            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getPublishedPostsByCategoryId(int categoryId) {
        // get published posts by categoryId
        String todaysDate = LocalDate.now().toString();
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_PUBLISHED_POSTS_BY_CATEGORY_ID, 
                                  new PostMapper(),
                                  categoryId,
                                  todaysDate,
                                  todaysDate);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }
    
    public List<Post> getPublishedPostsByCategoryId(int categoryId, int num) {
        // get published posts by categoryId
        String todaysDate = LocalDate.now().toString();
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_PUBLISHED_POSTS_BY_CATEGORY_ID_LIMIT_BY_NUM, 
                                  new PostMapper(),
                                  categoryId,
                                  todaysDate,
                                  todaysDate,
                                  num);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getPublishedPostsByTagId(int tagId) {
        // get published posts by tagId
        String todaysDate = LocalDate.now().toString();
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_PUBLISHED_POSTS_BY_TAG_ID, 
                                  new PostMapper(),
                                  tagId,
                                  todaysDate,
                                  todaysDate);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getPublishedPosts() {
        // get published posts
        String todaysDate = LocalDate.now().toString();
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_PUBLISHED_POSTS, 
                                  new PostMapper(),
                                  todaysDate,
                                  todaysDate);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getPublishedPosts(int num) {
        // get specific number of published posts
        String todaysDate = LocalDate.now().toString();
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_PUBLISHED_POSTS_LIMIT_BY_NUM, 
                                  new PostMapper(),
                                  todaysDate,
                                  todaysDate,
                                  num);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getDraftPostsByUserId(int userId) {
        // get draft posts by userId
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_DRAFT_POSTS_BY_USER_ID, 
                                  new PostMapper(),
                                  userId);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getDraftPostsByUserId(int userId, int num) {
        // get specific number of draft posts by userId
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_DRAFT_POSTS_BY_USER_ID_LIMIT_BY_NUM, 
                                  new PostMapper(),
                                  userId,
                                  num);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getPostsByCategoryId(int categoryId) {
        // get posts by categoryId
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_POSTS_BY_CATEGORY_ID, 
                                  new PostMapper(),
                                  categoryId);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getPostsLimitByNum(int num) {
        // get specific number of pages
        List<Post> postList = 
                jdbcTemplate.query(SQL_SELECT_ALL_POSTS_LIMIT_BY_NUM, 
                                  new PostMapper(),
                                  num);
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    @Override
    public List<Post> getAllPosts() {
        // get all the posts
        List<Post> postList = 
            jdbcTemplate.query(SQL_SELECT_ALL_POSTS, 
                               new PostMapper());
        // set the user, category, status, and list of tags for each post
        return associateUserCategoryStatusAndTagsWithPosts(postList);
    }

    // Status Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Status addStatus(Status status) {
        jdbcTemplate.update(SQL_INSERT_STATUS, status.getName());

        int statusId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        status.setStatusId(statusId);
        
        return status;
    }

    @Override
    public void deleteStatus(int statusId) {
        jdbcTemplate.update(SQL_DELETE_STATUS, statusId);
    }

    @Override
    public void updateStatus(Status status) {
        jdbcTemplate.update(SQL_UPDATE_STATUS,
                            status.getName(),
                            status.getStatusId());
    }

    @Override
    public Status getStatusById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATUS, 
                                               new StatusMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Status> getAllStatuses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATUSES, new StatusMapper());
    }

    // Tag Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag tag) {
        jdbcTemplate.update(SQL_INSERT_TAG, tag.getName());

        int tagId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        tag.setTagId(tagId);
        
        return tag;
    }

    @Override
    public void deleteTag(int tagId) {
        // delete PostTag relationships for this tag
        jdbcTemplate.update(SQL_DELETE_POST_TAG_BY_TAG_ID, tagId);
        // delete tag
        jdbcTemplate.update(SQL_DELETE_TAG, tagId);
    }

    @Override
    public void updateTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG,
                            tag.getName(),
                            tag.getTagId());
    }

    @Override
    public Tag getTagById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG, 
                                               new TagMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Tag> getTagsLimitByNum(int num) {
        return jdbcTemplate.query(SQL_SELECT_TAGS_LIMIT_BY_NUM, 
                                  new TagMapper(),
                                  num);
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS, new TagMapper());
    }

    // Navigation Helper Methods
    private void insertNavigationPage(Navigation navigation) {
        final int navigationId = navigation.getNavigationId();
        final List<Page> pages = navigation.getPages();

        // Update the NavigationPage bridge table with an entry for 
        // each page of this navigation
        for (Page currentPage : pages) {
            jdbcTemplate.update(SQL_INSERT_NAVIGATION_PAGE, 
                                navigationId, 
                                currentPage.getPageId());
        }
    }

    private List<Page> findPagesForNavigation(Navigation navigation) {
        // get all the pages for navigation
        List<Page> pageList = 
                jdbcTemplate.query(SQL_SELECT_PAGES_BY_NAVIGATION_ID, 
                                   new PageMapper(),
                                   navigation.getNavigationId());
        // set the status for each page
        return associateStatusWithPages(pageList);
    }

    private List<Navigation> associatePagesWithNavigations(List<Navigation> navigationList) {
        for (Navigation currentNavigation : navigationList) {
            currentNavigation.setPages(findPagesForNavigation(currentNavigation));
        }
        return navigationList;
    }

    // Page Helper Methods
    private Status findStatusForPage(Page page) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATUS_BY_PAGE_ID, 
                                               new StatusMapper(), 
                                               page.getPageId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private List<Page> associateStatusWithPages(List<Page> pageList) {
        for (Page currentPage : pageList) {
            currentPage.setStatus(findStatusForPage(currentPage));
        }
        return pageList;
    }

    // Post Helper Methods
    private void insertPostTag(Post post) {
        final int postId = post.getPostId();
        final List<Tag> tags = post.getTags();

        // Update the PostTag bridge table with an entry for 
        // each tag of this post
        for (Tag currentTag : tags) {
            jdbcTemplate.update(SQL_INSERT_POST_TAG, 
                                postId, 
                                currentTag.getTagId());
        }
    }

    private int findUserIdForPost(Post post) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_ID_BY_POST_ID, 
                                           Integer.class,
                                           post.getPostId());
    }

    private Category findCategoryForPost(Post post) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_BY_POST_ID, 
                                               new CategoryMapper(), 
                                               post.getPostId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private Status findStatusForPost(Post post) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATUS_BY_POST_ID, 
                                               new StatusMapper(), 
                                               post.getPostId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private List<Tag> findTagsForPost(Post post) {
        return jdbcTemplate.query(SQL_SELECT_TAGS_BY_POST_ID, 
                                  new TagMapper(), 
                                  post.getPostId());
    }

    private List<Post> associateUserCategoryStatusAndTagsWithPosts(List<Post> postList) {
        for (Post currentPost : postList) {
            // add the user to current post
            currentPost.setUserId(findUserIdForPost(currentPost));
            // add the category to current post
            currentPost.setCategory(findCategoryForPost(currentPost));
            // add the status to current post
            currentPost.setStatus(findStatusForPost(currentPost));
            // add tags to current post
            currentPost.setTags(findTagsForPost(currentPost));
        }
        return postList;
    }

    // Mappers
    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getInt("CategoryID"));
            category.setName(rs.getString("Name"));
            return category;
        }
    }

    private static final class ImageMapper implements RowMapper<Image> {

        @Override
        public Image mapRow(ResultSet rs, int i) throws SQLException {
            Image image = new Image();
            image.setImageId(rs.getInt("ImageID"));
            image.setName(rs.getString("Name"));
            return image;
        }
    }

    private static final class NavigationMapper implements RowMapper<Navigation> {

        @Override
        public Navigation mapRow(ResultSet rs, int i) throws SQLException {
            Navigation navigation = new Navigation();
            navigation.setNavigationId(rs.getInt("NavigationID"));
            navigation.setSection(rs.getString("Section"));
            return navigation;
        }
    }

    private static final class PageMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int i) throws SQLException {
            Page page = new Page();
            page.setPageId(rs.getInt("PageID"));
            page.setTitle(rs.getString("Title"));
            page.setContent(rs.getString("Content"));
            return page;
        }
    }

    private static final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("PostID"));
            post.setTitle(rs.getString("Title"));
            post.setContent(rs.getString("Content"));
            post.setExcerpt(rs.getString("Excerpt"));
            post.setCreationDate(rs.getTimestamp("CreationDate").toLocalDateTime().toLocalDate());
            post.setLiveDate(rs.getTimestamp("LiveDate").toLocalDateTime().toLocalDate());
            if (rs.getTimestamp("ExpirationDate") != null) {
                post.setExpirationDate(rs.getTimestamp("ExpirationDate").toLocalDateTime().toLocalDate());
            }
            return post;
        }
    }

    private static final class StatusMapper implements RowMapper<Status> {

        @Override
        public Status mapRow(ResultSet rs, int i) throws SQLException {
            Status status = new Status();
            status.setStatusId(rs.getInt("StatusID"));
            status.setName(rs.getString("Name"));
            return status;
        }
    }

    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setTagId(rs.getInt("TagID"));
            tag.setName(rs.getString("Name"));
            return tag;
        }
    }
}
