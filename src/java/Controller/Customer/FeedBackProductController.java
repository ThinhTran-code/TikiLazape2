/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.BlogDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.OrderDAO;
import Model.Blog.Blog;
import Model.Product.Feedback;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ACER
 */
public class FeedBackProductController extends HttpServlet {

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
            out.println("<title>Servlet FeedBackProductController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedBackProductController at " + request.getContextPath() + "</h1>");
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
        int orderDetai_id = Integer.parseInt(request.getParameter("orderDetai_id"));
        request.setAttribute("orderDetai_id", orderDetai_id);
        request.getRequestDispatcher("view/CustomerView/Feedback.jsp").forward(request, response);
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
        int orderDetailId = Integer.parseInt(request.getParameter("orderDetai_id"));
        String feedback = request.getParameter("feedback");
        int rateStars = Integer.parseInt(request.getParameter("rating-input")); 
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_tikilazapee");
       try {
           FeedbackDAO fb = new FeedbackDAO();
            int product_id = fb.insertFeedBack(orderDetailId, feedback, rateStars, user.getUser_id());
            if (product_id != 0) {
                response.sendRedirect("product?product_id=" + product_id);
            } 
            
        } catch (Exception e) {
           e.printStackTrace();
           
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
