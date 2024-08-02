/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Product.FilterProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterDAO extends dbConfig {

    public FilterDAO() {
        super();
    }

    public String getFilterOfCategories(int category_id) {
        String sql = "select [Filters].filter_id , [Filters].category_id,[Filters].nameFilter from [Filters] \n"
                + "JOIN [Categories] on [Categories].category_id = [Filters].category_id \n"
                + "where [Categories].category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<FilterProduct> getListFilter(int category_id) {
        List<FilterProduct> listF = new ArrayList();

        String sql = "select * from  [Filters]\n"
                + "where [Filters].category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int filter_id = rs.getInt(1);
                String nameFilter = rs.getString(3);
                FilterProduct filter = new FilterProduct();
                filter.setCategory_id(category_id);
                filter.setFilter_id(filter_id);
                filter.setNameFilter(nameFilter);
                listF.add(filter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listF;
    }

    public List<FilterProduct> getListFilter(int category_id, int filter_id) {
        List<FilterProduct> listf = new ArrayList();

        String sql = "select * from  [Filters]\n"
                + "where [Filters].category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                filter_id = rs.getInt(1);
                String nameFilter = rs.getString(3);
                FilterProduct filter = new FilterProduct();
                filter.setCategory_id(category_id);
                filter.setFilter_id(filter_id);
                filter.setNameFilter(nameFilter);
                listf.add(filter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listf;
    }

    public List<FilterProduct> getListFilterForSearch(String txt, int category_id) {
        List<FilterProduct> listf = new ArrayList();
        String sql = """
                     WITH SearchProducts AS (
                                                             SELECT 
                                                distinct p.category_id,
                     \t\t\t\t\t\t   p.filter_id,
                                                              ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                                         FROM 
                                                                 [Products] p
                                                              JOIN
                                                              [ImageProducts] i ON p.product_id = i.product_id
                                              
                                          where p.product_name like ?
                                                          )
                                                          
                                                           SELECT  
                                                   f.nameFilter,
                                                   f.filter_id
                                                             FROM
                                                                 SearchProducts s
                                             JOIN [Filters] f ON s.filter_id = f.filter_id
                     \t\t\t\t\t\tjoin [Categories] c on f.category_id = c.category_id
                                                            WHERE 
                                                                 rn = 1 and c.category_id = ?;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String nameFilter = rs.getString(1);
                int idFilter = rs.getInt(2);
                FilterProduct filter = new FilterProduct();
                filter.setNameFilter(nameFilter);
                filter.setFilter_id(idFilter);
                listf.add(filter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listf;
    }

    public List<FilterProduct> getListAllFilter() {
        List<FilterProduct> listF = new ArrayList();

        String sql = """
                     select * from  [Filters]
                     """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int filter_id = rs.getInt(1);
                int category_id = rs.getInt(2);
                String nameFilter = rs.getString(3);
                FilterProduct filter = new FilterProduct();
                filter.setCategory_id(category_id);
                filter.setFilter_id(filter_id);
                filter.setNameFilter(nameFilter);
                listF.add(filter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listF;
    } 
    public void addFilter(int category_id, String filter_name){
        try{
        String sql="INSERT INTO [dbo].[Filters]\n" +
"           ([category_id]\n" +
"           ,[nameFilter])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?)";
        PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setString(2,filter_name);
            int x = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        FilterDAO df = new FilterDAO();
    }
}
