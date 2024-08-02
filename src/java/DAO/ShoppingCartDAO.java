/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product.Color;
import Model.Product.Product;
import Model.Product.Type;
import Model.ShoppingCart.ItemCart;
import Model.ShoppingCart.ShoppingCart;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCartDAO extends dbConfig {

    public ShoppingCartDAO() {
        super();
    }

    public ShoppingCart getCart(int shoppingCart_id) {
        ShoppingCart cart = new ShoppingCart();
        TypeDAO typedao = new TypeDAO();
        ColorDAO colordao = new ColorDAO();
        ProductDAO db = new ProductDAO();
        cart.setShoppingCart_id(shoppingCart_id);
        List<ItemCart> list = new ArrayList();
        String sql = "select * from [CartItem] WHERE shoppingCart_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, shoppingCart_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cartItem_id = rs.getInt(1);
                int product_id = rs.getInt(3);
                int type_id = rs.getInt(4);
                int unit_price = rs.getInt(6);
                int color_id = rs.getInt(5);
                int quantityProduct = rs.getInt(7);
                long intoPrice = rs.getLong(8);
                ItemCart i = new ItemCart(cartItem_id, shoppingCart_id, product_id, type_id, quantityProduct, intoPrice);
                i.setColor_id(color_id);
                Color color = colordao.getColorByID(color_id);
                Type type = typedao.getTypeByID(type_id);
                i.setColor(color);
                i.setType(type);
                Product product = db.getProductByID(product_id);
                i.setProduct(product);
                i.setUnitPrice(unit_price);
                list.add(i);
            }
            cart.setListItem(list);
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public void changeQuantityItem(int cartItem_id, int quantity) {
        String sql = "EXEC change_quantity_ItemCart ?, ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartItem_id);
            ps.setInt(2, quantity);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int checkExistItem(int shoppingCart_id, int product_id, int type_id, int color_id) {
        String sql = "select * from CartItem \n"
                + "where shoppingCart_id = ? and product_id = ? and color_id = ? and type_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, shoppingCart_id);
            ps.setInt(2, product_id);
            ps.setInt(3, color_id);
            ps.setInt(4, type_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void addItemToCart(int shoppingCart_id, int product_id, int type_id, long unitPrice, int quantity, long intoPrice, int color_id) {
        int cartItem = checkExistItem(shoppingCart_id, product_id, type_id, color_id);
        if (cartItem != 0) {
            changeQuantityItem(cartItem, quantity);
        } else {
            String sql = "INSERT INTO [dbo].[CartItem]\n"
                    + "           ([shoppingCart_id]\n"
                    + "           ,[product_id]\n"
                    + "           ,[type_id]\n"
                    + "           ,[color_id]\n"
                    + "           ,[unitPrice]\n"
                    + "           ,[quantityProduct]\n"
                    + "           ,[intoPrice])"
                    + "     VALUES (?, ?, ?, ?, ?, ?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, shoppingCart_id);
                ps.setInt(2, product_id);
                ps.setInt(3, type_id);
                ps.setLong(4, color_id);
                ps.setLong(5, unitPrice);
                ps.setInt(6, quantity);
                ps.setLong(7, intoPrice);
                int x = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public int getCartItemID(){
        String sql = "select max(cartItem_id) id from CartItem";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public void deleteItemFromCart(int cartItem_id) {
        String sql = "DELETE FROM [dbo].[CartItem] WHERE cartItem_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartItem_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateTypeColorFromCart(ItemCart item) {
        ProductTypeColorDAO db = new ProductTypeColorDAO();
        int check = checkExistItem(item.getShoppingCart_id(), item.getProduct_id(), item.getType_id(), item.getColor_id());
        int quantity_current = getQuantityFromCart(item.getCartItem_id());
        int quantity_after_update = getQuantityFromCart(check) + quantity_current;
        int available = db.getQuantity(item.getProduct_id(), item.getColor_id(), item.getType_id());
        if (quantity_after_update <= available) {
            if (check != 0) {
                if (item.getCartItem_id() != check) {
                    changeQuantityItem(check, quantity_current);
                    deleteItemFromCart(item.getCartItem_id());
                }
            } else {
                updateColorAndType(item.getCartItem_id(), item.getType_id(), item.getColor_id());
            }
        }else{
            return false;
        }

        return true;
    }

    public int getQuantityFromCart(int cartItem_id) {
        String sql = "select quantityProduct from CartItem where cartItem_id = " + cartItem_id;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void updateColorAndType(int cartItem_id, int type_id, int color_id) {
        String sql = "UPDATE [dbo].[CartItem]\n"
                + "   SET  [type_id] = ? ,[color_id] = ?\n"
                + " WHERE cartItem_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, type_id);
            ps.setInt(2, color_id);
            ps.setInt(3, cartItem_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAllItemFromShoppingCart(int shoppingcart_id){
        String sql = "DELETE FROM [dbo].[CartItem] WHERE shoppingCart_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, shoppingcart_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<ItemCart> getItems(String[] listItem_id){
        List<ItemCart> list = new ArrayList();
        TypeDAO typedao = new TypeDAO();
        ColorDAO colordao = new ColorDAO();
        ProductDAO db = new ProductDAO();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(listItem_id).forEach(s -> sb.append(s).append(","));
        sb.replace(sb.length()-1, sb.length(), "");
        String sql = "select * from CartItem where cartItem_id in (" + sb.toString() +")";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cartItem_id = rs.getInt(1);
                int product_id = rs.getInt(3);
                int type_id = rs.getInt(4);
                int unit_price = rs.getInt(6);
                int color_id = rs.getInt(5);
                int quantityProduct = rs.getInt(7);
                long intoPrice = rs.getLong(8);
                ItemCart i = new ItemCart(cartItem_id, rs.getInt(2), product_id, type_id, quantityProduct, intoPrice);
                i.setColor_id(color_id);
                Color color = colordao.getColorByID(color_id);
                Type type = typedao.getTypeByID(type_id);
                i.setColor(color);
                i.setType(type);
                Product product = db.getProductByID(product_id);
                i.setProduct(product);
                i.setUnitPrice(unit_price);
                list.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int getTotalPrice(String[] listItem_id){
        StringBuilder sb = new StringBuilder();
        Arrays.stream(listItem_id).forEach(s -> sb.append(s).append(","));
        sb.replace(sb.length()-1, sb.length(), "");
        String sql = "select sum(intoPrice) quantity from CartItem where cartItem_id in ("+sb.toString()+")";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public static void main(String[] args) {
        ShoppingCartDAO db = new ShoppingCartDAO();
        String[] l = {"35"};
        System.out.println(db.getItems(l).get(0).getCartItem_id());
        
    }

}
