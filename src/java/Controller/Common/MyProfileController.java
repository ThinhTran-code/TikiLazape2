/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Common;

import DAO.UserDAO;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Thinh
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, //1mb
        maxFileSize = 1024 * 1024 * 10,//10mb
        maxRequestSize = 1024 * 1024 * 11// 11mb
)

public class MyProfileController extends HttpServlet {

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
            out.println("<title>Servlet MyProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyProfileController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("view/CustomerView/Profile.jsp").forward(request, response);

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
        String user_id = request.getParameter("user_id");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phoneNumber");
        String dobir = request.getParameter("dobir");
        String email = request.getParameter("email");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String address = request.getParameter("address");
        String image = "image//image_avatar_user//avataruser(0).jpg";
        String uploadFile = getServletContext().getRealPath("image/image_avatar_user");
        User user = (User) session.getAttribute("s_u_tikilazapee");
        Date date = new Date();
        // update user
        user.setFullname(fullname);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setAddress(address);
      
        int gender = Integer.parseInt(request.getParameter("gender"));
        user.setGender(gender);
        user.setDOB(java.sql.Date.valueOf(dobir));
        UserDAO userDAO = new UserDAO();
        

        try {

            ArrayList<String> listMonth = (ArrayList<String>) session.getAttribute("listMonth");
            int month_int = listMonth.indexOf(month) + 1;

            Part part = request.getPart("image");
            if (part != null && part.getSize() > 0) {
                part.write(uploadFile + "/" + part.getSubmittedFileName());
                image = "image/image_avatar_user/" + part.getSubmittedFileName();
                user.setImage(image);
            }
            userDAO.updateUser(user);
            session.setAttribute("s_u_tikilazapee", user); // Update the session attribute with the modified user object
            request.setAttribute("message", "Profile updated successfully");
            request.getRequestDispatcher("view/CustomerView/Profile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating profile");
            request.getRequestDispatcher("./view/CustomerView/Profile.jsp").forward(request, response);
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
