/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.service;

import com.sg.cmsblog.model.Category;
import com.sg.cmsblog.model.Image;
import com.sg.cmsblog.model.Navigation;
import com.sg.cmsblog.model.Page;
import com.sg.cmsblog.model.Post;
import com.sg.cmsblog.model.Status;
import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class CMSBlogServiceLayerTest {
    
    private CMSBlogServiceLayer service;
    
    public CMSBlogServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("cmsBlogServiceLayer", CMSBlogServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCategory method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAddCategory() {
        Category category = new Category();
        category.setName("Tutorial");
        
        service.addCategory(category);
    }

    /**
     * Test of deleteCategory method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testDeleteCategory() throws Exception {
        service.deleteCategory(2);
    }
    
    @Test
    public void testDeleteCategoryOfPost() throws Exception {
        try {
            service.deleteCategory(1);
            fail("Expected PostHasCategoryException was not thrown.");
        } catch (PostHasCategoryException e) {
            return;
        }
    }

    /**
     * Test of updateCategory method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setName("Tutorial");

        service.updateCategory(category);
    }

    /**
     * Test of getCategoryById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetCategoryById() {
        Category category = service.getCategoryById(1);
        assertNotNull(category);
        category = service.getCategoryById(2);
        assertNull(category);
    }

    /**
     * Test of getCategoriesLimitByNum method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetCategoriesLimitByNum() {
        assertEquals(1, service.getCategoriesLimitByNum(1).size());
    }

    /**
     * Test of getAllCategories method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllCategories() {
        assertEquals(1, service.getAllCategories().size());
    }

    /**
     * Test of addImage method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAddImage() throws Exception {
        Image image = new Image();
        image.setName("image.jpg");
        
        service.addImage(image);
    }
    
    @Test
    public void testAddImageNameLength1() throws Exception {
        Image image = new Image();
        image.setName("a");
        
        service.addImage(image);
    }
    
    @Test
    public void testAddImageNameLength50() throws Exception {
        Image image = new Image();
        image.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx");
        
        service.addImage(image);
    }
    
    @Test
    public void testAddImageInvalidDataNoName() throws Exception {
        Image image = new Image();
        
        try {
            service.addImage(image);
            fail("Expected ImageDataValidationException was not thrown.");
        } catch (ImageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddImageInvalidDataNameLength0() throws Exception {
        Image image = new Image();
        image.setName("");
        
        try {
            service.addImage(image);
            fail("Expected ImageDataValidationException was not thrown.");
        } catch (ImageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddImageInvalidDataNameLength51() throws Exception {
        Image image = new Image();
        image.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        
        try {
            service.addImage(image);
            fail("Expected ImageDataValidationException was not thrown.");
        } catch (ImageDataValidationException e) {
            return;
        }
    }

    /**
     * Test of deleteImage method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testDeleteImage() {
        service.deleteImage(1);
    }

    /**
     * Test of getImageById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetImageById() {
        Image image = service.getImageById(1);
        assertNotNull(image);
        image = service.getImageById(2);
        assertNull(image);
    }

    /**
     * Test of getImagesLimitByNum method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetImagesLimitByNum() {
        assertEquals(1, service.getImagesLimitByNum(1).size());
    }

    /**
     * Test of getAllImages method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllImages() {
        assertEquals(1, service.getAllImages().size());
    }

    /**
     * Test of updateNavigation method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testUpdateNavigation() {
        Status status = new Status();
        status.setName("Publish");
        
        Page page = new Page();
        page.setPageId(1);
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
        
        Navigation navigation = new Navigation();
        navigation.setSection("Navbar");
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        navigation.setPages(pages);

        service.updateNavigation(navigation);
    }

    /**
     * Test of getNavigationById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetNavigationById() {
        Navigation navigation = service.getNavigationById(1);
        assertNotNull(navigation);
        navigation = service.getNavigationById(2);
        assertNull(navigation);
    }

    /**
     * Test of getAllNavigations method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllNavigations() {
        assertEquals(1, service.getAllNavigations().size());
    }

    /**
     * Test of addPage method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAddPage() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
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
        
        service.addPage(page);
    }
    
    @Test
    public void testAddPageTitleLength1() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setTitle("a");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        service.addPage(page);
    }
    
    @Test
    public void testAddPageTitleLength50() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setTitle("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        service.addPage(page);
    }
    
    @Test
    public void testAddPageInvalidDataNoTitle() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        try {
            service.addPage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPageInvalidDataTitleLength0() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setTitle("");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        try {
            service.addPage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPageInvalidDataTitleLength51() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setTitle("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        try {
            service.addPage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPageInvalidDataNoContent() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setStatus(status);
        
        try {
            service.addPage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPageInvalidDataContentLength0() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setTitle("Lorem Ipsum");
        page.setContent("");
        page.setStatus(status);
        
        try {
            service.addPage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPageInvalidDataNoStatus() throws Exception {
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
        
        try {
            service.addPage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }

    /**
     * Test of deletePage method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testDeletePage() {
        service.deletePage(1);
    }

    /**
     * Test of updatePage method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testUpdatePage() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
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
        
        service.updatePage(page);
    }
    
    @Test
    public void testUpdatePageTitleLength1() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("a");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        service.updatePage(page);
    }
    
    @Test
    public void testUpdatePageTitleLength50() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        service.updatePage(page);
    }
    
    @Test
    public void testUpdatePageInvalidDataNoTitle() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        try {
            service.updatePage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePageInvalidDataTitleLength0() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        try {
            service.updatePage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePageInvalidDataTitleLength51() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        page.setStatus(status);
        
        try {
            service.updatePage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePageInvalidDataNoContent() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("Lorem Ipsum");
        page.setStatus(status);
        
        try {
            service.updatePage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePageInvalidDataContentLength0() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("Lorem Ipsum");
        page.setContent("");
        page.setStatus(status);
        
        try {
            service.updatePage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePageInvalidDataNoStatus() throws Exception {
        Page page = new Page();
        page.setPageId(1);
        page.setTitle("Lorem Ipsum");
        page.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        
        try {
            service.updatePage(page);
            fail("Expected PageDataValidationException was not thrown.");
        } catch (PageDataValidationException e) {
            return;
        }
    }

    /**
     * Test of getPageById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPageById() {
        Page page = service.getPageById(1);
        assertNotNull(page);
        page = service.getPageById(3);
        assertNull(page);
    }

    /**
     * Test of getPublishedPageById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPageById() {
        Page page = service.getPublishedPageById(2);
        assertNotNull(page);
        page = service.getPublishedPageById(1);
        assertNull(page);
    }

    /**
     * Test of getPublishedPagesByNavigationId method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPagesByNavigationId() {
        assertEquals(1, service.getPublishedPagesByNavigationId(1).size());
    }

    /**
     * Test of getPagesLimitByNum method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPagesLimitByNum() {
        assertEquals(2, service.getPagesLimitByNum(2).size());
    }

    /**
     * Test of getAllPages method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllPages() {
        assertEquals(2, service.getAllPages().size());
    }

    /**
     * Test of addPost method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAdminAddDraftPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.addPost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testAdminAddPublishedPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.addPost(post, user);
        
        assertEquals("Publish", post.getStatus().getName());
    }
    
    @Test
    public void testUserAddNoStatusPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.addPost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testUserAddDraftPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.addPost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testUserAddPublishedPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.addPost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testAddPostTitleLength1() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setTitle("a");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.addPost(post, user);
    }
    
    @Test
    public void testAddPostTitleLength50() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.addPost(post, user);
    }
    
    @Test
    public void testAddPostInvalidDataNoTitle() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataTitleLength0() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setTitle("");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataTitleLength51() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setTitle("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataNoContent() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setTitle("Lorem Ipsum");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataContentLength0() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setTitle("Lorem Ipsum");
        post.setContent("");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataNoExcerpt() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataExcerptLength0() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        post.setExcerpt("");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataNoCreationDate() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataNoLiveDate() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataNoCategory() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostInvalidDataNoStatus() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
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
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.addPost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }

    /**
     * Test of deletePost method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAdminDeleteOwnDraftPost() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.deletePost(1, user);
    }
    
    @Test
    public void testAdminDeleteOtherUsersDraftPost() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.deletePost(1, user);
    }
    
    @Test
    public void testAdminDeleteOwnPublishedPost() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.deletePost(2, user);
    }
    
    @Test
    public void testAdminDeleteOtherUsersPublishedPost() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.deletePost(2, user);
    }
    
    @Test
    public void testUserDeleteOwnDraftPost() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.deletePost(1, user);
    }
    
    @Test
    public void testUserDeleteOtherUsersDraftPost() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            service.deletePost(1, user);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUserDeleteOwnPublishedPost() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            service.deletePost(2, user);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUserDeleteOtherUsersPublishedPost() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            service.deletePost(2, user);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }

    /**
     * Test of updatePost method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAdminUpdateOwnDraftPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.updatePost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testAdminUpdateOtherUsersDraftPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.updatePost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testAdminUpdateOwnPublishedPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(2);
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
        
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.updatePost(post, user);
        
        assertEquals("Publish", post.getStatus().getName());
    }
    
    @Test
    public void testAdminUpdateOtherUsersPublishedPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(2);
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
        
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.updatePost(post, user);
        
        assertEquals("Publish", post.getStatus().getName());
    }
    
    @Test
    public void testUserUpdateOwnDraftPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.updatePost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testUserUpdateOtherUsersDraftPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setId(2);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUserUpdateOwnDraftPostAndTryToSetAsPublished() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.updatePost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testUserUpdateOwnDraftPostWithNoStatus() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        service.updatePost(post, user);
        
        assertEquals("Draft", post.getStatus().getName());
    }
    
    @Test
    public void testUserUpdateOwnPublishedPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(2);
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
        
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUserUpdateOtherUsersPublishedPost() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Publish");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(2);
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
        
        User user = new User();
        user.setId(2);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostTitleLength1() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("a");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.updatePost(post, user);
    }
    
    @Test
    public void testUpdatePostTitleLength50() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        service.updatePost(post, user);
    }
    
    @Test
    public void testUpdatePostInvalidDataNoTitle() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataTitleLength0() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataTitleLength51() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataNoContent() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Lorem Ipsum");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataContentLength0() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Lorem Ipsum");
        post.setContent("");
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
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataNoExcerpt() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Lorem Ipsum");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataExcerptLength0() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Lorem Ipsum");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et dolore "
                + "magna aliqua. Ut enim ad minim veniam, quis nostrud "
                + "exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                + "consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                + "Excepteur sint occaecat cupidatat non proident, sunt in "
                + "culpa qui officia deserunt mollit anim id est laborum.");
        post.setExcerpt("");
        post.setCreationDate(LocalDate.parse("2020-04-04"));
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataNoCreationDate() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        post.setLiveDate(LocalDate.parse("2020-04-04"));
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataNoLiveDate() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        post.setExpirationDate(LocalDate.parse("2030-04-04"));
        post.setUserId(1);
        post.setCategory(category);
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataNoCategory() throws Exception {
        Status status = new Status();
        status.setName("Draft");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        post.setStatus(status);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testUpdatePostInvalidDataNoStatus() throws Exception {
        Category category = new Category();
        category.setName("Tutorial");
        
        Tag tag = new Tag();
        tag.setName("Java");
        
        Post post = new Post();
        post.setPostId(1);
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
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        post.setTags(tags);
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        try {
            service.updatePost(post, user);
            fail("Expected PostDataValidationException was not thrown.");
        } catch (PostDataValidationException e) {
            return;
        }
    }

    /**
     * Test of getPostById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAdminGetOwnDraftPostById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        Post post = service.getPostById(1, user);
        assertNotNull(post);
        post = service.getPostById(3, user);
        assertNull(post);
    }
    
    @Test
    public void testAdminGetOtherUsersDraftPostById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        Post post = service.getPostById(1, user);
        assertNotNull(post);
    }
    
    @Test
    public void testAdminGetOwnPublishedPostById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        Post post = service.getPostById(1, user);
        assertNotNull(post);
    }
    
    @Test
    public void testAdminGetOtherUsersPublishedPostById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");
        
        Post post = service.getPostById(1, user);
        assertNotNull(post);
    }
    
    @Test
    public void testUserGetOwnDraftPostById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        Post post = service.getPostById(1, user);
        assertNotNull(post);
    }
    
    @Test
    public void testUserGetOtherUsersDraftPostById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            Post post = service.getPostById(1, user);
            assertNotNull(post);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUserGetOwnPublishedPostById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            Post post = service.getPostById(2, user);
            assertNotNull(post);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }
    
    @Test
    public void testUserGetOtherUsersPublishedPostById() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.addAuthority("ROLE_USER");
        
        try {
            Post post = service.getPostById(2, user);
            assertNotNull(post);
            fail("Expected PostUserPermissionException was not thrown.");
        } catch (PostUserPermissionException e) {
            return;
        }
    }

    /**
     * Test of getPublishedPostById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPostById() {
        Post post = service.getPublishedPostById(2);
        assertNotNull(post);
        post = service.getPublishedPostById(1);
        assertNull(post);
    }

    /**
     * Test of getPublishedPostsByCategoryId method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPostsByCategoryId_int() {
        assertEquals(1, service.getPublishedPostsByCategoryId(1).size());
    }

    /**
     * Test of getPublishedPostsByCategoryId method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPostsByCategoryId_int_int() {
        assertEquals(1, service.getPublishedPostsByCategoryId(1, 1).size());
    }

    /**
     * Test of getPublishedPostsByTagId method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPostsByTagId() {
        assertEquals(1, service.getPublishedPostsByTagId(1).size());
    }

    /**
     * Test of getPublishedPosts method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPosts_0args() {
        assertEquals(1, service.getPublishedPosts().size());
    }

    /**
     * Test of getPublishedPosts method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPublishedPosts_int() {
        assertEquals(1, service.getPublishedPosts(1).size());
    }

    /**
     * Test of getDraftPostsByUserId method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetDraftPostsByUserId_int() {
        assertEquals(1, service.getDraftPostsByUserId(1).size());
    }

    /**
     * Test of getDraftPostsByUserId method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetDraftPostsByUserId_int_int() {
        assertEquals(1, service.getDraftPostsByUserId(1, 1).size());
    }

    /**
     * Test of getPostsLimitByNum method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetPostsLimitByNum() {
        assertEquals(2, service.getPostsLimitByNum(2).size());
    }

    /**
     * Test of getAllPosts method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllPosts() {
        assertEquals(2, service.getAllPosts().size());
    }

    /**
     * Test of getStatusById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetStatusById() {
        Status status = service.getStatusById(1);
        assertNotNull(status);
        status = service.getStatusById(3);
        assertNull(status);
    }

    /**
     * Test of getAllStatuses method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllStatuses() {
        assertEquals(2, service.getAllStatuses().size());
    }

    /**
     * Test of addTag method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAddTag() {
        Tag tag = new Tag();
        tag.setName("Java");
        
        service.addTag(tag);
    }

    /**
     * Test of deleteTag method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testDeleteTag() {
        service.deleteTag(1);
    }

    /**
     * Test of updateTag method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testUpdateTag() {
        Tag tag = new Tag();
        tag.setTagId(1);
        tag.setName("Java");

        service.updateTag(tag);
    }

    /**
     * Test of getTagById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetTagById() {
        Tag tag = service.getTagById(1);
        assertNotNull(tag);
        tag = service.getTagById(2);
        assertNull(tag);
    }

    /**
     * Test of getTagsLimitByNum method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetTagsLimitByNum() {
        assertEquals(1, service.getTagsLimitByNum(1).size());
    }

    /**
     * Test of getAllTags method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllTags() {
        assertEquals(1, service.getAllTags().size());
    }

    /**
     * Test of addUser method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");

        service.addUser(user);
    }

    /**
     * Test of deleteUser method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testDeleteUser() {
        service.deleteUser("admin");
    }

    /**
     * Test of getUser method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetUser() {
        User user = service.getUser("admin");
        assertNotNull(user);
        user = service.getUser("user");
        assertNull(user);
    }

    /**
     * Test of getUserById method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetUserById() {
        User user = service.getUserById(1);
        assertNotNull(user);
        user = service.getUserById(2);
        assertNull(user);
    }

    /**
     * Test of getUsersLimitByNum method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetUsersLimitByNum() {
        assertEquals(1, service.getUsersLimitByNum(1).size());
    }

    /**
     * Test of getAllUsers method, of class CMSBlogServiceLayer.
     */
    @Test
    public void testGetAllUsers() {
        assertEquals(1, service.getAllUsers().size());
    }
}
