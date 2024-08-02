/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;
import Model.Image.ImageFeedback;
import Model.User.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hbtth
 */
public class Feedback {
    private int feedback_id;
    private int customer_id;
    private int product_id;
    private int feedback_rateStars;
    private String feedback_comment;
    private Date feedback_commentDate;
    private Date feedback_modifed;
    private List<ImageFeedback> listImage;
    private User user;
    private Product product;
    public Feedback() {
        listImage = new ArrayList();
    }

    public Feedback(int feedback_id, int customer_id, int feedback_rateStars, String feedback_comment, Date feedback_commentDate) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.feedback_rateStars = feedback_rateStars;
        this.feedback_comment = feedback_comment;
        this.feedback_commentDate = feedback_commentDate;
    }

    
    
    public Feedback(int feedback_id, int customer_id, int feedback_rateStars, String feedback_comment, Date feedback_commentDate, Date feedback_modifed) {
        this.feedback_id = feedback_id;
        this.customer_id = customer_id;
        this.feedback_rateStars = feedback_rateStars;
        this.feedback_comment = feedback_comment;
        this.feedback_commentDate = feedback_commentDate;
        this.feedback_modifed = feedback_modifed;
    }

    
    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getFeedback_rateStars() {
        return feedback_rateStars;
    }

    public void setFeedback_rateStars(int feedback_rateStars) {
        this.feedback_rateStars = feedback_rateStars;
    }

    public String getFeedback_comment() {
        return feedback_comment;
    }

    public void setFeedback_comment(String feedback_comment) {
        this.feedback_comment = feedback_comment;
    }

    public Date getFeedback_commentDate() {
        return feedback_commentDate;
    }

    public void setFeedback_commentDate(Date feedback_commentDate) {
        this.feedback_commentDate = feedback_commentDate;
    }

    public Date getFeedback_modifed() {
        return feedback_modifed;
    }

    public void setFeedback_modifed(Date feedback_modifed) {
        this.feedback_modifed = feedback_modifed;
    }

    public List<ImageFeedback> getListImage() {
        return listImage;
    }

    public void setListImage(List<ImageFeedback> listImage) {
        this.listImage = listImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
     
}
