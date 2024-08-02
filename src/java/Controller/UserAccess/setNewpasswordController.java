/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.UserAccess;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import org.json.JSONObject;
import util.Hashing;
import util.Mail;
import util.Validate;

/**
 *
 * @author hbtth
 */
public class setNewpasswordController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet setNewpasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet setNewpasswordController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("view/loginView/setNewpasswordPage.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String OTP = request.getParameter("OTP");
        String OTPsystem = (String) session.getAttribute("OTP");
        if(!OTP.equals(OTPsystem)){
            request.setAttribute("notice", "OTP is not correct!!!");
            request.getRequestDispatcher("view/loginView/setNewpasswordPage.jsp").forward(request, response);
        }
        else if(!password.equals(re_password)){
            request.setAttribute("notice", "Password and Re-Password is not the same!!!");
            request.getRequestDispatcher("view/loginView/setNewpasswordPage.jsp").forward(request, response);
        }else if(!Validate.checkPassword(password)){
            request.setAttribute("notice", "* Password must be between 8 and 31 character(include character and number) and must"
                                           + " be alphanumeric and contain at least 1 special character!<br>");
            request.getRequestDispatcher("view/loginView/setNewpasswordPage.jsp").forward(request, response);
        }else{
            
            String username = (String) session.getAttribute("username");
            UserDAO db = new UserDAO();
            Hashing hash = new Hashing();
            session.removeAttribute("username");
            session.removeAttribute("email");
            db.updatePassword(username, hash.encrypt(password));
            response.sendRedirect("login");
        }
    }
   

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
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
