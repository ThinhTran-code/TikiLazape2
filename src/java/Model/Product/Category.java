/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

import Model.Image.ImageSlider;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author hbtth
 */
public class Category {
    private int category_id;
    private String category_name;
    private String nameImage;
    private List<FilterProduct> listFilter;
    private List<Product> listProduct;
    private List<ImageSlider> listImageSlider;
    public Category() {
        listFilter = new ArrayList();
        listProduct = new ArrayList();
        listImageSlider = new ArrayList();
    }

    public Category(int category_id, String category_name, String nameImage) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.nameImage = nameImage;
    }

    public Category(int category_id, String category_name, String nameImage, List<FilterProduct> listFilter) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.nameImage = nameImage;
        this.listFilter = listFilter;
    }

    
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public List<FilterProduct> getListFilter() {
        return listFilter;
    }

    public void setListFilter(List<FilterProduct> listFilter) {
        this.listFilter = listFilter;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public List<ImageSlider> getListImage() {
        return listImageSlider;
    }

    public void setListImage(List<ImageSlider> listImage) {
        this.listImageSlider = listImage;
    }
    
    
}
