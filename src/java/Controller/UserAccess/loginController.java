/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.UserAccess;

import DAO.UserDAO;
import Model.User.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Hashing;

/**
 *
 * @author hbtth
 */
public class loginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String s = request.getServletPath();
            out.println(s);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookie = request.getCookies();
        Hashing hashing = new Hashing();
        String username = null;
        String password = null;
        String rem = null;
        // traversall all cookie 
        if (cookie != null) {
            for (Cookie i : cookie) {
                // take username when get cookie with name "c_u_tikilazapee"
                if (i.getName().equals("c_u_tikilazapee")) {
                    username = i.getValue();
                }
                // take password when get cookie with name "c_u_tikilazapee"
                if (i.getName().equals("c_p_tikilazapee")) {
                    password = i.getValue();
                }
                // take remember me when get cookie with name "c_u_tikilazapee"
                if (i.getName().equals("c_r_tikilazapee")) {
                    rem = i.getValue();
                }
            }
            request.setAttribute("username", username);
            try {
                // check password is null and decrypt password from cookie to set value in form login page
                if (password != null) {
                    request.setAttribute("password", hashing.decrypt(password));
                }
            } catch (Exception ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("rem", rem);
        }
        request.getRequestDispatcher("view/loginView/loginPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Hashing hash = new Hashing();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String newpassword = hash.encrypt(password);
            String rem = request.getParameter("rem");
            UserDAO db = new UserDAO();
            Account account = new Account(username, newpassword);
            if (db.checkLogin(account)) {
                // remember account to cookie
                if (db.checkIsActive(account)) {
                    if (rem != null) {
                        Cookie c_u_tikilazapee = new Cookie("c_u_tikilazapee", username);
                        Cookie c_p_tikilazapee = new Cookie("c_p_tikilazapee", newpassword);
                        Cookie c_r_tikilazapee = new Cookie("c_r_tikilazapee", rem);
                        c_p_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                        c_u_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                        c_r_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                        response.addCookie(c_u_tikilazapee);
                        response.addCookie(c_p_tikilazapee);
                        response.addCookie(c_r_tikilazapee);
                    }
//                 else {
//                    User user = db.getUserByUsername(username);
//                    HttpSession session = request.getSession();
//                    session.setAttribute("s_u_tikilazapee", user);
//                }
                    // contain session login on 3 day
                    Cookie c_u_tikilazapee = new Cookie("c_s_u_tikilazapee", username);
                    Cookie c_p_tikilazapee = new Cookie("c_s_p_tikilazapee", newpassword);
                    c_p_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                    c_u_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                    response.addCookie(c_u_tikilazapee);
                    response.addCookie(c_p_tikilazapee);
                    response.sendRedirect("index");
                } else {
                    request.setAttribute("noitce", "Your account has been banned from our systems due to a violation of our policies and terms");
                    request.getRequestDispatcher("view/loginView/loginPage.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("noitce", "Username or password is not correct!!!");
                request.getRequestDispatcher("view/loginView/loginPage.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
