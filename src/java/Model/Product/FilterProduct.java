/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;

import java.util.List;

/**
 *
 * @author hbtth
 */
public class FilterProduct {
    private int filter_id;
    private int category_id;
    private String nameFilter;
    private List<Product> listProduct;

    public FilterProduct() {
    }

  

    public FilterProduct(int filter_id, int category_id, String nameFilter, List<Product> listProduct) {
        this.filter_id = filter_id;
        this.category_id = category_id;
        this.nameFilter = nameFilter;
        this.listProduct = listProduct;
        
    }

  

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
    
    
    
}
