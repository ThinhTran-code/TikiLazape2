/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import DAO.UserDAO;
import Model.User.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import util.Hashing;
import util.Validate;

/**
 *
 * @author Thinh
 */
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
    HttpSession session = request.getSession();
    UserDAO userDAO = new UserDAO();
    Hashing hashing = new Hashing();

    String user_id = request.getParameter("user_id");
    String currentpassword = request.getParameter("current_password");
    String newpassword = request.getParameter("new_password");
    String re_newpassword = request.getParameter("re_new_password");

    // Check if any required parameters are null
    if (user_id != null && currentpassword != null && newpassword != null && re_newpassword != null) {
        // Trim only if not null
        user_id = user_id.trim();
       currentpassword = currentpassword.trim();
         newpassword = newpassword.trim();
         re_newpassword = re_newpassword.trim();
        // Retrieve the account from session
        Account account = (Account) session.getAttribute("s_u_tikilazapee");        
        try {
            // Validate the current password
            if (!hashing.encrypt(currentpassword).equals(account.getPassword())) {
                request.setAttribute("messPassword", "Current password is incorrect");
            } else if (!newpassword.equals(re_newpassword)) {
                // Validate the new password and re-entered new password match
                request.setAttribute("messPassword", "New passwords do not match");
            } else if (!Validate.checkPassword(newpassword)) {
                request.setAttribute("notice", "* Password must be between 8 and 31 characters (including letters and numbers) and must contain at least 1 special character!<br>");
            } else {
                // Hash the new password and update it in the database
                userDAO.changePassword(hashing.encrypt(newpassword), user_id);
                request.setAttribute("messPassword", "Password updated successfully");
                
            }
        } catch (Exception ex) {
            request.setAttribute("messPassword", "An error occurred during password change");
        }
        // Forward to the appropriate JSP page
        request.getRequestDispatcher("view/CustomerView/Profile.jsp").forward(request, response);
    } else {
        // Handle missing parameters or invalid state
        request.setAttribute("messPassword", "Missing parameters or invalid state");
        request.getRequestDispatcher("view/CustomerView/Profile.jsp").forward(request, response);
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
