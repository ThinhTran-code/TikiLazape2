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
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author sktnb
 */
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
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
        int index = Integer.parseInt(request.getParameter("index"));
        String txt = request.getParameter("txt");
        ProductDAO pd = new ProductDAO();
        FilterDAO fd = new FilterDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Product> lists = new ArrayList();
        List<FilterProduct> listf = new ArrayList();
        List<Category> listc = new ArrayList();
        listc = cd.getListCategoryForSearch(txt);
        int count = pd.count(txt);
        int size = 6;
        int endPage = count / size;
        if (count % size != 0) {
            endPage++;
        }

        lists = pd.PagingSearchForGuest(txt, index);
        request.setAttribute("fd", fd);
        request.setAttribute("index", index);
        request.setAttribute("pd", pd);
        request.setAttribute("listc", listc);
        request.setAttribute("lists", lists);
        request.setAttribute("endPage", endPage);
        request.setAttribute("txt", txt);
        request.getRequestDispatcher("/view/CustomerView/SearchPage.jsp").forward(request, response);
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
        int index = Integer.parseInt(request.getParameter("sortindex"));
        int valueOption = Integer.parseInt(request.getParameter("optionSort"));
        String txt = request.getParameter("sorttxt");
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        FilterDAO fd = new FilterDAO();
        List<Product> lists = new ArrayList();
        List<FilterProduct> listf = new ArrayList();
        List<Category> listc = new ArrayList();
        listc = cd.getListCategoryForSearch(txt);
        int count = pd.count(txt);
        int size = 6;
        int endPage = count / size;
        if (count % size != 0) {
            endPage++;
        }
        switch (valueOption) {
            case 0 ->
                lists = pd.sortByPriceMinforGuest(txt, index);
            case 1 ->
                lists = pd.sortByPriceMaxforGuest(txt, index);
            case 2 ->
                lists = pd.sortByTopSalesforGuest(txt, index);
        }
        request.setAttribute("listc", listc);
        request.setAttribute("fd", fd);
        request.setAttribute("index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("pd", pd);
        request.setAttribute("valueOption", valueOption);
        request.setAttribute("lists", lists);
        request.setAttribute("txt", txt);
        request.setAttribute("listf", listf);
        request.getRequestDispatcher("/view/CustomerView/SearchPage.jsp").forward(request, response);
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
