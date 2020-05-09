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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class CMSBlogDaoTest {
    
    CMSBlogDao dao;
    
    public CMSBlogDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("cmsBlogDao", CMSBlogDao.class);
        
        // remove all of the navigations
        List<Navigation> navigations = dao.getAllNavigations();
        for (Navigation currentNavigation : navigations) {
            dao.deleteNavigation(currentNavigation.getNavigationId());
        }
        
        // remove all of the pages
        List<Page> pages = dao.getAllPages();
        for (Page currentPage : pages) {
            dao.deletePage(currentPage.getPageId());
        }
        
        // remove all of the images
        List<Image> images = dao.getAllImages();
        for (Image currentImage : images) {
            dao.deleteImage(currentImage.getImageId());
        }
        
        // remove all of the posts
        List<Post> posts = dao.getAllPosts();
        for (Post currentPost : posts) {
            dao.deletePost(currentPost.getPostId());
        }
        
        // remove all of the categories
        List<Category> categories = dao.getAllCategories();
        for (Category currentCategory : categories) {
            dao.deleteCategory(currentCategory.getCategoryId());
        }
        
        // remove all of the statuses
        List<Status> statuses = dao.getAllStatuses();
        for (Status currentStatus : statuses) {
            dao.deleteStatus(currentStatus.getStatusId());
        }
        
        // remove all of the tags
        List<Tag> tags = dao.getAllTags();
        for (Tag currentTag : tags) {
            dao.deleteTag(currentTag.getTagId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCategory method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetCategory() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);

        Category fromDao = dao.getCategoryById(category.getCategoryId());
        assertEquals(category, fromDao);
    }

    /**
     * Test of deleteCategory method, of class CMSBlogDao.
     */
    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);

        Category fromDao = dao.getCategoryById(category.getCategoryId());
        assertEquals(category, fromDao);
        dao.deleteCategory(category.getCategoryId());
        assertNull(dao.getCategoryById(category.getCategoryId()));
    }

    /**
     * Test of updateCategory method, of class CMSBlogDao.
     */
    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);

        category.setName("Instruction");

        dao.updateCategory(category);

        Category fromDao = dao.getCategoryById(category.getCategoryId());
        assertEquals(category, fromDao);
    }

    /**
     * Test of getCategoriesLimitByNum method, of class CMSBlogDao.
     */
    @Test
    public void testGetCategoriesLimitByNum() {
        Category category1 = new Category();
        category1.setName("Tutorial");
        
        dao.addCategory(category1);
        
        Category category2 = new Category();
        category2.setName("News");
        
        dao.addCategory(category2);
        
        assertEquals(1, dao.getCategoriesLimitByNum(1).size());
    }

    /**
     * Test of getAllCategories method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        category1.setName("Tutorial");
        
        dao.addCategory(category1);
        
        Category category2 = new Category();
        category2.setName("News");
        
        dao.addCategory(category2);
        
        assertEquals(2, dao.getAllCategories().size());
    }

    /**
     * Test of addImage method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetImage() {
        Image image = new Image();
        image.setName("image.jpg");

        dao.addImage(image);

        Image fromDao = dao.getImageById(image.getImageId());
        assertEquals(image, fromDao);
    }

    /**
     * Test of deleteImage method, of class CMSBlogDao.
     */
    @Test
    public void testDeleteImage() {
        Image image = new Image();
        image.setName("image.jpg");

        dao.addImage(image);

        Image fromDao = dao.getImageById(image.getImageId());
        assertEquals(image, fromDao);
        dao.deleteImage(image.getImageId());
        assertNull(dao.getImageById(image.getImageId()));
    }

    /**
     * Test of updateImage method, of class CMSBlogDao.
     */
    @Test
    public void testUpdateImage() {
        Image image = new Image();
        image.setName("image.jpg");

        dao.addImage(image);

        image.setName("photo.jpg");

        dao.updateImage(image);

        Image fromDao = dao.getImageById(image.getImageId());
        assertEquals(image, fromDao);
    }

    /**
     * Test of getImagesLimitByNum method, of class CMSBlogDao.
     */
    @Test
    public void testGetImagesLimitByNum() {
        Image image1 = new Image();
        image1.setName("image1.jpg");
        
        dao.addImage(image1);
        
        Image image2 = new Image();
        image2.setName("image2.jpg");
        
        dao.addImage(image2);
        
        assertEquals(1, dao.getImagesLimitByNum(1).size());
    }

    /**
     * Test of getAllImages method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllImages() {
        Image image1 = new Image();
        image1.setName("image1.jpg");
        
        dao.addImage(image1);
        
        Image image2 = new Image();
        image2.setName("image2.jpg");
        
        dao.addImage(image2);
        
        assertEquals(2, dao.getAllImages().size());
    }

    /**
     * Test of addNavigation method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetNavigation() {
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);
        
        Navigation navigation = new Navigation();
        navigation.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        navigation.setPages(pages);

        dao.addNavigation(navigation);

        Navigation fromDao = dao.getNavigationById(navigation.getNavigationId());
        assertEquals(navigation, fromDao);
    }

    /**
     * Test of deleteNavigation method, of class CMSBlogDao.
     */
    @Test
    public void testDeleteNavigation() {
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);
        
        Navigation navigation = new Navigation();
        navigation.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        navigation.setPages(pages);

        dao.addNavigation(navigation);

        Navigation fromDao = dao.getNavigationById(navigation.getNavigationId());
        assertEquals(navigation, fromDao);
        dao.deleteNavigation(navigation.getNavigationId());
        assertNull(dao.getNavigationById(navigation.getNavigationId()));
    }

    /**
     * Test of updateNavigation method, of class CMSBlogDao.
     */
    @Test
    public void testUpdateNavigation() {
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);
        
        Navigation navigation = new Navigation();
        navigation.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        navigation.setPages(pages);

        dao.addNavigation(navigation);

        navigation.setSection("Header");

        dao.updateNavigation(navigation);

        Navigation fromDao = dao.getNavigationById(navigation.getNavigationId());
        assertEquals(navigation, fromDao);
    }

    /**
     * Test of getNavigationsLimitByNum method, of class CMSBlogDao.
     */
    @Test
    public void testGetNavigationsLimitByNum() {
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);
        
        Navigation navigation1 = new Navigation();
        navigation1.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        navigation1.setPages(pages);

        dao.addNavigation(navigation1);
        
        Navigation navigation2 = new Navigation();
        navigation2.setSection("Footer");
        navigation2.setPages(pages);
        
        dao.addNavigation(navigation2);
        
        assertEquals(1, dao.getNavigationsLimitByNum(1).size());
    }

    /**
     * Test of getAllNavigations method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllNavigations() {
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);
        
        Navigation navigation1 = new Navigation();
        navigation1.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        navigation1.setPages(pages);

        dao.addNavigation(navigation1);
        
        Navigation navigation2 = new Navigation();
        navigation2.setSection("Footer");
        navigation2.setPages(pages);
        
        dao.addNavigation(navigation2);
        
        assertEquals(2, dao.getAllNavigations().size());
    }

    /**
     * Test of addPage method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetPage() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);

        Page fromDao = dao.getPageById(page.getPageId());
        assertEquals(page, fromDao);
    }

    /**
     * Test of deletePage method, of class CMSBlogDao.
     */
    @Test
    public void testDeletePage() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);

        Page fromDao = dao.getPageById(page.getPageId());
        assertEquals(page, fromDao);
        dao.deletePage(page.getPageId());
        assertNull(dao.getPageById(page.getPageId()));
    }

    /**
     * Test of updatePage method, of class CMSBlogDao.
     */
    @Test
    public void testUpdatePage() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);
        
        status.setName("Publish");
        
        dao.updateStatus(status);

        page.setTitle("Updated Title");
        page.setContent("Updated Conent");
        page.setStatus(status);

        dao.updatePage(page);

        Page fromDao = dao.getPageById(page.getPageId());
        assertEquals(page, fromDao);
    }

    /**
     * Test of getPublishedPageById method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPageById() {
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);

        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);

        dao.addPage(page);

        Page fromDao = dao.getPublishedPageById(page.getPageId());
        assertEquals(page, fromDao);
    }

    /**
     * Test of getPublishedPagesByNavigationId method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPagesByNavigationId() {
        Status status1 = new Status();
        status1.setName("Publish");

        dao.addStatus(status1);

        Page page1 = new Page();
        page1.setTitle("Lorem Ipsum");
        page1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page1.setStatus(status1);

        dao.addPage(page1);
        
        Status status2 = new Status();
        status2.setName("Publish");

        dao.addStatus(status2);

        Page page2 = new Page();
        page2.setTitle("Lorem Ipsum");
        page2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page2.setStatus(status2);

        dao.addPage(page2);
        
        Navigation navigation = new Navigation();
        navigation.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page1);
        navigation.setPages(pages);

        dao.addNavigation(navigation);
        
        assertEquals(1, dao.getPublishedPagesByNavigationId(navigation.getNavigationId()).size());
    }

    /**
     * Test of getPagesLimitByNum method, of class CMSBlogDao.
     */
    @Test
    public void testGetPagesLimitByNum() {
        Status status1 = new Status();
        status1.setName("Draft");

        dao.addStatus(status1);

        Page page1 = new Page();
        page1.setTitle("Lorem Ipsum");
        page1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page1.setStatus(status1);

        dao.addPage(page1);
        
        Status status2 = new Status();
        status2.setName("Draft");

        dao.addStatus(status2);

        Page page2 = new Page();
        page2.setTitle("Lorem Ipsum");
        page2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page2.setStatus(status2);

        dao.addPage(page2);
        
        assertEquals(1, dao.getPagesLimitByNum(1).size());
    }

    /**
     * Test of getAllPages method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllPages() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        Page page1 = new Page();
        page1.setTitle("Lorem Ipsum");
        page1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page1.setStatus(status);

        dao.addPage(page1);

        Page page2 = new Page();
        page2.setTitle("Lorem Ipsum");
        page2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page2.setStatus(status);

        dao.addPage(page2);
        
        assertEquals(2, dao.getAllPages().size());
    }

    /**
     * Test of addPost method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetPost() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post = new Post();
        post.setTitle("Lorem Ipsum");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);

        dao.addPost(post);

        Post fromDao = dao.getPostById(post.getPostId());
        assertEquals(post, fromDao);
    }

    /**
     * Test of deletePost method, of class CMSBlogDao.
     */
    @Test
    public void testDeletePost() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post = new Post();
        post.setTitle("Lorem Ipsum");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);

        dao.addPost(post);

        Post fromDao = dao.getPostById(post.getPostId());
        assertEquals(post, fromDao);
        dao.deletePost(post.getPostId());
        assertNull(dao.getPostById(post.getPostId()));
    }

    /**
     * Test of updatePost method, of class CMSBlogDao.
     */
    @Test
    public void testUpdatePost() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post = new Post();
        post.setTitle("Lorem Ipsum");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);

        dao.addPost(post);
        
        status.setName("Publish");
        
        dao.updateStatus(status);

        post.setTitle("Updated Title");
        post.setContent("Updated Conent");
        post.setExcerpt("Updated Excerpt");
        post.setStatus(status);

        dao.updatePost(post);

        Post fromDao = dao.getPostById(post.getPostId());
        assertEquals(post, fromDao);
    }
    
    /**
     * Test of getPublishedPostById method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPostById() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post = new Post();
        post.setTitle("Lorem Ipsum");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);

        dao.addPost(post);

        Post fromDao = dao.getPublishedPostById(post.getPostId());
        assertEquals(post, fromDao);
    }

    /**
     * Test of getPublishedPostsByCategoryId method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPostsByCategoryId() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(2, dao.getPublishedPostsByCategoryId(category.getCategoryId()).size());
    }

    /**
     * Test of getPublishedPostsByCategoryId method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPostsByCategoryId_int() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(1, dao.getPublishedPostsByCategoryId(category.getCategoryId(), 1).size());
    }

    /**
     * Test of getPublishedPostsByTagId method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPostsByTagId() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(2, dao.getPublishedPostsByTagId(tag.getTagId()).size());
    }

    /**
     * Test of getPublishedPosts method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPosts() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(2, dao.getPublishedPosts().size());
    }

    /**
     * Test of getPublishedPosts method, of class CMSBlogDao.
     */
    @Test
    public void testGetPublishedPosts_int() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Publish");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(1, dao.getPublishedPosts(1).size());
    }

    /**
     * Test of getDraftPostsByUserId method, of class CMSBlogDao.
     */
    @Test
    public void testGetDraftPostsByUserId_int() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(2, dao.getDraftPostsByUserId(post1.getUserId()).size());
    }

    /**
     * Test of getDraftPostsByUserId method, of class CMSBlogDao.
     */
    @Test
    public void testGetDraftPostsByUserId_int_int() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(1, dao.getDraftPostsByUserId(post1.getUserId(), 1).size());
    }

    /**
     * Test of getPostsLimitByNum method, of class CMSBlogDao.
     */
    @Test
    public void testGetPostsLimitByNum() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(1, dao.getPostsLimitByNum(1).size());
    }

    /**
     * Test of getAllPosts method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllPosts() {
        Category category = new Category();
        category.setName("Tutorial");

        dao.addCategory(category);
        
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);
        
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Post post1 = new Post();
        post1.setTitle("Lorem Ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post1.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post1.setCreationDate(LocalDate.parse("2020-04-04"));
        post1.setLiveDate(LocalDate.parse("2020-04-04"));
        post1.setExpirationDate(LocalDate.parse("2030-04-04"));
        post1.setUserId(1);
        post1.setCategory(category);
        post1.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post1.setTags(tags);

        dao.addPost(post1);
        
        Post post2 = new Post();
        post2.setTitle("Lorem Ipsum");
        post2.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post2.setExcerpt("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua.");
        post2.setCreationDate(LocalDate.parse("2020-04-04"));
        post2.setLiveDate(LocalDate.parse("2020-04-04"));
        post2.setExpirationDate(LocalDate.parse("2030-04-04"));
        post2.setUserId(1);
        post2.setCategory(category);
        post2.setStatus(status);
        post2.setTags(tags);

        dao.addPost(post2);
        
        assertEquals(2, dao.getAllPosts().size());
    }

    /**
     * Test of addStatus method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetStatus() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        Status fromDao = dao.getStatusById(status.getStatusId());
        assertEquals(status, fromDao);
    }

    /**
     * Test of deleteStatus method, of class CMSBlogDao.
     */
    @Test
    public void testDeleteStatus() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        Status fromDao = dao.getStatusById(status.getStatusId());
        assertEquals(status, fromDao);
        dao.deleteStatus(status.getStatusId());
        assertNull(dao.getStatusById(status.getStatusId()));
    }

    /**
     * Test of updateStatus method, of class CMSBlogDao.
     */
    @Test
    public void testUpdateStatus() {
        Status status = new Status();
        status.setName("Draft");

        dao.addStatus(status);

        status.setName("Working");

        dao.updateStatus(status);

        Status fromDao = dao.getStatusById(status.getStatusId());
        assertEquals(status, fromDao);
    }

    /**
     * Test of getAllStatuses method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllStatuses() {
        Status status1 = new Status();
        status1.setName("Draft");
        
        dao.addStatus(status1);
        
        Status status2 = new Status();
        status2.setName("Pending");
        
        dao.addStatus(status2);
        
        assertEquals(2, dao.getAllStatuses().size());
    }

    /**
     * Test of addTag method, of class CMSBlogDao.
     */
    @Test
    public void testAddGetTag() {
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Tag fromDao = dao.getTagById(tag.getTagId());
        assertEquals(tag, fromDao);
    }

    /**
     * Test of deleteTag method, of class CMSBlogDao.
     */
    @Test
    public void testDeleteTag() {
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        Tag fromDao = dao.getTagById(tag.getTagId());
        assertEquals(tag, fromDao);
        dao.deleteTag(tag.getTagId());
        assertNull(dao.getTagById(tag.getTagId()));
    }

    /**
     * Test of updateTag method, of class CMSBlogDao.
     */
    @Test
    public void testUpdateTag() {
        Tag tag = new Tag();
        tag.setName("Java");

        dao.addTag(tag);

        tag.setName("Spring");

        dao.updateTag(tag);

        Tag fromDao = dao.getTagById(tag.getTagId());
        assertEquals(tag, fromDao);
    }

    /**
     * Test of getTagsLimitByNum method, of class CMSBlogDao.
     */
    @Test
    public void testGetTagsLimitByNum() {
        Tag tag1 = new Tag();
        tag1.setName("Java");
        
        dao.addTag(tag1);
        
        Tag tag2 = new Tag();
        tag2.setName("JavaScript");
        
        dao.addTag(tag2);
        
        assertEquals(1, dao.getTagsLimitByNum(1).size());
    }

    /**
     * Test of getAllTags method, of class CMSBlogDao.
     */
    @Test
    public void testGetAllTags() {
        Tag tag1 = new Tag();
        tag1.setName("Java");
        
        dao.addTag(tag1);
        
        Tag tag2 = new Tag();
        tag2.setName("JavaScript");
        
        dao.addTag(tag2);
        
        assertEquals(2, dao.getAllTags().size());
    }
}
