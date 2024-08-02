/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.UserAccess;

import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import org.json.JSONObject;
import util.Mail;


/**
 *
 * @author hbtth
 */
public class sendCaptchaController extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sendCaptchaController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sendCaptchaController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_is_check_authentication");
        String OTP = util.Mail.send(user.getEmail());
        session.setAttribute("OTP", OTP);
        response.sendRedirect("authentication");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_is_check_authentication");
        String OTP = util.Mail.send(user.getEmail());
        session.setAttribute("OTP", OTP);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("s_u_is_check_authentication");
        session.removeAttribute("OTP");
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());
        String username = json.getString("username");
        String email =  json.getString("email");
        String OTP = Mail.sendOTP(email);
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        session.setAttribute("OTP", OTP);
        session.setAttribute("email", email);
        System.out.println(sb.toString() + "OTP" + OTP);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
