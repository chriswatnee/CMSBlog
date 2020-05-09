/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author chris
 */
public class Post {
    
    private int postId;
    private String title;
    private String content;
    private String excerpt;
    private LocalDate creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate liveDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;
    private int userId;
    private Category category;
    private Status status;
    private List<Tag> tags;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(LocalDate liveDate) {
        this.liveDate = liveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.postId;
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + Objects.hashCode(this.content);
        hash = 19 * hash + Objects.hashCode(this.excerpt);
        hash = 19 * hash + Objects.hashCode(this.creationDate);
        hash = 19 * hash + Objects.hashCode(this.liveDate);
        hash = 19 * hash + Objects.hashCode(this.expirationDate);
        hash = 19 * hash + this.userId;
        hash = 19 * hash + Objects.hashCode(this.category);
        hash = 19 * hash + Objects.hashCode(this.status);
        hash = 19 * hash + Objects.hashCode(this.tags);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.excerpt, other.excerpt)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.liveDate, other.liveDate)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }
}
