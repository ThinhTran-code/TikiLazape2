/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Seller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Product.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sktnb
 */
public class SearchProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String txt = request.getParameter("txt");
        CategoryDAO CAdb = new CategoryDAO();
        ProductDAO db = new ProductDAO();
        List<Product> listProduct = db.searchByNameforSeller(3, txt);
        request.setAttribute("txt", txt);
        request.setAttribute("CAdb", CAdb);
        request.setAttribute("listP", listProduct);
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/SellerView/ManageProduct.jsp").forward(request, response);
      
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
        int valueOption = Integer.parseInt(request.getParameter("optionSort"));
        CategoryDAO CAdb = new CategoryDAO();
        ProductDAO db = new ProductDAO();
        List<Product> listProduct = new ArrayList<>();
        switch (valueOption) {
            case 0 ->
               listProduct  = db.sortByPriceMinforSeller(3);
            case 1 ->
                listProduct = db.sortByPriceMaxforSeller(3);
            
        }
        
        request.setAttribute("CAdb", CAdb);
        request.setAttribute("listP", listProduct);
        request.setAttribute("valueOption", valueOption);
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/SellerView/ManageProduct.jsp").forward(request, response);
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
