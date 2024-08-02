/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ShoppingCart;

import java.util.List;

/**
 *
 * @author hbtth
 */
public class ShoppingCart {
    private int shoppingCart_id;
    private List<ItemCart> listItem;

    public ShoppingCart() {
    }

    public ShoppingCart(int shoppingCart_id, List<ItemCart> listItem) {
        this.shoppingCart_id = shoppingCart_id;
        this.listItem = listItem;
    }

    public int getShoppingCart_id() {
        return shoppingCart_id;
    }

    public void setShoppingCart_id(int shoppingCart_id) {
        this.shoppingCart_id = shoppingCart_id;
    }

    public List<ItemCart> getListItem() {
        return listItem;
    }

    public void setListItem(List<ItemCart> listItem) {
        this.listItem = listItem;
    }
    
    
}
