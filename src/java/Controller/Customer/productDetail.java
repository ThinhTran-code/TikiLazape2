/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.FeedbackDAO;
import DAO.ProductDAO;
import DAO.ShoppingCartDAO;
import DAO.StoreDAO;
import Model.Product.Color;
import Model.Product.Type;

import Model.Product.Feedback;

import Model.Product.Product;
import Model.User.Store;
import Model.User.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import org.json.JSONObject;
import util.Feature;

public class productDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("product_id"));

        ProductDAO db = new ProductDAO();
        Product p = db.getProductByIdProduct(id);
        List<Product> pf = db.getProductByIdFilter(p.getFilter_id());
        request.setAttribute("product", p);
        request.setAttribute("productF", pf);
        StoreDAO ds = new StoreDAO();
        Store s = ds.getAllInfoStoresByProductId(id);

        request.setAttribute("infoStore", s);
        FeedbackDAO fbd = new FeedbackDAO();
        List<Feedback> fb = fbd.getListFeedbackByProductId(id);
        request.setAttribute("feedback", fb);

        String paramType = request.getParameter("type");
        String paramColor = request.getParameter("color");
        System.out.println(paramType);

        Type pType = null;
        Color pColor = null;
        if (paramType != null) {
            int type = Integer.parseInt(paramType);
            for (Type i : p.getListType()) {
                if (i.getType_id() == type) {
                    pType = i;
                    if (paramColor != null) {
                        int color = Integer.parseInt(paramColor);
                        for (Color j : pType.getListColor()) {
                            if (j.getColor_id() == color) {
                                pColor = j;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        request.setAttribute("pType", pType);
        request.setAttribute("pColor", pColor);
        request.setAttribute("db", db);

//        StoresDAO ds = new StoresDAO();
//        Store s = ds.getAllInfoStoresByProductId(id);
//        request.setAttribute("InfoStore", s);
//        FeedbackDAO fbd = new FeedbackDAO();
//        List<Feedback> fb = fbd.getListFeedbackByProductId(id);
        request.setAttribute("feedback", fb);
        request.getRequestDispatcher("view/CustomerView/productDetail.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        long unitPrice = Feature.calculateSalePriceI(product.getProduct_originPrice(), product.getProduct_percenSale());
        db.addItemToCart(user.getUser_id(), product_id, type_id, unitPrice, product_quantity, unitPrice * product_quantity, color_id);
        int cartItemID = db.getCartItemID();
        response.getWriter().print(cartItemID);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
