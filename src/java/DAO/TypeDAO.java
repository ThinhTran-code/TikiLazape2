/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Product.Product;
import Model.Product.Type;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hbtth
 */
public class TypeDAO extends dbConfig {

    public TypeDAO() {
        super();
    }

    public List<Type> getListTypeByProductID(int product_id) {
        List<Type> list = new ArrayList();
        String sql = " select distinct [Types].type_id, [Types].type_describes\n"
                + "from [Types] \n"
                + "left join [ProductTypeColor]\n"
                + "on [Types].type_id = ProductTypeColor.type_id\n"
                + "right join [Products]\n"
                + "on [ProductTypeColor].product_id = Products.product_id\n"
                + " where Products.product_id = ?";
        ColorDAO db = new ColorDAO();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int type_id = rs.getInt(1);
                String type_describe = rs.getString(2);
                Type type = new Type(type_id, product_id, type_describe);
                type.setListColor(db.getListColor(type_id, product_id));
                list.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Type> getListTypeByProductID(String product_id) {
        List<Type> list = new ArrayList();
        String sql = " select distinct [Types].type_id, [Types].type_describes \n"
                + " from [Types] join [ProductTypeColor] \n"
                + " on [Types].type_id = ProductTypeColor.type_id\n"
                + " join [Products]\n"
                + " on [ProductTypeColor].product_id = Products.product_id\n"
                + " where Products.product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int type_id = rs.getInt(1);
                String type_describe = rs.getString(2);
                list.add(new Type(type_id, Integer.parseInt(product_id), type_describe));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int quantityOfProductById(int id_product) {
        int quantity;
        String sql = "select sum(pt.quantity) \n"
                + "from [ProductTypeColor] pt\n"
                + "where pt.product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_product);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt(1);
                return quantity;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Type getTypeByID(int type_id) {
        String sql = "select * from Types where type_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, type_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Type type = new Type();
                String type_describe = rs.getString(2);
                type.setType_id(type_id);
                type.setType_describes(type_describe);
                return type;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Type> getListAllType() {
        List<Type> list = new ArrayList();
        String sql = """
                      select  * from [Types] 
                     """;
        ColorDAO db = new ColorDAO();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int type_id = rs.getInt(1);
                String type_describe = rs.getString(2);
                Type type = new Type(type_id, type_describe);
                list.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new TypeDAO().getListTypeByProductID(38).size());
    }
}
