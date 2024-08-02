/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Image;

/**
 *
 * @author hbtth
 */
public class ImageSlider {
    private int slider_id;
    private int category_id;
    private String nameURL;

    public ImageSlider() {
    }

    
    public ImageSlider(int slider_id, int category_id, String nameURL) {
        this.slider_id = slider_id;
        this.category_id = category_id;
        this.nameURL = nameURL;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getNameURL() {
        return nameURL;
    }

    public void setNameURL(String nameURL) {
        this.nameURL = nameURL;
    }
       
}
