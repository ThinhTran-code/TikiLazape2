/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Product.Product;
import java.util.List;
import Model.Image.ImageProduct;
import Model.Product.Color;
import Model.Product.ProductTypeColor;
import Model.Product.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.util.Arrays;

public class ProductDAO extends dbConfig {

    public ProductDAO() {
        super();
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

    public int getStockByProductCharacterics(int product_id, int type_id, int color_id) throws SQLException {

        String sql = "select sum(quantity) as stock\n"
                + "from [ProductTypeColor]\n"
                + "where product_id = ? and type_id = ? and color_id = ?;";
        PreparedStatement ppsm = con.prepareStatement(sql);
        ppsm.setInt(1, product_id);
        ppsm.setInt(2, type_id);
        ppsm.setInt(3, color_id);
        ResultSet rs = ppsm.executeQuery();
        int stock = 0;
        while (rs.next()) {
            stock = rs.getInt("stock");
        }
        return stock;
    }

    public Product getProductByIdProduct(int product_id) {
        String sql = "select p.product_id , p.category_id , p.product_name , p.brand_id , p.product_originPrice , p.product_percentSale ,  p.product_describes , p.store_id , i.imageProduct_url ,p.filter_id from [Products] p\n"
                + "join ImageProducts i on i.product_id = p.product_id\n"
                + "WHERE p.product_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String product_name = rs.getString(3);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                int filter_id = rs.getInt(10);
                String product_describes = rs.getString(7);
                String imageProduct = rs.getString(9);
                Product product = new Product();
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                TypeDAO type = new TypeDAO();
                List<Type> listT = type.getListTypeByProductID(product_id);
                ColorDAO color = new ColorDAO();
                List<Color> listColor = color.getListColorByProductID(product_id);
                ProductTypeColorDAO productdetail = new ProductTypeColorDAO();
                List<ProductTypeColor> productd = productdetail.getAllOfProductTypeColor(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listImage);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListType(listT);
                product.setListColor(listColor);

                product.setProduct_Describes(product_describes);
                product.setFilter_id(filter_id);

                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getProductByIdFilter(int filter_id) {
        List<Product> listProduct = new ArrayList();

        String sql = "SELECT p.product_id, p.category_id, p.product_name, p.brand_id, \n"
                + "       p.product_originPrice, p.product_percentSale, p.product_describes, \n"
                + "       p.store_id, i.imageProduct_url, p.filter_id\n"
                + "FROM Products p\n"
                + "JOIN (\n"
                + "    SELECT product_id, imageProduct_url, \n"
                + "           ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY product_id) AS row_num\n"
                + "    FROM ImageProducts\n"
                + ") i ON p.product_id = i.product_id AND i.row_num = 1\n"
                + "WHERE p.filter_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, filter_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString(3);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                filter_id = rs.getInt(10);
                String product_describes = rs.getString(7);
                String imageProduct = rs.getString(9);
                Product product = new Product();
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                TypeDAO type = new TypeDAO();
                List<Type> listT = type.getListTypeByProductID(product_id);
                ColorDAO color = new ColorDAO();
                List<Color> listColor = color.getListColorByProductID(product_id);
                ProductTypeColorDAO productdetail = new ProductTypeColorDAO();
                List<ProductTypeColor> productd = productdetail.getAllOfProductTypeColor(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listImage);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListType(listT);
                product.setListColor(listColor);

                product.setProduct_Describes(product_describes);
                product.setFilter_id(filter_id);

                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> GetProductofSeller(int id_user) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                                                  WITH OrderedImages AS (
                                                                                                                                              SELECT 
                                                                                                                                                  p.product_id, 
                                                                                                                                                  p.product_name,  
                                                                                                                                                  i.imageProduct_url,
                                                                                                                                          p.product_originPrice,
                                                                                                                                          p.product_percentSale,
                                                                                                                                              c.name,
                                                                           																   p.isActive,
                                                                                                                                                  ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn
                                                                                                                                              FROM 
                                                                                                                                                  [Products] p
                                                                                                                                                  join 
                                                                                                                                          Stores s on p.store_id = s.store_id
                                                                                                                                                  join 
                                                                                                                                          Users u on s.store_id = u.user_id
                                                                                                                                          JOIN 
                                                                                                                                                  [ImageProducts] i ON p.product_id = i.product_id
                                                                                                                                          JOIN
                                                                                                                                          Categories c on p.category_id = c.category_id
                                                                                                                                          WHERE u.user_id = ?
                                                                                                                                              
                                                                                                                                          )
                                                                                                                                          SELECT Top 100
                                                                                                                                              product_id, 
                                                                                                                                              product_name,
                                                                                                                                          name,
                                                                                                                                          imageProduct_url,
                                                                                                                                              product_originPrice,
                                                                                                                                          product_percentSale,
                                                                           															   isActive
                                                                                                                                          
                                                                                                                                          FROM
                                                                                                                                              OrderedImages
                                                                                                                                          WHERE 
                                                                                                                                              rn = 1;""";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                boolean isActive = rs.getBoolean(7);
                ImageDAO idb = new ImageDAO();
                String imageProduct = rs.getString(4);
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setCategory_id(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setIsActive(isActive);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getListProductForHomePage() {
        List<Product> listProduct = new ArrayList();

        String sql = "WITH RankedProducts AS (  \n"
                + "                                      SELECT   \n"
                + "                                          p.product_id,   \n"
                + "                                          p.product_name,   \n"
                + "                                          p.category_id,   \n"
                + "                                          p.product_originPrice,  \n"
                + "                                          p.product_percentSale,  \n"
                + "                                          c.[name] AS category_name,  \n"
                + "                                          i.imageProduct_url,  \n"
                + "                                          ROW_NUMBER() OVER (PARTITION BY p.category_id ORDER BY p.product_id) AS rn  \n"
                + "                                      FROM   \n"
                + "                                          [Products] p  \n"
                + "                                      JOIN   \n"
                + "                                          [ImageProducts] i ON p.product_id = i.product_id  \n"
                + "                                      JOIN   \n"
                + "                                          [Categories] c ON p.category_id = c.category_id  \n"
                + "										  where p.isActive =1\n"
                + "                                  ),  \n"
                + "                                  UniqueCategoryProducts AS (  \n"
                + "                                      SELECT   \n"
                + "                                          product_id,   \n"
                + "                                          product_name,   \n"
                + "                                          category_id,   \n"
                + "                                          product_originPrice,  \n"
                + "                                          product_percentSale,  \n"
                + "                                          category_name,  \n"
                + "                                          imageProduct_url  \n"
                + "                                      FROM   \n"
                + "                                          RankedProducts  \n"
                + "                                      WHERE   \n"
                + "                                          rn = 1  \n"
                + "                                  )  \n"
                + "                                  SELECT TOP 20  \n"
                + "                                      product_id,   \n"
                + "                                      product_name,   \n"
                + "                                  	category_id,  \n"
                + "                                      product_originPrice,  \n"
                + "                                      product_percentSale,  \n"
                + "                                      imageProduct_url  \n"
                + "                                  FROM   \n"
                + "                                      UniqueCategoryProducts;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(6);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListTopSalesProductForHomePage() {
        List<Product> listProduct = new ArrayList();

        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "        p.product_name, \n"
                + "        p.category_id, \n"
                + "        p.product_originPrice,\n"
                + "		p.product_percentSale,\n"
                + "		p.quantity,\n"
                + "        i.imageProduct_url,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		\n"
                + ")\n"
                + "SELECT Top 10\n"
                + "    product_id, \n"
                + "    product_name, \n"
                + "    category_id, \n"
                + "    product_originPrice,\n"
                + "	product_percentSale,\n"
                + "	quantity,\n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "\n"
                + "WHERE \n"
                + "    rn = 1\n"
                + "	ORDER BY product_percentSale DESC;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                int quantity = rs.getInt(6);
                String imageUrl = rs.getString(7);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListWishList(int userId) {
        List<Product> listProduct = new ArrayList();

        String sql = " SELECT             p.product_id, \n"
                + "                        p.product_name, \n"
                + "                        p.category_id, \n"
                + "                        p.product_originPrice,\n"
                + "                        p.product_percentSale,\n"
                + "                        c.[name] AS category_name,\n"
                + "                        ( select top 1 imageProduct_url from ImageProducts where product_id = p.product_id)\n"
                + "                    FROM \n"
                + "                        [Products] p\n"
                + "                    JOIN \n"
                + "                        [Categories] c ON p.category_id = c.category_id\n"
                + "						where p.product_id in (select product_id from Wishlist where customer_id = ?)";
        try {
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(7);
                System.out.println(originPrice);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public int avgStarRate(int product_id) {
        String sql = "SELECT AVG([feedback_rateStars]) AS AverageRateStars\n"
                + "FROM [dbo].[Feedbacks]\n"
                + "WHERE [product_id] = ?;";
        int avg = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                avg = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return avg;
    }

    public int totalProductOfCategory(int category_id) {
        String sql = "SELECT COUNT(*) AS TotalProducts\n"
                + "FROM [dbo].[Products]\n"
                + "WHERE [category_id] = ?;";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return total;
    }

    public int addWishList(int userId, String pid) {
        String sql = "INSERT INTO [dbo].[Wishlist]    ([customer_id] ,[product_id])  VALUES  (?,?)";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, pid);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteWishList(int userId, String pid) {
        String sql = "DELETE FROM [dbo].[Wishlist]  WHERE customer_id =? and product_id = ?";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, pid);
            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberPageForListAllProduct() {
        String sql = " select COUNT(*) from Products";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 12;
                if (total % 12 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberPageForListAllBlog() {
        String sql = " select COUNT(*) from Blog";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 8;
                if (total % 8 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberPageForListSaleProduct() {
        String sql = "select COUNT(*) from Products\n"
                + "where product_percentSale >=20";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 12;
                if (total % 12 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberOfProduct() {
        List<Product> listProduct = new ArrayList();
        String sql = "select * from [Products]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct.size();
    }

    public List<Product> getListProductForAdmin(int index) {
        List<Product> listProduct = new ArrayList();
        try {
            String sql = "select *\n"
                    + "from [Products]\n"
                    + "ORDER BY product_id\n"
                    + "Offset ? rows\n"
                    + "FETCH NEXT 12 Rows only";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(6);
                long originPrice = rs.getInt(7);
                int salePercent = rs.getInt(8);
                int id_Brand = rs.getInt(4);
                int id_Cata = rs.getInt(2);
                int store_id = rs.getInt(5);
                java.sql.Date inputDay = rs.getDate(9);
                boolean isActive = rs.getBoolean(11);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                product.setIsActive(isActive);
                product.setStore_id(store_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListProductSortByPrice() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate, quantity\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY product_originPrice";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                int quantity = rs.getInt(8);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListProductSortByNameAZ() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate, quantity\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY product_name ASC";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                int quantity = rs.getInt(8);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListProductSortByQuantity() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate, quantity\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY quantity";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                int quantity = rs.getInt(8);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public Product getProduct(int productId) throws SQLException {
        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "		p.category_id,\n"
                + "        p.product_name, \n"
                + "         \n"
                + "        p.product_originPrice, \n"
                + "		p.product_percentSale,\n"
                + "        i.imageProduct_url,\n"
                + "\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		Where p.product_id = ?\n"
                + ")\n"
                + "SELECT \n"
                + "    product_id, \n"
                + "	category_id, \n"
                + "    product_name, \n"
                + "    \n"
                + "    product_originPrice, \n"
                + "	product_percentSale,\n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "WHERE \n"
                + "    rn = 1;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        Product product = null;
        while (rs.next()) {
            int product_id = rs.getInt(1);
            int categoryid = rs.getInt(2);
            String product_name = rs.getString(3);
            int product_originPrice = rs.getInt(4);
            int product_percentSale = rs.getInt(5);

            ImageDAO image = new ImageDAO();
            List<ImageProduct> listImage = image.getListImage(product_id);

            product = new Product();
            product.setProduct_id(product_id);
            product.setCategory_id(categoryid);
            product.setProduct_name(product_name);
            product.setProduct_originPrice(product_originPrice);
            product.setProduct_percenSale(product_percentSale);
            product.setListImage(listImage);

        }
        return product;
    }

    public List<Product> getListProductByCategoriesId(int category_id) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "		p.category_id,\n"
                + "        p.product_name, \n"
                + "         \n"
                + "        p.product_originPrice, \n"
                + "		p.product_percentSale,\n"
                + "        i.imageProduct_url,\n"
                + "\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		Where category_id = ?\n"
                + ")\n"
                + "SELECT \n"
                + "    product_id, \n"
                + "	category_id, \n"
                + "    product_name, \n"
                + "    \n"
                + "    product_originPrice, \n"
                + "	product_percentSale,\n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "WHERE \n"
                + "    rn = 1;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int categoryid = rs.getInt(2);
                String product_name = rs.getString(3);
                int product_originPrice = rs.getInt(4);
                int product_percentSale = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(categoryid);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getAllProductForPaging(int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (   \n"
                + "                   SELECT    \n"
                + "                            p.product_id,    \n"
                + "                            p.product_name,    \n"
                + "                            p.category_id,    \n"
                + "                            p.product_originPrice,   \n"
                + "                            p.product_percentSale,   \n"
                + "                            i.imageProduct_url,   \n"
                + "                            ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn   \n"
                + "                            FROM    \n"
                + "                              [Products] p   \n"
                + "                            JOIN    \n"
                + "                               [ImageProducts] i ON p.product_id = i.product_id   \n"
                + "                                where p.isActive =1  )   \n"
                + "                   SELECT   \n"
                + "                            product_id,    \n"
                + "                            product_name,    \n"
                + "                            category_id,    \n"
                + "                            product_originPrice,   \n"
                + "                            product_percentSale,   \n"
                + "                            imageProduct_url   \n"
                + "                  FROM    \n"
                + "                             OrderedImages   \n"
                + "                  WHERE    \n"
                + "                        rn = 1 \n"
                + "                 	Order by product_id \n"
                + "                 	Offset ? rows \n"
                + "                 	FETCH NEXT 12 Rows only";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int categoryid = rs.getInt(3);
                String product_name = rs.getString(2);
                int product_originPrice = rs.getInt(4);
                int product_percentSale = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(categoryid);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getListTopSaleProduct(int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (    \n"
                + "                    SELECT     \n"
                + "                          p.product_id,     \n"
                + "                          p.product_name,     \n"
                + "                          p.category_id,     \n"
                + "                          p.product_originPrice,    \n"
                + "                           		p.product_percentSale,    \n"
                + "                          i.imageProduct_url,\n"
                + "						  \n"
                + "                          ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn    \n"
                + "                      FROM     \n"
                + "                          [Products] p    \n"
                + "                      JOIN     \n"
                + "                          [ImageProducts] i ON p.product_id = i.product_id    \n"
                + "						  where p.isActive = 1\n"
                + "                           	      )    \n"
                + "                           SELECT    \n"
                + "                      product_id,     \n"
                + "                      product_name,     \n"
                + "                      category_id,     \n"
                + "                      product_originPrice,    \n"
                + "                      product_percentSale,    \n"
                + "                      imageProduct_url    \n"
                + "                           FROM     \n"
                + "                      OrderedImages    \n"
                + "                           WHERE     \n"
                + "                      rn = 1 and product_percentSale >=20  \n"
                + "                  		 Order by product_percentSale DESC  \n"
                + "                  		 Offset ? rows  \n"
                + "                  		 FETCH NEXT 12 Rows only";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int categoryid = rs.getInt(3);
                String product_name = rs.getString(2);
                int product_originPrice = rs.getInt(4);
                int product_percentSale = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(categoryid);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public Product getProductByID(int product_id) {
        TypeDAO db = new TypeDAO();
        ColorDAO dbc = new ColorDAO();
        ProductTypeColorDAO ptc = new ProductTypeColorDAO();
        String sql = "with productnew as(\n"
                + "select p.product_id,sum(ptc.quantity) as product_quantity from Products p join ProductTypeColor ptc \n"
                + "on p.product_id = ptc.product_id\n"
                + "group by p.product_id\n"
                + ")\n"
                + "select p.product_name, product_originPrice, product_percentSale, productnew.product_quantity, aa.imageProduct_url from [Products] p join productnew\n"
                + "on p.product_id = productnew.product_id\n"
                + "join (\n"
                + "	select top 1 i.product_id ,i.imageProduct_url from ImageProducts i\n"
                + "	where i.product_id = ?\n"
                + ") aa\n"
                + "on p.product_id = aa.product_id\n"
                + "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String product_name = rs.getString(1);
                long product_originPrice = rs.getLong(2);
                int product_percentSale = rs.getInt(3);
                int product_quantity = rs.getInt(4);
                String imageProduct = rs.getString(5);
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                List<Type> listType = db.getListTypeByProductID(product_id + "");
                List<Color> listColor = dbc.getListColorByProductID(product_id);
                List<ProductTypeColor> list = ptc.getProductTypeColorByProduct_ID(product_id);
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setListType(listType);
                product.setListColor(listColor);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setProduct_quantity(product_quantity);
                product.setListProductTypeColor(list);
                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getListProductByFilter(int filter_id) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     select Products.product_id
                     from Products
                     where filter_id = ?
                     ORDER BY [Products].product_id;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, filter_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                Product product = this.getProduct(product_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> listProduct = new ArrayList();

        String sql = """
                     select * from [Products] 
                     where [Products].product_name like ?""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int category_id = rs.getInt(2);
                int filter_id = rs.getInt(3);
                int brand_id = rs.getInt(4);
                int store_id = rs.getInt(5);
                String product_name = rs.getString(6);
                long product_originPrice = rs.getLong(7);
                int product_percenSale = rs.getInt(8);

                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);

                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(category_id);
                product.setFilter_id(filter_id);

                product.setBrand_id(brand_id);
                product.setStore_id(store_id);
                product.setProduct_name(product_name);

                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);

                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getListProductForViewStore(int user_id, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (   \n"
                + "                   SELECT    \n"
                + "                         p.product_id,    \n"
                + "                         p.product_name,    \n"
                + "                         p.category_id,    \n"
                + "                         p.product_originPrice,   \n"
                + "                         p.product_percentSale,   \n"
                + "                         i.imageProduct_url,   \n"
                + "                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn   \n"
                + "                     FROM    \n"
                + "                         [Products] p   \n"
                + "                     JOIN    \n"
                + "                         [ImageProducts] i ON p.product_id = i.product_id   \n"
                + "                 			where p.store_id = ? and isActive =1\n"
                + "                          	      )   \n"
                + "                          SELECT   \n"
                + "                     product_id,    \n"
                + "                     product_name,    \n"
                + "                     category_id,    \n"
                + "                     product_originPrice,   \n"
                + "                     product_percentSale,   \n"
                + "                     imageProduct_url   \n"
                + "                          FROM    \n"
                + "                     OrderedImages   \n"
                + "                          WHERE    \n"
                + "                     rn = 1  \n"
                + "                 		 Order by product_id \n"
                + "                 		 Offset ? rows \n"
                + "                 		 FETCH NEXT 9 Rows only";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, (index - 1) * 9);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(6);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> searchByNameforGuest(String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                        SELECT
                     \t\t\t\t        
                                             p.product_id, 
                                             p.product_name,  
                                             p.product_originPrice,
                                             p.product_percentSale,
                                             p.quantity,
                                             i.imageProduct_url,
                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                    FROM 
                                            [Products] p
                                         JOIN
                                         [ImageProducts] i ON p.product_id = i.product_id
                     \t\t\t\t
                     \t\t\t\t\t where p.product_name like ? 
                                     )
                                     
                                      SELECT top 100
                     \t\t\t\t       
                                            product_id,
                                            product_name,
                                           product_originPrice,
                                             product_percentSale,
                                             quantity,
                                            imageProduct_url
                                        FROM
                                            RankedProducts
                                       WHERE 
                                            rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                int product_quantity = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setProduct_quantity(product_quantity);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return listProduct;
    }

    public List<Product> getListTopSalesProductForViewStore(int user_id) {
        List<Product> listProduct = new ArrayList();

        String sql = "WITH OrderedImages AS ( \n"
                + "                                    SELECT  \n"
                + "                                        p.product_id, \n"
                + "                 					   p.store_id, \n"
                + "                                         p.product_name,  \n"
                + "                                        p.category_id,  \n"
                + "                                        p.product_originPrice, \n"
                + "                                         p.product_percentSale, \n"
                + "                                         i.imageProduct_url, \n"
                + "                                            ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn \n"
                + "                                  FROM  \n"
                + "                                        [Products] p \n"
                + "                                   JOIN  \n"
                + "                                         [ImageProducts] i ON p.product_id = i.product_id \n"
                + "                 						where p.store_id = ? and isActive =1\n"
                + "                                 ) \n"
                + "                                 SELECT \n"
                + "                                   product_id,  \n"
                + "                                     product_name,  \n"
                + "                 					category_id, \n"
                + "                                    product_originPrice, \n"
                + "                                     product_percentSale, \n"
                + "                                     imageProduct_url \n"
                + "                                 FROM  \n"
                + "                                   OrderedImages \n"
                + "                                 WHERE \n"
                + "                                 rn = 1 \n"
                + "                 				order by product_percentSale DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(6);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public int totalProductOfStore(int user_id) {
        String sql = "SELECT COUNT(*) AS TotalProducts\n"
                + "                FROM [dbo].[Products]\n"
                + "                WHERE [store_id] = ? and isActive =1;";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return total;
    }

    public List<Product> sortByPriceMinforGuest(String txt, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                         SELECT
                             p.product_id, 
                             p.product_name,  
                             p.product_originPrice,
                             p.product_percentSale,
                             i.imageProduct_url,
                             ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                         FROM 
                             [Products] p
                         JOIN
                             [ImageProducts] i ON p.product_id = i.product_id
                         JOIN
                             ProductTypeColor pt ON p.product_id = pt.product_id
                         WHERE 
                             p.product_name LIKE ?
                     ),
                     FilteredRankedProducts AS (
                         SELECT 
                             ROW_NUMBER() OVER (ORDER BY product_originPrice ASC) AS r,
                             product_id, 
                             product_name,  
                             product_originPrice,
                             product_percentSale,
                             imageProduct_url
                         FROM  
                             RankedProducts 
                         WHERE 
                             rn = 1
                     )
                     SELECT 
                         product_id, 
                         product_name,  
                         product_originPrice,
                         product_percentSale,
                         imageProduct_url 
                     FROM  
                         FilteredRankedProducts
                     WHERE  
                         r BETWEEN ?*6-5 and ?*6
                     ORDER BY 
                         product_originPrice ASC;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByPriceMaxforGuest(String txt, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                                                   SELECT
                                                                       p.product_id, 
                                                                       p.product_name,  
                                                                       p.product_originPrice,
                                                                       p.product_percentSale,
                                                                       i.imageProduct_url,
                                                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                                                   FROM 
                                                                       [Products] p
                                                                   JOIN
                                                                       [ImageProducts] i ON p.product_id = i.product_id
                                                                   JOIN
                                                                       ProductTypeColor pt ON p.product_id = pt.product_id
                                                                   WHERE 
                                                                       p.product_name LIKE ?
                                                               ),
                                                               FilteredRankedProducts AS (
                                                                   SELECT 
                                                                       ROW_NUMBER() OVER (ORDER BY product_originPrice DESC) AS r,
                                                                       product_id, 
                                                                       product_name,  
                                                                       product_originPrice,
                                                                       product_percentSale,
                                                                       imageProduct_url
                                                                   FROM  
                                                                       RankedProducts 
                                                                   WHERE 
                                                                       rn = 1
                                                               )
                                                               SELECT 
                                                                   product_id, 
                                                                   product_name,  
                                                                   product_originPrice,
                                                                   product_percentSale,
                                                                   imageProduct_url 
                                                               FROM  
                                                                   FilteredRankedProducts
                                                               WHERE  
                                                                   r BETWEEN ?*6-5 and ?*6
                                                               ORDER BY 
                                                                   product_originPrice DESC;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByTopSalesforGuest(String txt, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                              SELECT
                                                  p.product_id, 
                                                  p.product_name,  
                                                  p.product_originPrice,
                                                  p.product_percentSale,
                                                  i.imageProduct_url,
                                                  ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                              FROM 
                                                  [Products] p
                                              JOIN
                                                  [ImageProducts] i ON p.product_id = i.product_id
                                              JOIN
                                                  ProductTypeColor pt ON p.product_id = pt.product_id
                                              WHERE 
                                                  p.product_name LIKE ?
                                          ),
                                          FilteredRankedProducts AS (
                                              SELECT 
                                                  ROW_NUMBER() OVER (ORDER BY product_percentSale DESC) AS r,
                                                  product_id, 
                                                  product_name,  
                                                  product_originPrice,
                                                  product_percentSale,
                                                  imageProduct_url
                                              FROM  
                                                  RankedProducts 
                                              WHERE 
                                                  rn = 1
                                          )
                                          SELECT 
                                              product_id, 
                                              product_name,  
                                              product_originPrice,
                                              product_percentSale,
                                              imageProduct_url 
                                          FROM  
                                              FilteredRankedProducts
                                          WHERE  
                                              r BETWEEN ?*6-5 and ?*6
                                          ORDER BY 
                                              product_percentSale DESC;
                     """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public int count(String txt) {
        String sql = """
                   WITH RankedProducts AS (
                                      SELECT
                   \t\t\t\t        
                                           p.product_id, 
                                           p.product_name,  
                                           p.product_originPrice,
                                           p.product_percentSale,
                                           i.imageProduct_url,
                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                  FROM 
                                          [Products] p
                                       JOIN
                                       [ImageProducts] i ON p.product_id = i.product_id
                   \t\t\t\t\twhere p.product_name like ?
                                   )
                                   
                                    SELECT 
                   \t\t\t\t       
                                          count(*)
                                      FROM
                                          RankedProducts
                                     WHERE 
                                          rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Product> PagingSearchForGuest(String txt, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                                             SELECT
                                                 
                                                                  p.product_id, 
                                                                  p.product_name,  
                                                                  p.product_originPrice,
                                                                  p.product_percentSale,
                                                                  i.imageProduct_url,
                                                              ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                          
                                                         FROM 
                                                                 [Products] p
                                                              JOIN
                                                              [ImageProducts] i ON p.product_id = i.product_id
                                          Join
                                          ProductTypeColor pt on p.product_id = pt.product_id
                                         
                                          where p.product_name like ?
                                                          )
                                                          select product_id, 
                                                                                  product_name,  
                                                                                  product_originPrice,
                                                                                  product_percentSale,
                                                                                  imageProduct_url from  
                                                           (SELECT 
                                                ROW_NUMBER() OVER (order by product_id) as r, 
                                                                
                                                            * FROM
                                                                 RankedProducts 
                                             where rn = 1 ) as x
                                                            WHERE 
                                                                 rn = 1 and 
                                             r between ?*6-5 and ?*6 ;""";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> searchByNameforSeller(int id_user, String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH OrderedImages AS (
                                              SELECT 
                                                  p.product_id, 
                                                  p.product_name,  
                                                  i.imageProduct_url,
                                                  p.product_originPrice,
                                                  pt.quantity,
                                                  p.product_percentSale,
                                                  c.name,
                                                  ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn
                                              FROM 
                                                  [Products] p
                                                  join 
                                          Stores s on p.store_id = s.store_id
                                                  join 
                                         Users u on s.store_id = u.user_id
                                         JOIN 
                                                  [ImageProducts] i ON p.product_id = i.product_id
                                          JOIN
                                         Categories c on p.category_id = c.category_id
                                         JOIN 
                                         ProductTypeColor pt on p.product_id = pt.product_id
                                          WHERE u.user_id = ? and p.product_name like ?
                                              
                                          )
                                          SELECT Top 100
                                              product_id, 
                                              product_name,
                                          name,
                                          imageProduct_url,
                                          product_originPrice,
                                          product_percentSale,
                                          quantity
                                          FROM
                                              OrderedImages
                                          WHERE 
                                              rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ps.setString(2, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String imageProduct = rs.getString(4);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                int product_quantity = rs.getInt(7);
                ImageDAO idb = new ImageDAO();
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setCategory_id(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setProduct_quantity(product_quantity);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByPriceMinforSeller(int id_user) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "                                                                   SELECT \n"
                + "                                                                       p.product_id, \n"
                + "                                                                       p.product_name,  \n"
                + "                                                                       i.imageProduct_url,\n"
                + "                                                                       p.product_originPrice,\n"
                + "                                                                       p.product_percentSale,\n"
                + "                                                                       c.name,\n"
                + "                                                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "                                                                   FROM \n"
                + "                                                                       [Products] p\n"
                + "                                                                       join \n"
                + "                                                               Stores s on p.store_id = s.store_id\n"
                + "                                                                       join \n"
                + "                                                              Users u on s.store_id = u.user_id\n"
                + "                                                              JOIN \n"
                + "                                                                       [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                                                               JOIN\n"
                + "                                                              Categories c on p.category_id = c.category_id\n"
                + "                                                               WHERE u.user_id = ?\n"
                + "                                                                   \n"
                + "                                                               )\n"
                + "                                                               SELECT Top 100\n"
                + "                                                                   product_id, \n"
                + "                                                                   product_name,\n"
                + "                                                               name,\n"
                + "                                                               imageProduct_url,\n"
                + "                                                               product_originPrice,\n"
                + "                                                               product_percentSale\n"
                + "                                                               FROM\n"
                + "                                                                   OrderedImages\n"
                + "                                                               WHERE \n"
                + "                                                                   rn = 1\n"
                + "                                          order by product_originPrice asc;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String imageProduct = rs.getString(4);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                ImageDAO idb = new ImageDAO();
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByPriceMaxforSeller(int id_user) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "                                                                   SELECT \n"
                + "                                                                       p.product_id, \n"
                + "                                                                       p.product_name,  \n"
                + "                                                                       i.imageProduct_url,\n"
                + "                                                                       p.product_originPrice,\n"
                + "                                                                       p.product_percentSale,\n"
                + "                                                                       c.name,\n"
                + "                                                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "                                                                   FROM \n"
                + "                                                                       [Products] p\n"
                + "                                                                       join \n"
                + "                                                               Stores s on p.store_id = s.store_id\n"
                + "                                                                       join \n"
                + "                                                              Users u on s.store_id = u.user_id\n"
                + "                                                              JOIN \n"
                + "                                                                       [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                                                               JOIN\n"
                + "                                                              Categories c on p.category_id = c.category_id\n"
                + "                                                               WHERE u.user_id = ?\n"
                + "                                                                   \n"
                + "                                                               )\n"
                + "                                                               SELECT Top 100\n"
                + "                                                                   product_id, \n"
                + "                                                                   product_name,\n"
                + "                                                               name,\n"
                + "                                                               imageProduct_url,\n"
                + "                                                               product_originPrice,\n"
                + "                                                               product_percentSale\n"
                + "                                                               FROM\n"
                + "                                                                   OrderedImages\n"
                + "                                                               WHERE \n"
                + "                                                                   rn = 1\n"
                + "                                          order by product_originPrice desc;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String imageProduct = rs.getString(4);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                ImageDAO idb = new ImageDAO();
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }
//    public int quantityOfProductById(int id_product) {
//        int quantity;
//        String sql = "select sum(pt.quantity) \n"
//                + "from [ProductTypeColor] pt\n"
//                + "where pt.product_id = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id_product);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//              quantity = rs.getInt(1);
//              return quantity;
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }

    public int getStoreIDByProductID(int product_id) {
        String sql = "select store_id from Products where product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void AddProduct(String txtName, String txtPrice, String txtDescription,
            int categoryID, int filterID, int brandID, int storeID, int percentSale,
            String importDate, String[] txtImages, ProductTypeColor[] typeColors) {
        String productSql = "INSERT INTO Products (product_name, product_originPrice, product_importDate, product_describes, category_id, filter_id, brand_id, store_id, product_percentSale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Chèn dữ liệu vào bảng Products
            PreparedStatement productStm = con.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS);
            productStm.setString(1, txtName);
            productStm.setString(2, txtPrice);
            productStm.setString(3, importDate);
            productStm.setString(4, txtDescription);
            productStm.setInt(5, categoryID);
            productStm.setInt(6, filterID);
            productStm.setInt(7, brandID);
            productStm.setInt(8, storeID);
            productStm.setInt(9, percentSale);

            int rowsAffected = productStm.executeUpdate();

            int productId = -1;
            if (rowsAffected > 0) {
                // Lấy productId từ generated keys
                try (ResultSet generatedKeys = productStm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productId = generatedKeys.getInt(1);
                        System.out.println("Generated Product ID: " + productId);
                    }
                }
            }

            if (productId != -1) {
                // Chèn dữ liệu vào bảng ImageProducts nếu có hình ảnh được cung cấp
                if (txtImages != null && txtImages.length > 0) {
                    String imageSql = "INSERT INTO ImageProducts (product_id, imageProduct_url) VALUES (?, ?)";
                    try (PreparedStatement imageStm = con.prepareStatement(imageSql)) {
                        for (String imageUrl : txtImages) {
                            imageStm.setInt(1, productId);
                            imageStm.setString(2, imageUrl);
                            int imageRowsAffected = imageStm.executeUpdate();
                            if (imageRowsAffected > 0) {
                                System.out.println("Image added successfully: " + imageUrl);
                            } else {
                                System.out.println("Failed to add image: " + imageUrl);
                            }
                        }
                    }
                    System.out.println("Images added successfully.");
                }

                // Chèn dữ liệu vào bảng ProductTypeColor cho mỗi type/color combination
                if (typeColors != null && typeColors.length > 0) {
                    String ptcSql = "INSERT INTO ProductTypeColor (product_id, type_id, color_id, quantity) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement ptcStm = con.prepareStatement(ptcSql)) {
                        for (ProductTypeColor typeColor : typeColors) {
                            ptcStm.setInt(1, productId);
                            ptcStm.setInt(2, typeColor.getType_id());
                            ptcStm.setInt(3, typeColor.getColor_id());
                            ptcStm.setInt(4, typeColor.getQuantity());
                            int ptcRowsAffected = ptcStm.executeUpdate();
                            if (ptcRowsAffected > 0) {
                                System.out.println("ProductTypeColor added successfully: TypeID=" + typeColor.getType_id() + ", ColorID=" + typeColor.getColor_id() + ", Quantity=" + typeColor.getQuantity());
                            } else {
                                System.out.println("Failed to add ProductTypeColor: TypeID=" + typeColor.getType_id() + ", ColorID=" + typeColor.getColor_id());
                            }
                        }
                    }
                }

                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (SQLException ex) {
        }
    }

    public void UpdateProduct(int productId, String txtName, long txtPrice, String txtDescription,
            int percentSale, String[] txtImages, ProductTypeColor[] typeColors) {
        String productSql = "UPDATE Products SET product_name = ?, product_originPrice = ?, product_describes = ?, product_percentSale = ? WHERE product_id = ?";
        String deleteImageSql = "DELETE FROM ImageProducts WHERE product_id = ?";
        String insertImageSql = "INSERT INTO ImageProducts (product_id, imageProduct_url) VALUES (?, ?)";
        String updatePtcSql = """
                           UPDATE ProductTypeColor
                              SET quantity = ?
                            WHERE product_id = ? AND type_id = ? AND color_id = ?""";
        String insertPtcSql = """
                          INSERT INTO ProductTypeColor (product_id, type_id, color_id, quantity)
                          VALUES (?, ?, ?, ?)""";

        try {
            con.setAutoCommit(false); // Start transaction

            // Cập nhật dữ liệu trong bảng Products
            try (PreparedStatement productStm = con.prepareStatement(productSql)) {
                productStm.setString(1, txtName);
                productStm.setLong(2, txtPrice);
                productStm.setString(3, txtDescription);
                productStm.setInt(4, percentSale);
                productStm.setInt(5, productId);
                productStm.executeUpdate();
                System.out.println("Product updated successfully.");
            }

            // Xóa ảnh cũ nếu có ảnh mới
            if (txtImages != null && txtImages.length > 0) {
                try (PreparedStatement deleteImageStm = con.prepareStatement(deleteImageSql)) {
                    deleteImageStm.setInt(1, productId);
                    deleteImageStm.executeUpdate();
                    System.out.println("Old images deleted successfully.");
                }

                // Thêm ảnh mới
                try (PreparedStatement insertImageStm = con.prepareStatement(insertImageSql)) {
                    for (String imageUrl : txtImages) {
                        insertImageStm.setInt(1, productId);
                        insertImageStm.setString(2, imageUrl);
                        insertImageStm.executeUpdate();
                        System.out.println("Image added successfully: " + imageUrl);
                    }
                }
            }

            // Cập nhật thông tin ProductTypeColor
            try (PreparedStatement updatePtcStm = con.prepareStatement(updatePtcSql); PreparedStatement insertPtcStm = con.prepareStatement(insertPtcSql)) {
                for (ProductTypeColor typeColor : typeColors) {
                    updatePtcStm.setInt(1, typeColor.getQuantity());
                    updatePtcStm.setInt(2, productId);
                    updatePtcStm.setInt(3, typeColor.getType_id());
                    updatePtcStm.setInt(4, typeColor.getColor_id());

                    int rowsAffected = updatePtcStm.executeUpdate();
                    if (rowsAffected == 0) {
                        insertPtcStm.setInt(1, productId);
                        insertPtcStm.setInt(2, typeColor.getType_id());
                        insertPtcStm.setInt(3, typeColor.getColor_id());
                        insertPtcStm.setInt(4, typeColor.getQuantity());
                        insertPtcStm.executeUpdate();
                        System.out.println("ProductTypeColor inserted successfully: " + typeColor);
                    } else {
                        System.out.println("ProductTypeColor updated successfully: " + typeColor);
                    }
                }
            }

            con.commit(); // Commit transaction
            System.out.println("ProductTypeColors added/updated successfully.");
        } catch (SQLException ex) {
            try {
                con.rollback(); // Rollback transaction in case of error
            } catch (SQLException e) {
                System.out.println("Error rolling back transaction: " + e.getMessage());
            }
            System.out.println("Error updating product: " + ex.getMessage());
        }
    }

    public void acceptProduct(int product_id) {
        try {
            String sql = "UPDATE Products\n"
                    + "Set isActive = 1\n"
                    + "where product_id= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            int x = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectProduct(int product_id) {
        try {
            String sql = "UPDATE Products\n"
                    + "Set isActive = 0\n"
                    + "where product_id= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            int x = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> searchProductForAdmin(String txtSearch) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select distinct(p.product_id), p.product_name, p.product_originPrice, c.category_id, p.product_importDate, b.brand_id, s.store_id, p.isActive\n"
                    + "from Products p join Categories c on p.category_id = c.category_id\n"
                    + "join Stores s on p.store_id = s.store_id\n"
                    + "join Brands b on p.brand_id = b.brand_id\n"
                    + "where p.product_name like ? or c.name like ? or b.brandName like ? or s.store_name like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setNString(1, "%" + txtSearch + "%");
            ps.setNString(2, "%" + txtSearch + "%");
            ps.setNString(3, "%" + txtSearch + "%");
            ps.setString(4, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(4);
                int store_id = rs.getInt(7);
                java.sql.Date inputDay = rs.getDate(5);
                boolean isActive = rs.getBoolean(8);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                product.setIsActive(isActive);
                product.setStore_id(store_id);
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int totalProductofSeller(int user_id) {
        String sql = """
                     WITH OrderedImages AS (
                                                                                        SELECT 
                                                                                            p.product_id, 
                                                                                            p.product_name,  
                                                                                            i.imageProduct_url,
                                                                                    p.product_originPrice,
                                                                                    p.product_percentSale,
                                                                                        c.name,
                                                                                            ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn
                                                                                        FROM 
                                                                                            [Products] p
                                                                                            join 
                                                                                    Stores s on p.store_id = s.store_id
                                                                                            join 
                                                                                    Users u on s.store_id = u.user_id
                                                                                    JOIN 
                                                                                            [ImageProducts] i ON p.product_id = i.product_id
                                                                                    JOIN
                                                                                    Categories c on p.category_id = c.category_id
                                                                                    WHERE u.user_id = ?
                                                                                        
                                                                                    )
                                                                                    SELECT count(*)
                                                                                        
                                                                                    
                                                                                    FROM
                                                                                        OrderedImages
                                                                                    WHERE 
                                                                                        rn = 1;""";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return total;
    }

    public List<Product> SearchByFilterForGuest(String txt, String[] filter_ids) {
        List<Product> list = new ArrayList<>();

        if (filter_ids == null || filter_ids.length == 0) {
            return list; // Return empty list if there are no filters
        }

        // Construct a comma-separated list of placeholders for the filter IDs
        StringBuilder sb = new StringBuilder();
        Arrays.stream(filter_ids).forEach(id -> sb.append("?").append(","));
        sb.replace(sb.length() - 1, sb.length(), ""); // Remove trailing comma

        // Construct the SQL query
        String sql = """
                 WITH SearchProducts AS (
                     SELECT 
                         p.product_id, 
                         p.product_name,  
                         p.product_originPrice,
                         p.product_percentSale,
                         i.imageProduct_url,
                         p.filter_id,
                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                     FROM 
                         [Products] p
                     JOIN
                         [ImageProducts] i ON p.product_id = i.product_id
                     WHERE 
                         p.product_name LIKE ?
                 )
                 SELECT  
                     s.product_id, 
                     s.product_name,  
                     s.product_originPrice,
                     s.product_percentSale,
                     s.imageProduct_url,
                     f.filter_id
                 FROM
                     SearchProducts s
                 JOIN 
                     [Filters] f ON s.filter_id = f.filter_id
                 WHERE 
                     s.rn = 1 AND f.filter_id IN (""" + sb.toString() + ");";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + txt + "%");
            // Set the filter ID values
            for (int i = 0; i < filter_ids.length; i++) {
                ps.setString(2 + i, filter_ids[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int product_id = rs.getInt(1);
                    String product_name = rs.getString(2);
                    long product_originPrice = rs.getLong(3);
                    int product_percentSale = rs.getInt(4);
                    String imageProduct = rs.getString(5);

                    Product product = new Product();
                    List<ImageProduct> listIP = new ArrayList<>();
                    ImageProduct imageP = new ImageProduct();
                    imageP.setImageProduct_url(imageProduct);
                    listIP.add(imageP);

                    product.setProduct_id(product_id);
                    product.setProduct_name(product_name);
                    product.setListImage(listIP);
                    product.setProduct_originPrice(product_originPrice);
                    product.setProduct_percenSale(product_percentSale);

                    list.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO db = new ProductDAO();
        String[] l = {"21", "25"};
        List<Product> list = new ArrayList<>();
//        list = db.sortByPriceMaxforGuest("ba", 1);
        list = db.SearchByFilterForGuest("ba", l);
        System.out.println(list.size());
    }
}
