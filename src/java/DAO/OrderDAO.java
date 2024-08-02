/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Order.Order;
import Model.Order.OrderDetail;
import Model.Product.Color;
import Model.Product.Product;
import Model.Product.Type;
import Model.ShoppingCart.ItemCart;
import Model.User.Store;
import Model.User.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hbtth
 */
public class OrderDAO extends dbConfig {

    public OrderDAO() {
        super();
    }
    
        
    public OrderDetail getOrderDetailById(int orderDetail_id){
        getStatusFeedback(orderDetail_id);
        ProductDAO db = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        TypeDAO td = new TypeDAO();
        
        String sql = "select * from OrderDetails where orderDetai_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderDetail_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderDetail_id = rs.getInt("orderDetail_id");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt(3);
                int type_id = rs.getInt(4);
                int color_id = rs.getInt(5);
                int unitPrice = rs.getInt(6);
                int quantityProduct = rs.getInt(7);
                long intoPrice = rs.getLong(8);
                boolean isFeedback = rs.getBoolean(9);
                Product product = db.getProductByID(product_id);
                Type type = td.getTypeByID(type_id);
                Color color = cd.getColorByID(color_id);
                OrderDetail o = new OrderDetail();
                o.setOrderDetail_id(orderDetail_id);
                o.setOrder_id(order_id);
                o.setColor_id(color_id);
                o.setType_id(type_id);
                o.setProduct_id(product_id);
                o.setUnitPrice(unitPrice);
                o.setQuantityProduct(quantityProduct);
                o.setIntoPrice(intoPrice);
                o.setProduct(product);
                o.setColor(color);
                o.setType(type);
                o.setIsFeedback(isFeedback);
                return o;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Integer> placeOrder(String[] list, int payment_method) {
        ShoppingCartDAO db = new ShoppingCartDAO();
        ProductDAO pd = new ProductDAO();
        List<ItemCart> listItem = db.getItems(list);
        Map<ItemCart, Integer> map = new HashMap();
        Map<Integer, Integer> listID = new HashMap();
        Set<Integer> listOrderID = new HashSet();
        Set<Integer> set = new HashSet();
        for (ItemCart i : listItem) {
            int store_id = pd.getStoreIDByProductID(i.getProduct_id());
            set.add(store_id);
            map.put(i, store_id);
        }
        for (int i : set) {
            int order_id = createOrder(listItem.get(0).getShoppingCart_id(), i, payment_method);
            listID.put(i, order_id);
            listOrderID.add(order_id);
        }
        for (Map.Entry<ItemCart, Integer> i : map.entrySet()) {
            int order_id = listID.get(i.getValue());
            db.deleteItemFromCart(i.getKey().getCartItem_id());
            createOrderDetail(i.getKey(), order_id);
        }
        return listOrderID.stream().toList();
    }

    private int createOrder(int customer_id, int store_id, int payment_method) {
        String sql;
        if (payment_method != 1) {
            sql = "INSERT INTO [dbo].[Orders]([customer_id],[store_id],[order_totalPrice],[order_payment_method])\n"
                    + "     VALUES (?,?,?,?)";
        } else {
            sql = "INSERT INTO [dbo].[Orders]([customer_id],[store_id],[order_totalPrice],[order_payment_method],[order_payment_status],[order_status])\n"
                    + "     VALUES (?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ps.setInt(2, store_id);
            ps.setInt(3, 0);
            ps.setInt(4, payment_method);
            if (payment_method == 1) {
                ps.setInt(5, 1);
                ps.setInt(6, 1);
            }
            int x = ps.executeUpdate();
            if (x != 0) {
                return getOrderID();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private int getOrderID() {
        String sql = "select max(Orders.order_id) as id from Orders";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void createOrderDetail(ItemCart item, int order_id) {
        ProductTypeColorDAO db = new ProductTypeColorDAO();
        String sql = "INSERT INTO [dbo].[OrderDetails]([order_id],[product_id],[type_id],[color_id],[unitPrice],[quantityProduct],[intoPrice])\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ps.setInt(2, item.getProduct_id());
            ps.setInt(3, item.getType_id());
            ps.setInt(4, item.getColor_id());
            ps.setInt(5, item.getUnitPrice());
            ps.setInt(6, item.getQuantityProduct());
            ps.setLong(7, item.getIntoPrice());
            int x = ps.executeUpdate();
            if (x != 0) {
                long total_order = getTotalOrder(order_id);
                total_order += item.getIntoPrice();
                int status = getOrderByOrderID(order_id).getOrder_status();
                if (status == 1) {
                    db.updateQuantityProduct(item.getProduct_id(), item.getColor_id(), item.getType_id(), item.getQuantityProduct() * -1);
                }
                updateTotalOrder(order_id, total_order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTotalOrder(int order_id, long total_order) {
        String sql = "UPDATE [dbo].[Orders] SET [order_totalPrice] = ? WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, total_order);
            ps.setInt(2, order_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getTotalOrder(int order_id) {
        String sql = "select order_totalPrice from [Orders] where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public List<Order> getOrderByUserID(int customer_id) {
        List<Order> list = new ArrayList<>();
        UserDAO db = new UserDAO();
        StoreDAO sd = new StoreDAO();
        String sql = "select * from Orders where customer_id = ? ORDER BY order_id DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                int store_id = rs.getInt(3);
                long order_totalPrice = rs.getInt(4);
                Date order_dateOrder = rs.getDate(5);
                int order_status = rs.getInt(6);
                int order_payment_method = rs.getInt(7);
                int order_payment_status = rs.getInt(8);
                List<OrderDetail> listOrderDetail = getListOrderDetailByOrderID(order_id);
                User user = db.getUserById(order_id);
                Store store = sd.getStoreByIdForViewStore(store_id);
                Order order = new Order(order_id, "", customer_id, store_id, order_totalPrice, order_dateOrder, order_status, listOrderDetail);
                order.setOrder_payment_method(order_payment_method);
                order.setOrder_payment_status(order_payment_status);
                order.setUser(user);
                order.setStore(store);
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Order getOrderByOrderID(int order_id) {
        getStatusFeedback(order_id);
        UserDAO db = new UserDAO();
        StoreDAO sd = new StoreDAO();
        String sql = "select * from Orders where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customer_id = rs.getInt(2);
                int store_id = rs.getInt(3);
                long order_totalPrice = rs.getInt(4);
                Date order_dateOrder = rs.getDate(5);
                int order_status = rs.getInt(6);
                int order_payment_method = rs.getInt(7);
                int order_payment_status = rs.getInt(8);
                List<OrderDetail> listOrderDetail = getListOrderDetailByOrderID(order_id);
                User user = db.getUserById(order_id);
                Store store = sd.getStoreByIdForViewStore(store_id);
                Order order = new Order(order_id, "", customer_id, store_id, order_totalPrice, order_dateOrder, order_status, listOrderDetail);
                order.setOrder_payment_method(order_payment_method);
                order.setOrder_payment_status(order_payment_status);
                order.setUser(user);
                order.setStore(store);
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<OrderDetail> getListOrderDetailByOrderID(int order_id) {
        getStatusFeedback(order_id);
        ProductDAO db = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        TypeDAO td = new TypeDAO();
        List<OrderDetail> list = new ArrayList();
        String sql = "select * from OrderDetails where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderDetail_id = rs.getInt(1);
                int product_id = rs.getInt(3);
                int type_id = rs.getInt(4);
                int color_id = rs.getInt(5);
                int unitPrice = rs.getInt(6);
                int quantityProduct = rs.getInt(7);
                long intoPrice = rs.getLong(8);
                boolean isFeedback = rs.getBoolean(9);
                Product product = db.getProductByID(product_id);
                Type type = td.getTypeByID(type_id);
                Color color = cd.getColorByID(color_id);
                OrderDetail o = new OrderDetail();
                o.setOrderDetail_id(orderDetail_id);
                o.setOrder_id(order_id);
                o.setColor_id(color_id);
                o.setType_id(type_id);
                o.setProduct_id(product_id);
                o.setUnitPrice(unitPrice);
                o.setQuantityProduct(quantityProduct);
                o.setIntoPrice(intoPrice);
                o.setProduct(product);
                o.setColor(color);
                o.setType(type);
                o.setIsFeedback(isFeedback);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void getStatusFeedback(int order_id) {
        String sql = "SELECT DATEDIFF(DAYOFYEAR,order_dateOrder, GETDATE()) FROM Orders\n"
                + "where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int diffDay = rs.getInt(1);
                if (diffDay > 3 || diffDay < -3) {
                    updateStatusFeedback(order_id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatusFeedback(int order_id) {
        String sql = "UPDATE OrderDetails\n"
                + "set isFeedback = 1\n"
                + "where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelOrderByID(int order_id) {
        String sql = "UPDATE Orders SET order_status = -2,"
                + " order_payment_status = -1\n"
                + "WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeStatusOrder(int order_id, int status) {
        if (status == 1) {
            updateQuantityProduct(order_id);
        }
        String sql = "UPDATE Orders SET order_status = ? "
                + "WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, order_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getListOrderByStore_id(int store_id, int status) {
        List<Order> list = new ArrayList<>();
        UserDAO db = new UserDAO();
        StoreDAO sd = new StoreDAO();
        String sql = "select * from Orders where store_id = ? ";
        if(status == -3){
            String next = "and 1 = 1 ORDER BY order_id DESC";
            sql += next;
        }else{
            String next = "and order_status = "+status+" ORDER BY order_id DESC";
            sql += next;
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, store_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                long order_totalPrice = rs.getInt(4);
                Date order_dateOrder = rs.getDate(5);
                int order_status = rs.getInt(6);
                int order_payment_method = rs.getInt(7);
                int order_payment_status = rs.getInt(8);
                List<OrderDetail> listOrderDetail = getListOrderDetailByOrderID(order_id);
                User user = db.getUserById(order_id);
                Store store = sd.getStoreByIdForViewStore(store_id);
                Order order = new Order(order_id, "", customer_id, store_id, order_totalPrice, order_dateOrder, order_status, listOrderDetail);
                order.setOrder_payment_method(order_payment_method);
                order.setOrder_payment_status(order_payment_status);
                order.setUser(user);
                order.setStore(store);
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void handleOrderCanceled(int order_id) {
        ProductTypeColorDAO db = new ProductTypeColorDAO();
        List<OrderDetail> list = getListOrderDetailByOrderID(order_id);
        list.stream().forEach(action -> db.updateQuantityProduct(action.getProduct_id(), action.getColor_id(), action.getType_id(), action.getQuantityProduct()));
    }

    
    
    
    private void updateQuantityProduct(int order_id) {
        ProductTypeColorDAO db = new ProductTypeColorDAO();
        List<OrderDetail> list = getListOrderDetailByOrderID(order_id);
        list.stream().forEach(action -> db.updateQuantityProduct(action.getProduct_id(), action.getColor_id(), action.getType_id(), action.getQuantityProduct() * -1));
    }

    public void repurchaseOrder(int order_id) {
        String sql = "select Orders.order_id, product_id, type_id, color_id, unitPrice, quantityProduct, intoPrice, store_id, customer_id \n"
                + "from Orders join OrderDetails on Orders.order_id = OrderDetails.order_id\n"
                + "where Orders.order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ShoppingCartDAO db = new ShoppingCartDAO();
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                int color_id = rs.getInt(4);
                int unitPrice = rs.getInt(5);
                int quantityProduct = rs.getInt(6);
                long intoPrice = rs.getLong(7);
                int store_id = rs.getInt(8);
                int shoppingCart_id = rs.getInt(9);
                db.addItemToCart(shoppingCart_id, product_id, type_id, unitPrice, quantityProduct, intoPrice, color_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void repurchaseOrder(int order_id, int orderDetailId) {
        String sql = "select Orders.order_id, product_id, type_id, color_id, unitPrice, quantityProduct, intoPrice, store_id, customer_id \n"
                + "from Orders join OrderDetails on Orders.order_id = OrderDetails.order_id\n"
                + "where Orders.order_id = ? and orderDetai_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ps.setInt(2, orderDetailId);
            ShoppingCartDAO db = new ShoppingCartDAO();
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(2);
                int type_id = rs.getInt(3);
                int color_id = rs.getInt(4);
                int unitPrice = rs.getInt(5);
                int quantityProduct = rs.getInt(6);
                long intoPrice = rs.getLong(7);
                int store_id = rs.getInt(8);
                int shoppingCart_id = rs.getInt(9);
                db.addItemToCart(shoppingCart_id, product_id, type_id, unitPrice, quantityProduct, intoPrice, color_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean confirmOrder(int order_id) {
        if (checkConfirmOrder(order_id) == false) {
            return false;
        }
        updateQuantityProduct(order_id);
        String sql = "UPDATE Orders SET order_status = ? "
                + "WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, order_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean checkConfirmOrder(int order_id) {
        String sql = "select o.orderDetai_id,quantityProduct, quantity from [OrderDetails] o \n"
                + "join ProductTypeColor p\n"
                + "on o.product_id = p.product_id and o.color_id = p.color_id and o.type_id = p.type_id\n"
                + "where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int quantity = rs.getInt(2);
                int available = rs.getInt(3);
                if(available - quantity < 0){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    public static void main(String[] args) {
        OrderDAO db = new OrderDAO();
        Order o = db.getOrderByOrderID(8);
        String[] l = {"35"};
        db.placeOrder(l, 1);
    }
}
