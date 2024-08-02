/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Order;

import Model.Product.Color;
import Model.Product.Product;
import Model.Product.Type;

/**
 *
 * @author hbtth
 */
public class OrderDetail {
    private int orderDetail_id;
    private int order_id;
    private int product_id;
    private int type_id;
    private int color_id;
    private int unitPrice;
    private int quantityProduct;
    private long intoPrice;
    private int status_orderDetails;
    private boolean isFeedback;
    private Product product;
    private Type type;
    private Color color;
    
    public OrderDetail() {
    }

    
    public OrderDetail(int orderDetail_id, int order_id, int product_id, int type_id, int quantityProduct, long inttoPrice, int status_orderDetails) {
        this.orderDetail_id = orderDetail_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.type_id = type_id;
        this.quantityProduct = quantityProduct;
        this.intoPrice = inttoPrice;
        this.status_orderDetails = status_orderDetails;
    }

    public int getOrderDetail_id() {
        return orderDetail_id;
    }

    public void setOrderDetail_id(int orderDetail_id) {
        this.orderDetail_id = orderDetail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public int getStatus_orderDetails() {
        return status_orderDetails;
    }

    public void setStatus_orderDetails(int status_orderDetails) {
        this.status_orderDetails = status_orderDetails;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getIntoPrice() {
        return intoPrice;
    }

    public void setIntoPrice(long intoPrice) {
        this.intoPrice = intoPrice;
    }

    public boolean isIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(boolean isFeedback) {
        this.isFeedback = isFeedback;
    }
    
}
