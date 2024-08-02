/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import DAO.BlogDAO;
import DAO.CategoryDAO;

import DAO.FeedbackDAO;
import DAO.UserDAO;

import DAO.ProductDAO;
import DAO.TypeDAO;
import Model.Blog.Blog;
import Model.Product.Category;
import Model.Product.Feedback;
import Model.Product.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author hbtth
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CategoryDAO db = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        FeedbackDAO fd = new FeedbackDAO();
        UserDAO ud = new UserDAO();
        TypeDAO td = new TypeDAO();
        BlogDAO bd = new BlogDAO();
        List<Product> listTopSalesProduct = pd.getListTopSaleProduct(1);
        List<Category> listCata = db.getListCategoryForHomePage();
        List<Product> listProduct = pd.getListProductForHomePage();
        List<Feedback> listFeedback = fd.getCommentForHomepage();
          List<Blog> listBlogLatest = bd.getListLatestBlog();
         request.setAttribute("listLatestBlog", listBlogLatest);
        session.removeAttribute("s_u_r_tikilazapee");
        request.setAttribute("listSalesProduct", listTopSalesProduct);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listCata", listCata);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("ud", ud);
        request.setAttribute("db", db);
        request.setAttribute("pd", pd);
        request.setAttribute("td", td);
        request.getRequestDispatcher("view/Homepage.jsp").forward(request, response);

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
