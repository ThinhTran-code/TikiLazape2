/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hbtth
 */
public class Type {
    private int type_id;
    private int product_id;
    private String type_describes;
    private List<Color> listColor;
    public Type() {
        listColor = new ArrayList();
    }

    public Type(int type_id, String type_describes) {
        this.type_id = type_id;
        this.type_describes = type_describes;
    }

    public Type(int type_id, int product_id, String type_describes) {
        this.type_id = type_id;
        this.product_id = product_id;
        this.type_describes = type_describes;
    }

    
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_describes() {
        return type_describes;
    }

    public void setType_describes(String type_describes) {
        this.type_describes = type_describes;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public List<Color> getListColor() {
        return listColor;
    }

    public void setListColor(List<Color> listColor) {
        this.listColor = listColor;
    }
    
    
}
