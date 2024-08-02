/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Blog;

import Model.Product.Category;
import Model.User.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hbtth
 */
public class Blog {
   private int blog_id;
   private int user_id;
   private int category_id;
   private String blog_title;
   private String blog_content;
   private String blog_image;
   private java.sql.Date blog_create_day;
   private User user;
   private Category category;
   private List<Interaction_Blog> listInteraction;
   private List<Interaction_Blog_Comment> listComment;
    public Blog() {
        listInteraction = new ArrayList();
        listComment = new ArrayList<>();
    }

    public Blog(int blog_id, int user_id, int category_id, String blog_title, String blog_content, String blog_image, Date blog_create_day) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.blog_title = blog_title;
        this.blog_content = blog_content;
        this.blog_image = blog_image;
        this.blog_create_day = blog_create_day;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_content() {
        return blog_content;
    }

    public void setBlog_content(String blog_content) {
        this.blog_content = blog_content;
    }

    public String getBlog_image() {
        return blog_image;
    }

    public void setBlog_image(String blog_image) {
        this.blog_image = blog_image;
    }

    public Date getBlog_create_day() {
        return blog_create_day;
    }

    public void setBlog_create_day(Date blog_create_day) {
        this.blog_create_day = blog_create_day;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Interaction_Blog> getListInteraction() {
        return listInteraction;
    }

    
    public void setListInteraction(List<Interaction_Blog> listInteraction) {
        this.listInteraction = listInteraction;
    }

    public List<Interaction_Blog_Comment> getListComment() {
        return listComment;
    }

    public void setListComment(List<Interaction_Blog_Comment> listComment) {
        this.listComment = listComment;
    }
   
}

