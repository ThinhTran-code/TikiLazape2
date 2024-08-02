/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Product.ProductTypeColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class ProductTypeColorDAO extends dbConfig {

    public ProductTypeColorDAO() {
        super();
    }

    public List<ProductTypeColor> getAllOfProductTypeColor(int product_id) {
        List<ProductTypeColor> list = new ArrayList();
        String sql = "select t.type_describes,c.color_name,c.color_id , ptc.product_id , ptc.productTypeColor_id,ptc.quantity,ptc.type_id\n"
                + "from ProductTypeColor ptc Join Products p on p.product_id = ptc.product_id\n"
                + "join Types t on t.type_id = ptc.type_id\n"
                + "join Color c on c.color_id = ptc.color_id\n"
                + "where ptc.product_id= ?	";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int color_id = rs.getInt(3);
                product_id = rs.getInt(4);
                int productTypeColor_id = rs.getInt(5);
                int quantity = rs.getInt(6);
                int type_id = rs.getInt(7);

                ProductTypeColor product = new ProductTypeColor();

                product.setColor_id(color_id);
                product.setProductType_id(product_id);
                product.setProduct_id(product_id);
                product.setQuantity(quantity);
                product.setType_id(type_id);

                list.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ProductTypeColor getProductTypeColorId(int product_id, int type_id, int color_id) {
        String sql = "SELECT * FROM ProductTypeColor WHERE product_id = ? AND type_id = ? AND color_id = ?";
        ProductTypeColor typeColor = null;

        try (PreparedStatement ptcStm = con.prepareStatement(sql)) {
            ptcStm.setInt(1, product_id);
            ptcStm.setInt(2, type_id);
            ptcStm.setInt(3, color_id);

            try (ResultSet rs = ptcStm.executeQuery()) {
                if (rs.next()) {
                    typeColor = new ProductTypeColor();
                    typeColor.setProductType_id(rs.getInt("productTypeColor_id"));
                    typeColor.setProduct_id(rs.getInt("product_id"));
                    typeColor.setType_id(rs.getInt("type_id"));
                    typeColor.setColor_id(rs.getInt("color_id"));
                    typeColor.setQuantity(rs.getInt("quantity"));
                    // Thêm các trường khác của ProductTypeColor nếu cần thiết
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting ProductTypeColor: " + e.getMessage());
        }

        return typeColor;
    }

    public List<ProductTypeColor> getProductTypeColorByProduct_ID(int product_id) {

        String sql = "select * from ProductTypeColor where product_id = ?";
        List<ProductTypeColor> list = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productTypeColor_id = rs.getInt(1);
                int type_id = rs.getInt(3);
                int color_id = rs.getInt(4);
                int quantity = rs.getInt(5);
                ProductTypeColor ptc = new ProductTypeColor(productTypeColor_id, product_id, type_id, color_id, quantity);
                list.add(ptc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getQuantity(int product_id, int color_id, int type_id) {
        String sql = "select quantity from [ProductTypeColor] where product_id = ? and type_id = ? and color_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ps.setInt(2, type_id);
            ps.setInt(3, color_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateQuantityProduct(int product_id, int color_id, int type_id, int quantity) {
        String sql = "UPDATE [dbo].[ProductTypeColor]\n"
                + "SET [quantity] = ?\n"
                + "WHERE product_id = ? and color_id = ? and type_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            int quantity_new = getQuantity(product_id, color_id, type_id) + quantity;
            ps.setInt(1, quantity_new);
            ps.setInt(2, product_id);
            ps.setInt(3, color_id);
            ps.setInt(4, type_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
