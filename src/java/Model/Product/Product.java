/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Product;
import Model.Image.ImageProduct;
import Model.User.Store;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import util.Feature;
/**
 *
 * @author hbtth
 */
public class Product {
    private int product_id;
    private int category_id;
    private int filter_id;
    private int brand_id;
    private int store_id;
    private String product_name;
    private long product_originPrice;
    private int product_percenSale;
    private int product_quantity;
    private Date product_importDate;
    private String product_Describes;
    private boolean isActive;
    private List<ImageProduct> listImage;
    private List<Type> listType;
    private List<Feedback> listFeedback;
    private Store store;
    private List<Color> listColor;
    private List<ProductTypeColor> listProductTypeColor;
    public Product() {
        listImage = new ArrayList();
        listType = new ArrayList();
        listFeedback = new ArrayList();
        listColor = new ArrayList();
        listProductTypeColor = new ArrayList();
    }

    public Product(int product_id, int category_id, int filter_id, int brand_id, int store_id, String product_name, long product_originPrice, int product_percenSale, Date product_importDate, String product_Describes, int product_quantity, List<ImageProduct> listImage, List<Type> listType) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.filter_id = filter_id;
        this.brand_id = brand_id;
        this.store_id = store_id;
        this.product_name = product_name;
        this.product_originPrice = product_originPrice;
        this.product_percenSale = product_percenSale;
        this.product_importDate = product_importDate;
        this.product_Describes = product_Describes;
        this.listImage = listImage;
        this.listType = listType;
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public long getProduct_originPrice() {
        return product_originPrice;
    }

    public void setProduct_originPrice(long product_originPrice) {
        this.product_originPrice = product_originPrice;
    }

    public int getProduct_percenSale() {
        return product_percenSale;
    }

    public void setProduct_percenSale(int product_percenSale) {
        this.product_percenSale = product_percenSale;
    }

    public Date getProduct_importDate() {
        return product_importDate;
    }

    public void setProduct_importDate(Date product_importDate) {
        this.product_importDate = product_importDate;
    }

    public String getProduct_Describes() {
        return product_Describes;
    }

    public void setProduct_Describes(String product_Describes) {
        this.product_Describes = product_Describes;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    

    public List<ImageProduct> getListImage() {
        return listImage;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setListImage(List<ImageProduct> listImage) {
        this.listImage = listImage;
    }

    public List<Type> getListType() {
        return listType;
    }

    public void setListType(List<Type> listType) {
        this.listType = listType;
    }

    public List<Feedback> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<Feedback> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Color> getListColor() {
        return listColor;
    }

    public void setListColor(List<Color> listColor) {
        this.listColor = listColor;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public List<ProductTypeColor> getListProductTypeColor() {
        return listProductTypeColor;
    }

    public void setListProductTypeColor(List<ProductTypeColor> listProductTypeColor) {
        this.listProductTypeColor = listProductTypeColor;
    }
   
    public int getProduct_quantity(int product_id, int color_id, int type_id) {
        for(ProductTypeColor i : listProductTypeColor){
            if(i.getProduct_id() == product_id &&
               i.getColor_id() == color_id &&
               i.getType_id() == type_id
               ){
                return i.getQuantity();
            }
        }
        return 0;
    }
    
    
}
