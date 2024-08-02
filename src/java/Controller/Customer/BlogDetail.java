/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.BlogDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.TypeDAO;
import DAO.UserDAO;
import Model.Blog.Blog;
import Model.Blog.Interaction_Blog;
import Model.Product.Category;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hbtth
 */
public class BlogDetail extends HttpServlet {

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
            out.println("<title>Servlet BlogDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogDetail at " + request.getContextPath() + "</h1>");
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

        try {
            int blog_id = Integer.parseInt(request.getParameter("blog_id"));

            UserDAO ud = new UserDAO();
            CategoryDAO cd = new CategoryDAO();
            BlogDAO bl = new BlogDAO();
            List<Blog> listBlogLatest = bl.getListLatestBlog();
            Blog blog = bl.getBlogByBlogId(blog_id);
            List<Blog> listAll = bl.getAllBlogs();
            request.setAttribute("category", cd);
            request.setAttribute("user", ud);
            request.setAttribute("listLatestBlog", listBlogLatest);
            request.setAttribute("listAll", listAll);
            request.setAttribute("blog", blog);

            String email = null;
            String pw = null;
            for (Cookie cooky : request.getCookies()) {
                String name = cooky.getName();
                String value = cooky.getValue();
                if ("c_u_tikilazapee".equals(name)) {
                    email = value;
                } else if ("c_p_tikilazapee".equals(name)) {
                    pw = value;
                }
                
            }
            User user = (User) request.getSession().getAttribute("s_u_tikilazapee");
            if (user != null && email != null) {
                user = ud.getUser(email, pw);
            }
            request.setAttribute("currentUser", user);
           
            ArrayList<Interaction_Blog> interactionList = (ArrayList<Interaction_Blog>) bl.getInteractionBlog(blog_id);
            request.setAttribute("interactionList", interactionList);

            request.getRequestDispatcher("view/CustomerView/BlogDetail.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        BlogDAO bl = new BlogDAO();
        int blogId = Integer.parseInt(request.getParameter("blog"));
        int userId = Integer.parseInt(request.getParameter("user"));
        String comment = request.getParameter("comment");
        try {
            bl.insertComment(blogId, userId, comment);
            response.sendRedirect("http://localhost:9999/tikilazapee/blogdetail?blog_id=" + blogId);
        } catch (SQLException ex) {
            Logger.getLogger(BlogDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
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
