/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Blog;

import Model.User.User;
import java.sql.Date;

/**
 *
 * @author hbtth
 */
public class Interaction_Blog_Comment {
    private int comment_id;
    private int blog_id;
    private int interaction_blog_id;
    private int user_id;
    private java.sql.Date comment_create_day;
    private String comment;
    private Interaction_Blog interaction_Blog;
    private User user;
    public Interaction_Blog_Comment() {
    }

    public Interaction_Blog_Comment(int comment_id, int interaction_blog_id, Date comment_create_day, String comment, Interaction_Blog interaction_Blog) {
        this.comment_id = comment_id;
        this.interaction_blog_id = interaction_blog_id;
        this.comment_create_day = comment_create_day;
        this.comment = comment;
        this.interaction_Blog = interaction_Blog;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getInteraction_blog_id() {
        return interaction_blog_id;
    }

    public void setInteraction_blog_id(int interaction_blog_id) {
        this.interaction_blog_id = interaction_blog_id;
    }

    public Date getComment_create_day() {
        return comment_create_day;
    }

    public void setComment_create_day(Date comment_create_day) {
        this.comment_create_day = comment_create_day;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Interaction_Blog getInteraction_Blog() {
        return interaction_Blog;
    }

    public void setInteraction_Blog(Interaction_Blog interaction_Blog) {
        this.interaction_Blog = interaction_Blog;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}

