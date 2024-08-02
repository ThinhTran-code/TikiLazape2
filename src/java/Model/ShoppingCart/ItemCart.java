/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ShoppingCart;

import Model.Product.Color;
import Model.Product.Product;
import Model.Product.Type;

/**
 *
 * @author hbtth
 */
public class ItemCart {
    private int cartItem_id;
    private int shoppingCart_id;
    private int product_id;
    private int type_id;
    private int color_id;
    private int unitPrice;
    private int quantityProduct;
    private long intoPrice;
    private Product product;
    private Type type;
    private Color color;
    public ItemCart() {
    }

    public ItemCart(int cartItem_id, int shoppingCart_id, int product_id, int type_id, int quantityProduct, long intoPrice) {
        this.cartItem_id = cartItem_id;
        this.shoppingCart_id = shoppingCart_id;
        this.product_id = product_id;
        this.type_id = type_id;
        this.quantityProduct = quantityProduct;
        this.intoPrice = intoPrice;
    }

    public int getCartItem_id() {
        return cartItem_id;
    }

    public void setCartItem_id(int cartItem_id) {
        this.cartItem_id = cartItem_id;
    }

    public int getShoppingCart_id() {
        return shoppingCart_id;
    }

    public void setShoppingCart_id(int shoppingCart_id) {
        this.shoppingCart_id = shoppingCart_id;
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

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public long getIntoPrice() {
        return intoPrice;
    }

    public void setIntoPrice(long intoPrice) {
        this.intoPrice = intoPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
