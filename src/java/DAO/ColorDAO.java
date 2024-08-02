/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorDAO extends dbConfig {

    public ColorDAO() {
        super();
    }

    public List<Color> getListColorByProductID(int product_id) {
        List<Color> list = new ArrayList();
        String sql = " select distinct c.color_id, c.color_name from [ProductTypeColor] ptc join Color c on ptc.color_id = c.color_id\n"
                + "where product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int color_id = rs.getInt(1);
                String color_name = rs.getString(2);

                list.add(new Color(color_id, color_name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Color> getListColor(int type_id, int product_id) throws SQLException {
        String sql = "select  Color.color_id, color_name\n"
                + "from Color\n"
                + "left join [ProductTypeColor]\n"
                + "on color.color_id = ProductTypeColor.color_id\n"
                + "right join [Products]\n"
                + "on [ProductTypeColor].product_id = Products.product_id\n"
                + " where Products.product_id = ? and ProductTypeColor.type_id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, product_id);
        ps.setInt(2, type_id);

        ResultSet rs = ps.executeQuery();
        ArrayList<Color> colorList = new ArrayList<>();
        while (rs.next()) {
            Color color = new Color();
            color.setColor_id(rs.getInt("color_id"));
            color.setColor_name(rs.getString("color_name"));
            colorList.add(color);
        }
        return colorList;
    }

//    public List<Color> getListColor(int type_id, int product_id) {
//        String sql = "select  Color.color_id, color_name\n"
//                + "from Color\n"
//                + "left join [ProductTypeColor]\n"
//                + "on color.color_id = ProductTypeColor.color_id\n"
//                + "right join [Products]\n"
//                + "on [ProductTypeColor].product_id = Products.product_id\n"
//                + " where Products.product_id = ? and ProductTypeColor.type_id = ?;";
//        PreparedStatement ps;
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, product_id);
//            ps.setInt(2, type_id);
//
//            ResultSet rs = ps.executeQuery();
//            ArrayList<Color> colorList = new ArrayList<>();
//            while (rs.next()) {
//                Color color = new Color();
//                color.setColor_id(rs.getInt("color_id"));
//                color.setColor_name(rs.getString("color_name"));
//                colorList.add(color);
//                return colorList;
//            }
//
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(ColorDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public Color getColorByID(int color_id) {
        String sql = "select * from Color where color_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, color_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Color color = new Color();
                String color_name = rs.getString(2);
                color.setColor_name(color_name);
                color.setColor_id(color_id);
                return color;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ColorDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ColorDAO db = new ColorDAO();
        List<Color> list = db.getListColorByProductID(5);
        for (Color color : list) {
            System.out.println(color.getColor_name());
        }
    }
    public List<Color> getListAllColor() {
        String sql = """
                     select *
                     from Color""";
        ArrayList<Color> colorList = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Color color = new Color();
            color.setColor_id(rs.getInt("color_id"));
            color.setColor_name(rs.getString("color_name"));
            colorList.add(color);
        }
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(ColorDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        return colorList;

    }
}
