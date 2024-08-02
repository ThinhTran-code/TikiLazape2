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
public class WishList {
    private int user_id;
    private List<Product> listWishList;

    public WishList() {
    }

    public WishList(int user_id, List<Product> listWishList) {
        this.user_id = user_id;
        this.listWishList = listWishList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Product> getListWishList() {
        return listWishList;
    }

    public void setListWishList(List<Product> listWishList) {
        this.listWishList = listWishList;
    }
    
}
