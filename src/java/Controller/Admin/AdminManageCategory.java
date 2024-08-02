/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Product.Category;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 *
 * @author Asus
 */
public class AdminManageCategory extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminManageCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        CategoryDAO categoryDb = new CategoryDAO();
        ProductDAO productDb = new ProductDAO();
        List<Category> list = categoryDb.getListCataForAdmin();
        request.setAttribute("listCategory", list);
        request.setAttribute("productDb", productDb);
        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/AdminView/adminManageCategory.jsp").forward(request, response);
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
        String optionSortParam = request.getParameter("optionSort");
        System.out.println("optionSort: " + optionSortParam); // In ra giá trị để kiểm tra
        int valueOption = Integer.parseInt(optionSortParam);
        List<Category> list;
        ProductDAO productDb = new ProductDAO();
        CategoryDAO db = new CategoryDAO();

        if (valueOption == 0) {
            list = db.getListCataForAdmin();
        } else {
            list = db.getListCataSortByNameAZ();
        }

        request.setAttribute("valueOption", valueOption);
        request.setAttribute("listCategory", list);
        request.setAttribute("productDb", productDb);

        ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/AdminView/adminManageCategory.jsp").forward(request, response);
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
