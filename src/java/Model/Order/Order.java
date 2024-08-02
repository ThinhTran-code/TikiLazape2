/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Order;
import Model.User.Store;
import Model.User.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hbtth
 */
public class Order {
    private int order_id;
    private String order_title;
    private int customer_id;
    private int store_id;
    private long order_totalPrice;
    private Date order_dateOrder;
    private int order_status;
    private int order_payment_method;
    private int order_payment_status;
    private List<OrderDetail> listOrderDetail;
    private User user;
    private Store store;
    public Order() {
        listOrderDetail = new ArrayList();
    }

    public Order(int order_id, String order_title, int customer_id, int store_id, long order_totalPrice, Date order_dateOrder, int order_status, List<OrderDetail> listOrderDetail) {
        this.order_id = order_id;
        this.order_title = order_title;
        this.customer_id = customer_id;
        this.store_id = store_id;
        this.order_totalPrice = order_totalPrice;
        this.order_dateOrder = order_dateOrder;
        this.order_status = order_status;
        this.listOrderDetail = listOrderDetail;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public long getOrder_totalPrice() {
        return order_totalPrice;
    }

    public void setOrder_totalPrice(long order_totalPrice) {
        this.order_totalPrice = order_totalPrice;
    }

    public Date getOrder_dateOrder() {
        return order_dateOrder;
    }

    public void setOrder_dateOrder(Date order_dateOrder) {
        this.order_dateOrder = order_dateOrder;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getOrder_payment_method() {
        return order_payment_method;
    }

    public void setOrder_payment_method(int order_payment_method) {
        this.order_payment_method = order_payment_method;
    }

    public int getOrder_payment_status() {
        return order_payment_status;
    }

    public void setOrder_payment_status(int order_payment_status) {
        this.order_payment_status = order_payment_status;
    }        
}
