/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.ProductDAO;
import DAO.ShoppingCartDAO;
import DAO.StoreDAO;
import Model.Product.Product;
import Model.ShoppingCart.ShoppingCart;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import org.json.JSONObject;
import util.Feature;

/**
 *
 * @author hbtth
 */
public class cartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        StoreDAO sd= new StoreDAO();
        User user = (User) session.getAttribute("s_u_tikilazapee");
        ShoppingCartDAO db = new ShoppingCartDAO();
        ShoppingCart cart = db.getCart(user.getUser_id());
        request.setAttribute("db", sd);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("view/CustomerView/ShoppingCartPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShoppingCartDAO db = new ShoppingCartDAO();
        ProductDAO pd = new ProductDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_tikilazapee");
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        JSONObject json = new JSONObject(sb.toString());
        int product_id = json.getInt("product_id");
        Product product = pd.getProductByID(product_id);
        int type_id = json.getInt("type_id");
        int color_id = json.getInt("color_id");
        int product_quantity = json.getInt("quantity");
        System.out.println(sb.toString());
        long unitPrice = Feature.calculateSalePriceI(product.getProduct_originPrice(), product.getProduct_percenSale());
        System.out.println(unitPrice);
        db.addItemToCart(user.getUser_id(), product_id, type_id, unitPrice, product_quantity, unitPrice * product_quantity, color_id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String data = sb.toString();
        
        JSONObject json = new JSONObject(data);
        int id = json.getInt("id");
        int val = json.getInt("value");
        ShoppingCartDAO db = new ShoppingCartDAO();
        System.out.println(data);
        db.changeQuantityItem(id, val);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while((line = reader.readLine())!= null){
            sb.append(line);
        }
        String data = sb.toString();
        JSONObject json = new JSONObject(data);
        int cartItem_id = json.getInt("id");
        ShoppingCartDAO db = new ShoppingCartDAO();
        db.deleteItemFromCart(cartItem_id);
    }
   
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
