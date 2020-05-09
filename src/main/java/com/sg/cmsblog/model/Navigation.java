/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class Navigation {
    
    private int navigationId;
    private String section;
    private List<Page> pages;

    public int getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(int navigationId) {
        this.navigationId = navigationId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.navigationId;
        hash = 79 * hash + Objects.hashCode(this.section);
        hash = 79 * hash + Objects.hashCode(this.pages);
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
        final Navigation other = (Navigation) obj;
        if (this.navigationId != other.navigationId) {
            return false;
        }
        if (!Objects.equals(this.section, other.section)) {
            return false;
        }
        if (!Objects.equals(this.pages, other.pages)) {
            return false;
        }
        return true;
    }
}
