/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.FilterDAO;
import DAO.UserDAO;
import DAO.ProductDAO;
import DAO.StoreDAO;
import DAO.TypeDAO;
import Model.Blog.Blog;
import Model.Product.Category;
import Model.Product.Feedback;
import Model.Product.Product;
import Model.User.*;
import java.util.List;

/**
 *
 * @author Asus
 */
public class viewStoreController extends HttpServlet {

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
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        String index = request.getParameter("index");
        if(index == null){
            index = "1";
        }
        int i = Integer.parseInt(index);
        BlogDAO bd = new BlogDAO();
         FilterDAO df =new FilterDAO();
        StoreDAO sb = new StoreDAO();
        ProductDAO pb = new ProductDAO();
        CategoryDAO db = new CategoryDAO();
        FeedbackDAO fd = new FeedbackDAO();
        UserDAO ud = new UserDAO();
        TypeDAO td = new TypeDAO();
        int avgRateStar = sb.avgStarRate(store_id);
        int totalOfProduct = pb.totalProductOfStore(store_id);
        int numPage = totalOfProduct/9;
        if(totalOfProduct %9 !=0){
            numPage++;
        }
        List<Feedback> listFeedback = fd.getCommentForViewStore(store_id);
        Store store = sb.getStoreByIdForViewStore(store_id);
        List<Product> listProduct = pb.getListProductForViewStore(store_id,i);
        List<Product> listSalesProduct = pb.getListTopSalesProductForViewStore(store_id);
        List<Category> listCategory = db.getListCategoryForStore(store_id);
         List<Blog> listBlogLatest = bd.getListLatestBlogForSeller(store_id);
         request.setAttribute("listLatestBlog", listBlogLatest);
        request.setAttribute("store", store);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listSalesProduct", listSalesProduct);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("avgRateStar", avgRateStar);
        request.setAttribute("totalOfProduct", totalOfProduct);
        request.setAttribute("index", i);
        request.setAttribute("numPage", numPage);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("id", store_id);
        request.setAttribute("pb", pb);
        request.setAttribute("db", db);
        request.setAttribute("ud", ud);
        request.setAttribute("td", td);
        request.setAttribute("df", df);
        request.getRequestDispatcher("view/CustomerView/ViewStore.jsp").forward(request, response);
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
