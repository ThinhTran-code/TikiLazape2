/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import DAO.CategoryDAO;
import DAO.FilterDAO;
import DAO.ProductDAO;
import Model.Product.Category;
import Model.Product.FilterProduct;
import Model.Product.Product;
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
public class SearchByFilter extends HttpServlet {

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
        String txt = request.getParameter("txt");
        String[] filterIds = request.getParameter("filterIds").split(",");
        ProductDAO pd = new ProductDAO();
        FilterDAO fd = new FilterDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Product> lists = new ArrayList();
        List<FilterProduct> listf = new ArrayList();
        List<Category> listc = new ArrayList();
        listc = cd.getListCategoryForSearch(txt);
        lists = pd.SearchByFilterForGuest(txt, filterIds);
        request.setAttribute("fd", fd);
        request.setAttribute("pd", pd);
        request.setAttribute("listc", listc);
        request.setAttribute("lists", lists);
        request.setAttribute("listf", listf);
        request.setAttribute("searchtxt", txt);
        
        request.getRequestDispatcher("/view/CustomerView/SearchPage.jsp").forward(request, response);   
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
        processRequest(request, response);
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
        processRequest(request, response);
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
