/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Image;

/**
 *
 * @author hbtth
 */
public class ImageProduct {
    private int imageProduct_id;
    private int product_id;
    private String imageProduct_url;

    public ImageProduct() {
    }

    public ImageProduct(int imageProduct_id, int product_id, String imageProduct_url) {
        this.imageProduct_id = imageProduct_id;
        this.product_id = product_id;
        this.imageProduct_url = imageProduct_url;
    }

    
    
    public int getImageProduct_id() {
        return imageProduct_id;
    }

    public void setImageProduct_id(int imageProduct_id) {
        this.imageProduct_id = imageProduct_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImageProduct_url() {
        return imageProduct_url;
    }

    public void setImageProduct_url(String imageProduct_url) {
        this.imageProduct_url = imageProduct_url;
    }
        
}
