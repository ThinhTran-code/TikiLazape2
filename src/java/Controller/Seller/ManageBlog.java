/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Seller;

import DAO.*;
import Model.Blog.*;
import Model.Product.Category;
import Model.User.User;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ManageBlog extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession();
            User user = (User) session.getAttribute("s_u_tikilazapee");
        BlogDAO bd = new BlogDAO();
        UserDAO ud = new UserDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Blog> listLeft = bd.getListBlogForSellerLeft(user.getUser_id());
         List<Blog> listRight = bd.getListBlogForSellerRight(user.getUser_id());
        List<Blog> listBlogLatest = bd.getListLatestBlogForSeller(user.getUser_id());
        List<Category> listCategory = cd.getListCategoryForManageBlogSeller(user.getUser_id());
        request.setAttribute("listLeft", listLeft);
        request.setAttribute("listRight", listRight);
        request.setAttribute("listLatestBlog", listBlogLatest);
        request.setAttribute("listCategories", listCategory);
        request.setAttribute("user", ud);
        request.setAttribute("category", cd);
         ServletContext context = request.getServletContext();
        context.getRequestDispatcher("/view/SellerView/ManageBlog.jsp").forward(request, response);
    
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
