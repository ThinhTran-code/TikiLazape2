/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.UserAccess;

import DAO.UserDAO;
import Model.User.Role;
import Model.User.User;
import Model.User.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import util.Hashing;
import util.Validate;

/**
 *
 * @author hbtth
 */
public class registerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> listDay = new ArrayList(),
                listMonth = new ArrayList(),
                listYears = new ArrayList();
        // create list day 
        for (int i = 0; i < 31; ++i) {
            listDay.add((i + 1) + "");
        }
        // create list month
        listMonth.add("Jan");
        listMonth.add("Feb");
        listMonth.add("Mar");
        listMonth.add("Apr");
        listMonth.add("May");
        listMonth.add("Jun");
        listMonth.add("Jul");
        listMonth.add("Aug");
        listMonth.add("Sep");
        listMonth.add("Oct");
        listMonth.add("Nov");
        listMonth.add("Dec");
        Date now = new Date();
        // traversal from date now into 120 year ago to add into list
        for (int i = now.getYear(); i >= now.getYear() - 120; --i) {
            listYears.add((i + 1900) + "");
        }
        HttpSession session = request.getSession();
        session.setAttribute("listDay", listDay);
        session.setAttribute("listMonth", listMonth);
        session.setAttribute("listYears", listYears);
        request.getRequestDispatcher("view/loginView/registerPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm-password");
        String email = request.getParameter("email");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        int gender = Integer.parseInt(request.getParameter("gender"));
        int role = Integer.parseInt(request.getParameter("role"));
        UserDAO db = new UserDAO();
        // get list Month from session
        HttpSession session = request.getSession();
        ArrayList<String> listMonth = (ArrayList<String>) session.getAttribute("listMonth");
        int month_int = listMonth.indexOf(month) + 1;
        java.sql.Date birthDate = java.sql.Date.valueOf(year + "-" + month_int + "-" + day);
        String notice = "";
        if (password.equals(confirm_password)) {
            // check validate email, password, and username
            if (!Validate.checkEmail(email) || !Validate.checkPassword(password)
                    || !Validate.checkUsername(username) || !Validate.checkAge(year)) {
                // to check Username must be alphanumeric and doesn't contain a speacial character
                if (!Validate.checkUsername(username)) {
                    notice += "*Username must be alphanumeric and doesn't contain a speacial character!<br>";
                }
                // to Password must be between 8 and 31 character(include character and number) and
                // must be alphanumeric and contain at least 1 special character
                if (!Validate.checkPassword(password)) {
                    notice += "* Password must be between 8 and 31 character(include character and number) and must"
                            + " be alphanumeric and contain at least 1 special character!<br>";
                }
                //Email matches one or more alphanumeric characters before the @ symbol. and have at least 1 domain
                if (!Validate.checkEmail(email)) {
                    notice += "* Email matches one or more alphanumeric characters before the @ symbol. and have at least 1 domain!<br>";
                }
                if (!Validate.checkAge(year)) {
                    notice += "* User's age must greater than 13! <br>";
                }
                request.setAttribute("notice", notice);
                request.getRequestDispatcher("view/loginView/registerPage.jsp").forward(request, response);
            } else {
                Account a = new Account();
                Hashing hash = new Hashing();
                // create default image to user
                String image = (gender == 0 ? "image\\image_avatar_user\\avataruser(0).jpg"
                        : (gender == 1 ? "image\\image_avatar_user\\avataruser(1).jpg"
                                : "image\\image_avatar_user\\avataruser(2).jpg"));
                User u = new User(fullname, email, gender, address, birthDate, image);
                // hash password to insert into database
                String hashpassword = hash.encrypt(password);
                u.setPassword(hashpassword);

                Role roleUser = new Role();
                roleUser.setRole_id(role);
                u.setUsername(username);
                u.setRole(roleUser);
                //to check insert user to database 
                if (db.addUser(u)) {
                    Cookie c_u_tikilazapee = new Cookie("c_s_u_tikilazapee", username);
                    Cookie c_p_tikilazapee = new Cookie("c_s_p_tikilazapee", u.getPassword());
                    c_p_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                    c_u_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                    response.addCookie(c_u_tikilazapee);
                    response.addCookie(c_p_tikilazapee);
                    if (u.getRole().getRole_id() == 2) {
                        session.setAttribute("s_u_r_tikilazapee", db.getUser(username, u.getPassword()));
                        response.sendRedirect("register-store");
                    } else {
                        response.sendRedirect("index");
                    }
                } else {
                    request.setAttribute("notice", "Username is already existed!!!");
                    request.getRequestDispatcher("view/loginView/registerPage.jsp").forward(request, response);
                }
            }
        } else {
            notice += "Password and confirm-password is not the same!!!";
            request.setAttribute("notice", notice);
            request.getRequestDispatcher("view/loginView/registerPage.jsp").forward(request, response);
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
