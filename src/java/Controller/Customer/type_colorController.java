/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Customer;

import DAO.ProductTypeColorDAO;
import DAO.ShoppingCartDAO;
import Model.ShoppingCart.ItemCart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import org.json.JSONObject;

/**
 *
 * @author hbtth
 */
public class type_colorController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ProductTypeColorDAO db = new ProductTypeColorDAO();
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int type_id = Integer.parseInt(request.getParameter("type_id"));
        int color_id = Integer.parseInt(request.getParameter("color_id"));
        int product_quantity = db.getQuantity(product_id, color_id, type_id);
        System.out.println(product_id + " " + type_id + " " + color_id + " " + product_quantity);
        out.print(product_quantity);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine())!= null){
            sb.append(line);
        }
        System.out.println(sb.toString());
        JSONObject json = new JSONObject(sb.toString());
        int product_id = json.getInt("product_id");
        int type_id = json.getInt("type_id");
        int color_id = json.getInt("color_id");
        int cartItem_id = json.getInt("cartItem_id");
        int shoppingCart_id = json.getInt("shoppingCart_id");
        ItemCart item = new ItemCart();
        item.setProduct_id(product_id);
        item.setType_id(type_id);
        item.setColor_id(color_id);
        item.setCartItem_id(cartItem_id);
        item.setShoppingCart_id(shoppingCart_id);
        ShoppingCartDAO db = new ShoppingCartDAO();
        boolean checkQuantity = db.updateTypeColorFromCart(item);
        JSONObject json1 = new JSONObject();
        if(!checkQuantity){
            json1.put("status", 0);
            json1.put("message", "There are only ");            
        }else{
            json1.put("status", "1");
            json1.put("message", ""); 
            
        }
        response.getWriter().print(json1);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while((line = reader.readLine())!= null){
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        int shoppingcart_id = json.getInt("shoppingcart_id");
        ShoppingCartDAO db = new ShoppingCartDAO();
        db.deleteAllItemFromShoppingCart(shoppingcart_id);
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
