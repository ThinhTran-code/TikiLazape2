/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Image;

/**
 *
 * @author hbtth
 */
public class ImageFeedback {
    private int imageFeedback_id;
    private int feedback_id;
    private String imagaFeedback_url;
    
    public ImageFeedback() {
    }

    public ImageFeedback(int imageFeedback_id, int feedback_id, String imagaFeedback_url) {
        this.imageFeedback_id = imageFeedback_id;
        this.feedback_id = feedback_id;
        this.imagaFeedback_url = imagaFeedback_url;
    }

    public int getImageFeedback_id() {
        return imageFeedback_id;
    }

    public void setImageFeedback_id(int imageFeedback_id) {
        this.imageFeedback_id = imageFeedback_id;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getImagaFeedback_url() {
        return imagaFeedback_url;
    }

    public void setImagaFeedback_url(String imagaFeedback_url) {
        this.imagaFeedback_url = imagaFeedback_url;
    }
    
}
