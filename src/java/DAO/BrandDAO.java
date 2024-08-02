/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product.Brand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Asus
 */
public class BrandDAO extends dbConfig {
    public BrandDAO(){
    super();
    }
    
    
    public String getNameBrandByID(int idBrand){
        String sql = "SELECT Brands.brandName FROM [Brands] WHERE Brands.brand_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBrand);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    public List<Brand> getAllBrand(){
        String sql = """
                     select *
                     from Brands""";
        ArrayList<Brand> brandList = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand();
            brand.setBrand_id(rs.getInt("brand_id"));
            brand.setBrand_name(rs.getString("brandName"));
            brandList.add(brand);
        }
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(BrandDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        return brandList;
    }
    
    public static void main(String[] args) {
        System.out.println(new BrandDAO().getAllBrand().size());
    }
}
