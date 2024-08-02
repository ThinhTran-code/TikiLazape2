/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import static DAO.dbConfig.con;
import Model.User.Store;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class StoreDAO extends dbConfig {

    public StoreDAO() {
        super();
    }

    public Store getStoreByIdForViewStore(int user_id) {

        String sql = "select * from [Stores]\n"
                + "where store_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String store_name = rs.getString(2);
                String store_phone = rs.getString(3);
                String store_address = rs.getString(4);
                String store_image = rs.getString(5);
                Store store = new Store(store_name, store_phone, store_address, store_image);

                return store;
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public int avgStarRate(int store_id) {
        String sql = "Select AVG([feedback_rateStars]) AS AverageRateStars\n"
                + "                FROM [dbo].[Feedbacks] f\n"
                + "				 join [Products] p on f.product_id = p.product_id\n"
                + "				 where p.store_id = ?";
        int avg = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, store_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                avg = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return avg;
    }

    public Store getAllInfoStoresByProductId(int product_id) {
        String sql = "select s.store_id , s.store_name , s.store_image , s.store_phone , s.store_address from Stores s\n"
                + "join Products p on p.store_id = s.store_id\n"
                + "where product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int store_id = rs.getInt(1);
                String store_name = rs.getString(2);
                String store_image = rs.getString(3);
                String store_phone = rs.getString(4);
                String store_address = rs.getString(5);
                Store stores = new Store();
                stores.setUser_id(store_id);
                stores.setStore_name(store_name);
                stores.setImage(store_image);
                stores.setStore_name(store_name);
                stores.setStore_image(store_image);
                stores.setStore_phone(store_phone);
                stores.setStore_address(store_address);

                return stores;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public Store getStoreByIdForSeller(int user_id) {

        String sql = "select s.store_name from Stores s join Users u on s.store_id = u.user_id where u.user_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String store_name = rs.getString(1);
                Store store = new Store();
                store.setStore_name(store_name);
                return store;
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    public static void main(String[] args) {
        int id = 3;
        StoreDAO dao = new StoreDAO();
        System.out.println(dao.getStoreByIdForSeller(id));
    }
    
    public String getNameStoreByProduct_ID(int product_id){
        Store store = getAllInfoStoresByProductId(product_id);
        return store.getStore_name();
    }

}
