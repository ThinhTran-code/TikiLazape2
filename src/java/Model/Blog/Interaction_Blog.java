/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Blog;

import Model.User.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hbtth
 */
public class Interaction_Blog {
    private int interaction_blog_id;
    private int user_id;
    private int blog_id;
    private boolean blog_isReaction;
    private User user;
    private Blog blog;
    private List<Interaction_Blog_Comment> listComment;
    public Interaction_Blog() {
        listComment = new ArrayList();
    }

    public Interaction_Blog(int interaction_blog_id, int user_id, int blog_id, boolean blog_isReaction) {
        this.interaction_blog_id = interaction_blog_id;
        this.user_id = user_id;
        this.blog_id = blog_id;
        this.blog_isReaction = blog_isReaction;
    }

    public int getInteraction_blog_id() {
        return interaction_blog_id;
    }

    public void setInteraction_blog_id(int interaction_blog_id) {
        this.interaction_blog_id = interaction_blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public boolean isBlog_isReaction() {
        return blog_isReaction;
    }

    public void setBlog_isReaction(boolean blog_isReaction) {
        this.blog_isReaction = blog_isReaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Interaction_Blog_Comment> getListComment() {
        return listComment;
    }

    public void setListComment(List<Interaction_Blog_Comment> listComment) {
        this.listComment = listComment;
    }
    
    
}
