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
public class Color {
    private int color_id;
    private String color_name;
    private List<Type> listType;
    public Color() {
        listType = new ArrayList();
    }

    public Color(int color_id, String color_name) {
        this.color_id = color_id;
        this.color_name = color_name;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public List<Type> getListType() {
        return listType;
    }

    public void setListType(List<Type> listType) {
        this.listType = listType;
    }

}
