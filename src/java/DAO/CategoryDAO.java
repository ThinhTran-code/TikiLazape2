/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO extends dbConfig {

    public CategoryDAO() {
        super();
    }

    public List<Category> getListCategoryForHomePage() {
        List<Category> listCata = new ArrayList<>();
        String sql = "select * from [Categories]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                Category category = new Category();
                category.setCategory_id(category_id);
                category.setCategory_name(name);
                category.setNameImage(image);
                listCata.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCata;
    }

    public Category getCategoryById(int id) {
        Category category = new Category();
        String sql = "select * from [Categories]\n"
                + "where category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                category.setCategory_id(id);
                String name = rs.getString(2);
                String imageUrl = rs.getString(3);
                category.setCategory_name(name);
                category.setNameImage(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public int getNumberOfCata() {
        String sql = "select COUNT(*) as numberOfCatalog from [Categories]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int numberOfCata = rs.getInt(1);
                return numberOfCata;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Category> getListCategoryForManageBlogSeller(int id_user) {
        List<Category> list = new ArrayList();
        String sql = "SELECT Distinct( b.category_id), c.name, c.nameImage\n"
                + "  FROM [Tikilazapee].[dbo].[Blog] b\n"
                + "  join Categories c on b.category_id = c.category_id\n"
                + "  Where user_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Category> getListCataForAdmin() {
        List<Category> listCata = new ArrayList();
        try {

            String sql = "SELECT * from Categories\n";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                String category_image = rs.getString(3);
                Category category = new Category(category_id, category_name, category_image);
                listCata.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCata;
    }

    public int DeleteCategory(String cid) {
        String sql = "Delete from Categories where category_id = ?";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cid);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;

    }

    public List<Category> getListCataSortByNameAZ() {
        List<Category> listCata = new ArrayList();
        try {

            String sql = "SELECT * from Categories\n"
                    + "Order by name ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                String category_image = rs.getString(3);
                Category category = new Category(category_id, category_name, category_image);
                listCata.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCata;
    }

    public void insertCata(String nameCata, String nameImage) {
        try {
            String sql = "INSERT INTO [Categories]([name], [nameImage]) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameCata);
            ps.setString(2, nameImage);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) {
        try {
            String sql = "Update Categories Set name = ?, nameImage = ?  where category_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category.getCategory_name());
            ps.setString(2, category.getNameImage());
            ps.setInt(3, category.getCategory_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> searchCataForAdmin(String txtSearch) {
        List<Category> listCata = new ArrayList();
        try {

            String sql = "SELECT * from Categories\n"
                    + "	where Categories.name like ?\n";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Cata = rs.getInt(1);
                String nameCata = rs.getString(2);
                String imageName = rs.getString(3);
                Category category = new Category(id_Cata, nameCata, imageName);
                listCata.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCata;
    }

    public String getCategoriesByID(int category_id) {
        String sql = "select [Categories].name from [Categories]\n"
                + "where [Categories].category_id =?";
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

    public List<Category> getListCategory() {
        List<Category> list = new ArrayList();
        String sql = "select * from  [Categories]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Category> getListCategoryForStore(int id_user) {
        List<Category> list = new ArrayList();
        String sql = "	select DISTINCT(c.category_id), c.name, c.nameImage from Categories c\n"
                + "	join Products p on c.category_id = p.category_id\n"
                + "	where p.store_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public String getCategory(int idProduct) {
        Category CategoryP = new Category();
        String sql = """
                     select c.name from Categories c
                     JOIN Products p on c.category_id = p.category_id
                     where p.product_id = ?;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                String category_image = rs.getString(3);
                Category category = new Category();
                category.setCategory_id(category_id);
                category.setCategory_name(category_name);
                category.setNameImage(category_image);
                list.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Category> getListCategoryForSearch(String txt) {
        List<Category> list = new ArrayList<>();
        String sql = """
                    WITH SearchProducts AS (
                                                            SELECT 
                                              distinct p.category_id,
                                                             ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                                        FROM 
                                                                [Products] p
                                                             JOIN
                                                             [ImageProducts] i ON p.product_id = i.product_id
                    
                                        where p.product_name like ?
                                                         )
                                                         
                                                          SELECT  
                                               c.category_id,
                                        c.name
                                                            FROM
                                                                SearchProducts s
                    JOIN [Categories] c ON s.category_id = c.category_id
                                                           WHERE 
                                                                rn = 1;""";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                Category category = new Category();
                category.setCategory_id(category_id);
                category.setCategory_name(category_name);
                list.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static void main(String[] args) {
        CategoryDAO db = new CategoryDAO();
        String name = "123";
        String image ="123";
        int id = 1021;
        Category c = new Category(id, name, image);
        db.updateCategory(c);
    }
}
