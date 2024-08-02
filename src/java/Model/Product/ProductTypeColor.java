/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

/**
 *
 * @author hbtth
 */
public class ProductTypeColor {
    private int productType_id;
    private int product_id;
    private int type_id;
    private int color_id;
    private int quantity;

    public ProductTypeColor() {
    }

    public ProductTypeColor(int productType_id, int product_id, int type_id, int color_id, int quantity) {
        this.productType_id = productType_id;
        this.product_id = product_id;
        this.type_id = type_id;
        this.color_id = color_id;
        this.quantity = quantity;
    }

    
    
    public int getProductType_id() {
        return productType_id;
    }

    public void setProductType_id(int productType_id) {
        this.productType_id = productType_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
