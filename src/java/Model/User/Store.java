/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

import Model.Product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hbtth
 */
public class Store extends User{
    private String store_name;
    private String store_phone;
    private String store_address;
    private String store_image;
    private List<Product> listproduct;
    public Store() {
        listproduct = new ArrayList();
    }

    
    public Store(String store_name, String store_phone, String store_address, String store_image) {
        this.store_name = store_name;
        this.store_phone = store_phone;
        this.store_address = store_address;
        this.store_image = store_image;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_image() {
        return store_image;
    }

    public void setStore_image(String store_image) {
        this.store_image = store_image;
    }

    public List<Product> getListproduct() {
        return listproduct;
    }

    public void setListproduct(List<Product> listproduct) {
        this.listproduct = listproduct;
    }

    @Override
    public String toString() {
        return "Store{" + "store_name=" + store_name + ", store_phone=" + store_phone + ", store_address=" + store_address + ", store_image=" + store_image + ", listproduct=" + listproduct + '}';
    }
    
    

}
