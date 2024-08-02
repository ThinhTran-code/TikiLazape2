/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Image.ImageProduct;
import Model.Image.ImageSlider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ImageDAO extends dbConfig {

    public ImageDAO() {
        super();
    }


    public String getImage(int idProduct) throws SQLException {
        String sql = """
                     WITH OrderedImages AS (
                         SELECT 
                             i.imageProduct_url,
                             ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn
                         FROM 
                             [Products] p
                         JOIN 
                             [ImageProducts] i ON p.product_id = i.product_id
                     \twhere i.product_id =?
                     )
                     SELECT 
                         imageProduct_url
                     FROM 
                         OrderedImages
                     WHERE 
                         rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ImageProduct> getImageOfProductID(int product_id) {
        List<ImageProduct> listI = new ArrayList<>();
        String sql = """
                     select i.imageProduct_url from ImageProducts i
                     JOIN Products p on i.product_id = p.product_id
                     where p.product_id =?;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String image_url = rs.getString(1);
                ImageProduct ip = new ImageProduct();
                ip.setImageProduct_url(image_url);
                listI.add(ip);
            }
        } catch (SQLException ex) {
        }
        return listI;
    }

    public List<ImageProduct> getImageOfProduct(int idProduct) {
        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "        p.product_name, \n"
                + "        p.category_id, \n"
                + "        p.product_originPrice,\n"
                + "		p.product_percentSale,\n"
                + "		p.quantity,\n"
                + "        i.imageProduct_url,\n"
                + "		\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		where p.product_id =?\n"
                + ")\n"
                + "SELECT \n"
                + "\n"
                + "    product_id, \n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "\n"
                + "WHERE \n"
                + "    rn = 1;";
        List<ImageProduct> listImage = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_imageProduct = rs.getInt(1);
                String nameImage = rs.getString(2);
                listImage.add(new ImageProduct(id_imageProduct, idProduct, nameImage));
            }
        } catch (SQLException ex) {
        }
        return listImage;

    }

    public List<ImageSlider> getListImageOfSlider(int category_id) {
        String sql = """
                     select ImageSliders.category_id,ImageSliders.nameURL,ImageSliders.slider_id from ImageSliders
                     join Categories on Categories.category_id = ImageSliders.category_id
                     where Categories.category_id = ?""";
        List<ImageSlider> listm = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int slider_id = rs.getInt(3);
                category_id = rs.getInt(1);
                String nameURL = rs.getString(2);
                ImageSlider image = new ImageSlider();
                image.setCategory_id(category_id);
                image.setNameURL(nameURL);
                image.setSlider_id(slider_id);
                listm.add(image);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listm;
    }

    public List<ImageProduct> getListImage(int idProduct) {
        String sql = "select [ImageProducts].imageProcduct_id , [ImageProducts].product_id,[ImageProducts].imageProduct_url , [Products].product_name from ImageProducts\n"
                + "Join [Products] on [ImageProducts].product_id = [Products].product_id \n"
                + "where [Products].product_id = ?";
        List<ImageProduct> listImage = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_imageProduct = rs.getInt(1);
                String nameImage = rs.getString(3);
                listImage.add(new ImageProduct(id_imageProduct, idProduct, nameImage));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listImage;
    }
    
    public static void main(String[] args) {
        ImageDAO im = new ImageDAO();
        List<ImageProduct> listi = new ArrayList<>();
        listi = im.getImageOfProductID(1);
        System.out.println(listi.size());

        
        
    }
}
